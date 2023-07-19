package run.ikaros.app.and.api;

import androidx.media3.common.util.Assertions;

import junit.framework.TestCase;

import org.junit.Test;

public class UserClientTest extends TestCase {


    // @Test
    public void testLogin() {
        UserClient userClient = new UserClient("http://nas:9999");
        User user = userClient.login("tomoki", "tomoki");
        Assertions.checkNotNull(user);
    }
}