
package bigoudy.bigoudypro;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message implements Serializable
{

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("idDiscussion")
    @Expose
    private String idDiscussion;
    @SerializedName("idFirstUser")
    @Expose
    private String idFirstUser;
    @SerializedName("idSecondUser")
    @Expose
    private String idSecondUser;
    @SerializedName("dateCreationMessage")
    @Expose
    private String dateCreationMessage;
    private final static long serialVersionUID = -4280215126380757559L;

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

    public String getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(String idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public String getIdFirstUser() {
        return idFirstUser;
    }

    public void setIdFirstUser(String idFirstUser) {
        this.idFirstUser = idFirstUser;
    }

    public String getIdSecondUser() {
        return idSecondUser;
    }

    public void setIdSecondUser(String idSecondUser) {
        this.idSecondUser = idSecondUser;
    }

    public String getDateCreationMessage() {
        return dateCreationMessage;
    }

    public void setDateCreationMessage(String dateCreationMessage) {
        this.dateCreationMessage = dateCreationMessage;
    }

}
