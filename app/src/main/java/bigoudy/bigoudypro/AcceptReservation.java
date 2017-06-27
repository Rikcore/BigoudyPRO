
package bigoudy.bigoudypro;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptReservation implements Serializable
{

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("timeNotAvailable")
    @Expose
    private Integer timeNotAvailable;
    @SerializedName("needNotifyNewMessage")
    @Expose
    private Integer needNotifyNewMessage;
    @SerializedName("notifDiscussionId")
    @Expose
    private Integer notifDiscussionId;
    @SerializedName("notifCustomerId")
    @Expose
    private Integer notifCustomerId;
    @SerializedName("paymentStripeId")
    @Expose
    private Integer paymentStripeId;
    @SerializedName("paymentAmmount")
    @Expose
    private Integer paymentAmmount;
    private final static long serialVersionUID = 4124705661333491029L;

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

    public Integer getTimeNotAvailable() {
        return timeNotAvailable;
    }

    public void setTimeNotAvailable(Integer timeNotAvailable) {
        this.timeNotAvailable = timeNotAvailable;
    }

    public Integer getNeedNotifyNewMessage() {
        return needNotifyNewMessage;
    }

    public void setNeedNotifyNewMessage(Integer needNotifyNewMessage) {
        this.needNotifyNewMessage = needNotifyNewMessage;
    }

    public Integer getNotifDiscussionId() {
        return notifDiscussionId;
    }

    public void setNotifDiscussionId(Integer notifDiscussionId) {
        this.notifDiscussionId = notifDiscussionId;
    }

    public Integer getNotifCustomerId() {
        return notifCustomerId;
    }

    public void setNotifCustomerId(Integer notifCustomerId) {
        this.notifCustomerId = notifCustomerId;
    }

    public Integer getPaymentStripeId() {
        return paymentStripeId;
    }

    public void setPaymentStripeId(Integer paymentStripeId) {
        this.paymentStripeId = paymentStripeId;
    }

    public Integer getPaymentAmmount() {
        return paymentAmmount;
    }

    public void setPaymentAmmount(Integer paymentAmmount) {
        this.paymentAmmount = paymentAmmount;
    }

}
