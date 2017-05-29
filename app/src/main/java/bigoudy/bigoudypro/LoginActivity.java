package bigoudy.bigoudypro;

import android.content.Intent;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
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

                Call<UserModel> userModelCall = serviceApi.getUserModel(action, mailUser, passwordUser);

                userModelCall.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> userModelResponse) {
                        if (userModelResponse.body().getIdConnectedUser() != "-1" & userModelResponse != null) {
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("bigoudyMailUser", mailUser).commit();
                            PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putString("bigoudyPasswordUser", passwordUser).commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                    }
                });

        }
    }
}
