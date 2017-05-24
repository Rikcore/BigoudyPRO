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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements InboxFragment.OnFragmentInteractionListener, AgendaFragment.OnFragmentInteractionListener, BookingFragment.OnFragmentInteractionListener {

    InboxFragment inboxFragment;
    AgendaFragment agendaFragment;
    BookingFragment bookingFragment;
    FragmentManager fragmentManager;

    String mailUser;
    String passwordUser;
    UserModel currentUser;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Patientez");


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

        fragmentManager
                .beginTransaction()
                .replace(R.id.contentLayout, agendaFragment, agendaFragment.getTag())
                .commit();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_agenda);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_inbox:
                        //Do what you need
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.contentLayout, inboxFragment, inboxFragment.getTag())
                                .commit();
                        return true;
                    case R.id.navigation_agenda:
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.contentLayout, agendaFragment, agendaFragment.getTag())
                                .commit();
                        //Do what you need
                        return true;
                    case R.id.navigation_booking:
                        fragmentManager
                                .beginTransaction()
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

        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/

        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        Response<UserModel> userModelResponse = null;

        Call<UserModel> userModelCall = serviceApi.getUserModel(action, mailUser, passwordUser);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                currentUser = response.body();
                progressDialog.dismiss();
                String totcaca = currentUser.getIdConnectedUser();
                String cacatoto = currentUser.getUserLight().getFirstnameUser();
                String bruno = "copier coller";
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });

    }

}
