package bigoudy.bigoudypro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private String mailUser;
    private String passwordUser;

    Retrofit retrofit;

    private static ServiceApi serviceApi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);

        TextView textViewBienvenue = (TextView)findViewById(R.id.textViewBienvenue);
        TextView textViewBigoudy = (TextView)findViewById(R.id.textViewBigoudy);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        Button buttonConnection = (Button)findViewById(R.id.buttonConnection);
        buttonConnection.setOnClickListener(this);


        Fonts.setFontMontSerrat(this, textViewBienvenue);
        Fonts.setFontButler(this, textViewBigoudy);
        Fonts.setFontMontSerrat(this, editTextEmail);
        Fonts.setFontMontSerrat(this, editTextPassword);
        Fonts.setFontMontSerrat(this, buttonConnection);


        OkHttpClient client = new OkHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(Resources.RESOURCES)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceApi = retrofit.create(ServiceApi.class);


    }

    protected void onStart(){
        super.onStart();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonConnection:
                mailUser = editTextEmail.getText().toString();
                passwordUser = editTextPassword.getText().toString();


                String action = "connectUser";
                final String passwordUserSha1 = new String(Hex.encodeHex(DigestUtils.sha1(passwordUser)));

                Call<UserModel> userModelCall = serviceApi.getUserModel(action, mailUser, passwordUserSha1);

                userModelCall.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> userModelResponse) {
                        if (userModelResponse.body().getIdConnectedUser() != "-1" & userModelResponse != null) {
                            String bigouderId = userModelResponse.body().getIdConnectedUser();
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("bigouderId", bigouderId).commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                        editTextPassword.setText("");
                    }
                });

        }
    }


}
