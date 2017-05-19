package bigoudy.bigoudypro;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewBienvenue = (TextView)findViewById(R.id.textViewBienvenue);
        TextView textViewBigoudy = (TextView)findViewById(R.id.textViewBigoudy);
        EditText editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        Button buttonConnection = (Button)findViewById(R.id.buttonConnection);


        Fonts.setFontMontSerrat(this, textViewBienvenue);
        Fonts.setFontButler(this, textViewBigoudy);
        Fonts.setFontMontSerrat(this, editTextEmail);
        Fonts.setFontMontSerrat(this, editTextPassword);
        Fonts.setFontMontSerrat(this, buttonConnection);



    }
}
