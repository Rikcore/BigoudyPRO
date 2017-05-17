package bigoudy.bigoudypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewBienvenue = (TextView)findViewById(R.id.textViewBienvenue);
        TextView textViewBigoudy = (TextView)findViewById(R.id.textViewBigoudy);
        Button buttonConnection = (Button)findViewById(R.id.buttonConnection);

        textViewBienvenue.setTypeface(MainActivity.montserratRegular);
        textViewBigoudy.setTypeface(MainActivity.butlerExtraBold);
        buttonConnection.setTypeface(MainActivity.montserratRegular);

    }
}
