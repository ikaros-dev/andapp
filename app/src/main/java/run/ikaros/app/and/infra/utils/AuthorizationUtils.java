package run.ikaros.app.and.infra.utils;

import java.util.Base64;

public class AuthorizationUtils {
    public static String encodeBasicHeader(String username, String password) {
        String str = username + ":" + password;
        return "Basic " + new String(Base64.getEncoder().encode(str.getBytes()));
    }
}
