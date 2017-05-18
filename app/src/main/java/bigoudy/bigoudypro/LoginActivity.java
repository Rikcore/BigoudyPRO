package bigoudy.bigoudypro;

import android.graphics.Typeface;
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


        Fonts.setFontMontSerrat(this, textViewBienvenue);
        Fonts.setFontMontSerrat(this, buttonConnection);
        Fonts.setFontButler(this, textViewBigoudy);


    }
}
