package bigoudy.bigoudypro;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity implements InboxFragment.OnFragmentInteractionListener, AgendaFragment.OnFragmentInteractionListener, BookingFragment.OnFragmentInteractionListener {

    InboxFragment inboxFragment;
    AgendaFragment agendaFragment;
    BookingFragment bookingFragment;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
