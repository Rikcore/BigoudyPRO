package bigoudy.bigoudypro;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by apprenti on 22/05/17.
 */

public interface ServiceApi {
    @FormUrlEncoded
    @POST("user.php")
    Call<UserModel> getUserModel(
            @Field("action") String action,
            @Field("mailUser") String mailUser,
            @Field("passwordUser") String passWordUser
    );

    @FormUrlEncoded
    @POST("meeting.php")
    Call<BookingModel> getBookingModel(
            @Field("action") String action,
            @Field("idBigouder") Integer idBigouder,
            @Field("filterDemand") String filterDemand
    );

    @FormUrlEncoded
    @POST("meeting.php")
    Call<DeclineMeeting> declineReservation(
            @Field("action") String action,
            @Field("idMeeting") Integer idMeeting,
            @Field("cancellationReason") String cancellationReason
    );

    @FormUrlEncoded
    @POST("meeting.php")
    Call<AcceptReservation> acceptReservation(

            @Field("action") String action,
            @Field("idMeeting") Integer idMeeting,
            @Field("durationMeeting") Integer durationMeeting,
            @Field("dateMeeting") String dateMeeting,
            @Field("timesAvailable") String timesAvailable,
            @Field("moreInfoBigouder") String moreInfoBigouder,
            @Field("idBigouder") Integer idBigouder,
            @Field("dateDiagnosticMeeting") String dateDiagnosticMeeting,
            @Field("timeDiagnosticAvailable") String timeDiagnosticAvailable
    );

    @FormUrlEncoded
    @POST("chat.php")
    Call<Message> sendMessageRdv(
            @Field("action") String action,
            @Field("idSender") Integer idSender,
            @Field("idReceiver") Integer idReceiver,
            @Field("textMessage") String textMessage
    );

    @FormUrlEncoded
    @POST("bigouder.php")
    Call<ExceptionTime> setException(
            @Field("action") String action,
            @Field("idBigouder") Integer idBigouder,
            @Field("beginTime") String beginTime,
            @Field("duration") Integer duration,
            @Field("day") String day,
            @Field("date") String date,
            //@Field("available") Boolean available
            @Field("available") Integer available
    );

}
