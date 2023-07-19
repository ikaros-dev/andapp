package run.ikaros.app.and.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @FormUrlEncoded
    @POST("/login")
    Call<UserLoginResponse> login(@Field("username") String username,
              @Field("password") String password);
}
