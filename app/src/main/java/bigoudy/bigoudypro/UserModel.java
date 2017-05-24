
package bigoudy.bigoudypro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("auth_error")
    @Expose
    private Integer authError;
    @SerializedName("idConnectedUser")
    @Expose
    private String idConnectedUser;
    @SerializedName("typeUser")
    @Expose
    private String typeUser;
    @SerializedName("reservUser")
    @Expose
    private String reservUser;
    @SerializedName("user_light")
    @Expose
    private UserLight userLight;
    @SerializedName("cookie_succes")
    @Expose
    private Boolean cookieSucces;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getAuthError() {
        return authError;
    }

    public void setAuthError(Integer authError) {
        this.authError = authError;
    }

    public String getIdConnectedUser() {
        return idConnectedUser;
    }

    public void setIdConnectedUser(String idConnectedUser) {
        this.idConnectedUser = idConnectedUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getReservUser() {
        return reservUser;
    }

    public void setReservUser(String reservUser) {
        this.reservUser = reservUser;
    }

    public UserLight getUserLight() {
        return userLight;
    }

    public void setUserLight(UserLight userLight) {
        this.userLight = userLight;
    }

    public Boolean getCookieSucces() {
        return cookieSucces;
    }

    public void setCookieSucces(Boolean cookieSucces) {
        this.cookieSucces = cookieSucces;
    }

}