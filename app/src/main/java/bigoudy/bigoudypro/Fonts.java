package bigoudy.bigoudypro;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.security.AccessController.getContext;

/**
 * Created by apprenti on 17/05/17.
 */

public class Fonts {


    public static void setFontButler(Context context, TextView textView){
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/butler_extrabold.otf"));
    }
    public static void setFontButler(Context context, Button button){
        button.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/butler_extrabold.otf"));
    }
    public static void setFontMontSerrat(Context context, TextView textView){
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf"));
    }
    public static void setFontMontSerrat(Context context, EditText editText){
        editText.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf"));
    }
    public static void setFontMontSerrat(Context context, Button button){
        button.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf"));
    }
}
