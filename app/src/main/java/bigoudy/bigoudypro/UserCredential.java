package bigoudy.bigoudypro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apprenti on 24/05/17.
 */

// NON UTILISE


public class UserCredential {
    @SerializedName("action")
    String action;
    @SerializedName("mailUser")
    String mailUser;
    @SerializedName("passwordUser")
    String passwordUser;

    public UserCredential (String action, String mailUser, String passwordUser){
        this.action = action;
        this.mailUser = mailUser;
        this.passwordUser = passwordUser;
    }

}
