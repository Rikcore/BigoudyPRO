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
            @Field("idBigouder") String idBigouder,
            @Field("filterDemande") String filterDemand
    );
}
