
package bigoudy.bigoudypro; ;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLight {

    @SerializedName("idUser")
    @Expose
    private String idUser;
    @SerializedName("mailUser")
    @Expose
    private String mailUser;
    @SerializedName("maleUser")
    @Expose
    private String maleUser;
    @SerializedName("firstnameUser")
    @Expose
    private String firstnameUser;
    @SerializedName("lastnameUser")
    @Expose
    private String lastnameUser;
    @SerializedName("telUser")
    @Expose
    private String telUser;
    @SerializedName("birthdayUser")
    @Expose
    private Object birthdayUser;
    @SerializedName("cityUser")
    @Expose
    private String cityUser;
    @SerializedName("inscriptionDateUser")
    @Expose
    private String inscriptionDateUser;
    @SerializedName("linkAvatar")
    @Expose
    private String linkAvatar;
    @SerializedName("userIsActivated")
    @Expose
    private String userIsActivated;
    @SerializedName("idStripeCustomer")
    @Expose
    private Object idStripeCustomer;
    @SerializedName("lengthCreditCardNumberUser")
    @Expose
    private String lengthCreditCardNumberUser;
    @SerializedName("endCreditCardNumberUser")
    @Expose
    private Object endCreditCardNumberUser;
    @SerializedName("brandCreditCardUser")
    @Expose
    private Object brandCreditCardUser;
    @SerializedName("notifications")
    @Expose
    private List<Object> notifications = null;
    @SerializedName("hasUnreadNotif")
    @Expose
    private Integer hasUnreadNotif;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getMaleUser() {
        return maleUser;
    }

    public void setMaleUser(String maleUser) {
        this.maleUser = maleUser;
    }

    public String getFirstnameUser() {
        return firstnameUser;
    }

    public void setFirstnameUser(String firstnameUser) {
        this.firstnameUser = firstnameUser;
    }

    public String getLastnameUser() {
        return lastnameUser;
    }

    public void setLastnameUser(String lastnameUser) {
        this.lastnameUser = lastnameUser;
    }

    public String getTelUser() {
        return telUser;
    }

    public void setTelUser(String telUser) {
        this.telUser = telUser;
    }

    public Object getBirthdayUser() {
        return birthdayUser;
    }

    public void setBirthdayUser(Object birthdayUser) {
        this.birthdayUser = birthdayUser;
    }

    public String getCityUser() {
        return cityUser;
    }

    public void setCityUser(String cityUser) {
        this.cityUser = cityUser;
    }

    public String getInscriptionDateUser() {
        return inscriptionDateUser;
    }

    public void setInscriptionDateUser(String inscriptionDateUser) {
        this.inscriptionDateUser = inscriptionDateUser;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getUserIsActivated() {
        return userIsActivated;
    }

    public void setUserIsActivated(String userIsActivated) {
        this.userIsActivated = userIsActivated;
    }

    public Object getIdStripeCustomer() {
        return idStripeCustomer;
    }

    public void setIdStripeCustomer(Object idStripeCustomer) {
        this.idStripeCustomer = idStripeCustomer;
    }

    public String getLengthCreditCardNumberUser() {
        return lengthCreditCardNumberUser;
    }

    public void setLengthCreditCardNumberUser(String lengthCreditCardNumberUser) {
        this.lengthCreditCardNumberUser = lengthCreditCardNumberUser;
    }

    public Object getEndCreditCardNumberUser() {
        return endCreditCardNumberUser;
    }

    public void setEndCreditCardNumberUser(Object endCreditCardNumberUser) {
        this.endCreditCardNumberUser = endCreditCardNumberUser;
    }

    public Object getBrandCreditCardUser() {
        return brandCreditCardUser;
    }

    public void setBrandCreditCardUser(Object brandCreditCardUser) {
        this.brandCreditCardUser = brandCreditCardUser;
    }

    public List<Object> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Object> notifications) {
        this.notifications = notifications;
    }

    public Integer getHasUnreadNotif() {
        return hasUnreadNotif;
    }

    public void setHasUnreadNotif(Integer hasUnreadNotif) {
        this.hasUnreadNotif = hasUnreadNotif;
    }

}
