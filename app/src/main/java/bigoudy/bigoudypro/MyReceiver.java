package bigoudy.bigoudypro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent();
        myIntent.setClass(context, MyService.class);

        if (intent.getAction().equals(intent.ACTION_BOOT_COMPLETED)){
            context.startService(myIntent);
            Log.d("Test receive", "Receiver works");
        }
        else{
            Log.d("Test receiver", "Receiver fails");
        }
    }
}
