package bigoudy.bigoudypro;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyService extends Service {

    private int count = Integer.MAX_VALUE;
    private BookingModel bookingModelForNotif;
    final android.os.Handler customHandler = new android.os.Handler();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        customHandler.postDelayed(updateTimerThread, 0);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return Service.START_STICKY;

    }

    private void displayNotification() {


        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intentNotif = new Intent(this, MainActivity.class);
        int keyNotif = 1;
        intentNotif.putExtra("keyNotif", keyNotif);
        intentNotif.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Bigoudy")
                .setContentText("Vous avez une nouvelle demande")
                .setSmallIcon(R.mipmap.notifications_copie)
                .setContentIntent(PendingIntent.getActivity(this, 0, intentNotif, PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)
                .build();

        notifManager.notify(0, noti);

    }

    public void watchBigoudy(String filter) {

        String action = "getIncomingMeetingByBigouderId";
        String idBigouder = PreferenceManager.getDefaultSharedPreferences(MyService.this).getString("bigouderId", null);

        if (idBigouder == null) {
            stopSelf();
        } else {


            OkHttpClient client = new OkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Resources.RESOURCES)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceApi serviceApi = retrofit.create(ServiceApi.class);

            final Call<BookingModel> bookingModelCall = serviceApi.getBookingModel(action, Integer.valueOf(idBigouder), filter);

            bookingModelCall.enqueue(new Callback<BookingModel>() {
                @Override
                public void onResponse(Call<BookingModel> call, Response<BookingModel> response) {
                    bookingModelForNotif = response.body();
                    int listSize = bookingModelForNotif.getMeetings().size();
                    if(listSize > count){
                        displayNotification();
                    }
                    count = listSize;

                }

                @Override
                public void onFailure(Call<BookingModel> call, Throwable t) {
                }
            });


        }
    }

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            watchBigoudy("demand");
            customHandler.postDelayed(this, 5000 /*900000*/); //15 minutes
        }
    };
}
