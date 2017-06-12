package bigoudy.bigoudypro;

import android.net.Uri;

/**
 * Created by apprenti on 01/06/17.
 */

public class FakeBooking {

    private String uriAvatar;
    private String dateRdv;
    private String firstNameLastName;
    private String coupe;
    private String priceAdresse;

    public FakeBooking (String uriAvatar, String dateRdv, String firstNameLastName, String coupe, String priceAdresse){
        this.uriAvatar = uriAvatar;
        this.dateRdv = dateRdv;
        this.firstNameLastName = firstNameLastName;
        this.coupe = coupe;
        this.priceAdresse = priceAdresse;
    }

    public String getUriAvatar() {
        return uriAvatar;
    }

    public void setUriAvatar(String uriAvatar) {
        this.uriAvatar = uriAvatar;
    }

    public String getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(String dateRdv) {
        this.dateRdv = dateRdv;
    }

    public String getFirstNameLastName() {
        return firstNameLastName;
    }

    public void setFirstNameLastName(String firstNameLastName) {
        this.firstNameLastName = firstNameLastName;
    }

    public String getCoupe() {
        return coupe;
    }

    public void setCoupe(String coupe) {
        this.coupe = coupe;
    }

    public String getPriceAdresse() {
        return priceAdresse;
    }

    public void setPriceAdresse(String priceAdresse) {
        this.priceAdresse = priceAdresse;
    }
}
