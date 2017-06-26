package bigoudy.bigoudypro;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by apprenti on 26/06/17.
 */

public class MessageDialog extends Dialog {

    public MessageDialog(@NonNull Context context) {
        super(context);
    }

    public void showDialog(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.message_item);

        final EditText editTextMessage = (EditText)dialog.findViewById(R.id.editTextMessage);
        Button buttonMessage = (Button)dialog.findViewById(R.id.buttonMessage);
        Button buttonAnnuler = (Button)dialog.findViewById(R.id.buttonAnnuler);

        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMessage.getText().toString().trim();
                dialog.dismiss();
            }
        });

        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.show();

    }
}
