package bigoudy.bigoudypro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static Typeface butlerExtraBold ;
    public static Typeface montserratRegular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butlerExtraBold = Typeface.createFromAsset(getAssets(), "fonts/butler_extrabold.otf");
        montserratRegular = Typeface.createFromAsset(getAssets(), "fonts/montserrat_regular.ttf");

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });
    }
}
