package run.ikaros.app.and.api;

import androidx.media3.common.util.Assertions;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import run.ikaros.app.and.Utils.Assert;

public class UserClient {
    private final String baseUrl;
    private Retrofit retrofit;
    private UserApi userApi;

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
            return userApi.login(username, password).execute().body().getEntity();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
