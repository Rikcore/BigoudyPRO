package bigoudy.bigoudypro;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;


import java.util.Calendar;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements InboxFragment.OnFragmentInteractionListener, AgendaFragment.OnFragmentInteractionListener, BookingFragment.OnFragmentInteractionListener, MeetingDetailFragment.OnFragmentInteractionListener {

    InboxFragment inboxFragment;
    AgendaFragment agendaFragment;
    BookingFragment bookingFragment;
    FragmentManager fragmentManager;

    android.support.v7.app.ActionBar actionBar;


    String mailUser;
    String passwordUser;
    UserModel currentUser;
    BookingModel bookingModel;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent myIntent = new Intent(MainActivity.this, MyService.class);
        startService(myIntent);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez");
        actionBar = getSupportActionBar();
        actionBar.setTitle("Agenda");


        String mailUser = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("bigoudyMailUser", null);
        String passwordUser = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("bigoudyPasswordUser", null);

        if (mailUser != null && passwordUser != null){
            progressDialog.show();
            getCurrentUser(mailUser, passwordUser);

        }
        else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }



        fragmentManager = getSupportFragmentManager();

        inboxFragment = new InboxFragment();
        agendaFragment = new AgendaFragment();
        bookingFragment = new BookingFragment();



        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_agenda);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_inbox:
                        //Do what you need
                        actionBar.hide();
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.contentLayout, inboxFragment, inboxFragment.getTag())
                                .commit();
                        return true;
                    case R.id.navigation_agenda:
                        actionBar.show();
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.contentLayout, agendaFragment, agendaFragment.getTag())
                                .commit();
                        //Do what you need
                        return true;
                    case R.id.navigation_booking:
                        actionBar.hide();
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.contentLayout, bookingFragment, bookingFragment.getTag())
                                .commit();
                        //Do what you need
                        return true;
                    default:
                        return false;
                }
            }
        });


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void getCurrentUser (String mailUser, String passwordUser){
        String action = "connectUser";


        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        Call<UserModel> userModelCall = serviceApi.getUserModel(action, mailUser, passwordUser);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                currentUser = response.body();
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("idBigouder", currentUser.getIdConnectedUser()).commit();
                progressDialog.dismiss();
                String totcaca = currentUser.getIdConnectedUser();
                Bundle bundle = new Bundle();
                bundle.putString("idConnectUser", currentUser.getIdConnectedUser());
                bookingFragment.setArguments(bundle);
                inboxFragment.setArguments(bundle);
                agendaFragment.setArguments(bundle);
                callModel(currentUser.getIdConnectedUser());



            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });

    }

    public void callModel (final String idConnectUser){
        final BookingModel model;
        String action = "getIncomingMeetingByBigouderId";
        Integer id = new Integer(idConnectUser).intValue();
        final String[] filter = {"incoming"};

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        final Call<BookingModel> bookingModelCall = serviceApi.getBookingModel(action, id, filter[0]);

        bookingModelCall.enqueue(new Callback<BookingModel>() {
            @Override
            public void onResponse(Call<BookingModel> call, Response<BookingModel> response) {
                bookingModel = response.body();
                Bundle bundle = new Bundle();
                bundle.putSerializable("bookingModel", bookingModel);
                bundle.putString("idConnectUser", idConnectUser);
                agendaFragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.contentLayout, agendaFragment, agendaFragment.getTag())
                        .commit();

            }

            @Override
            public void onFailure(Call<BookingModel> call, Throwable t) {
                String toto;

            }
        });

        return;




    }

}
