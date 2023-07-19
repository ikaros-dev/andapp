package run.ikaros.app.and.api;

public class UserApi {
    public User login(String username, String password) {
        User user = new User();
        user.setUsername("tomoki");
        user.setPassword("tomoki");
        return user;
    }
}
