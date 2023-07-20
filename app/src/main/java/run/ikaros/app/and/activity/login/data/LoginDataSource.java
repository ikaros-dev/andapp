package run.ikaros.app.and.activity.login.data;

import java.io.IOException;

import run.ikaros.app.and.api.user.User;
import run.ikaros.app.and.api.user.UserClient;
import run.ikaros.app.and.infra.utils.Assert;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private UserClient userClient;

    private synchronized void initUserClient(String baseUrl) {
        Assert.notBlank(baseUrl, "'baseUrl' must not blank.");
        if (userClient == null) {
            userClient = new UserClient(baseUrl);
        }
    }

    public Result<User> login(String baseUrl, String username, String password) {
        Assert.notBlank(baseUrl, "'baseUrl' must not blank.");
        Assert.notBlank(username, "'username' must not blank.");
        Assert.notBlank(password, "'password' must not blank.");

        try {
            initUserClient(baseUrl);
            final User[] user = new User[1];
            Thread thread = new Thread(() -> user[0] = userClient.login(username, password));
            thread.start();

            while (thread.isAlive()) {
                Thread.sleep(10);
            }

            return new Result.Success<>(user[0]);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}