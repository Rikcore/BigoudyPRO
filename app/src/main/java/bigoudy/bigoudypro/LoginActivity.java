package bigoudy.bigoudypro;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;

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


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
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
                String mailUser = editTextEmail.getText().toString();
                String passwordUser = editTextPassword.getText().toString();
                String action = "connectUser";

                //UserCredential userCredential = new UserCredential(action, mailUser, passwordUser);

                Response<UserModel> userModelResponse = null;
                Call<UserModel> userModelCall = serviceApi.getUserModel(action, mailUser, passwordUser);

                userModelCall.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        String prout = response.toString();
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        String caca = call.toString();
                    }
                });

        }
    }
}
