package run.ikaros.app.and.api.user;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import run.ikaros.app.and.infra.utils.Assert;

public class UserClient {
    private final String baseUrl;
    private Retrofit retrofit;
    private UserApi userApi;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public UserClient(String baseUrl) {
        this.baseUrl = baseUrl;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(UserApi.class);
    }

    public User login(String username, String password) {
        Assert.notBlank(username, "'username' must not blank.");
        Assert.notBlank(password, "'password' must not blank.");
        try {
            Response<UserLoginResponse> response = userApi.login(username, password).execute();
            if(response.isSuccessful()) {
                return response.body().getEntity();
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
