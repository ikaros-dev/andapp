package run.ikaros.app.and.Utils;

import java.util.Objects;

public class Assert {
    public static void notBlank(String str, String msg) {
        if(StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void notNull(Object obj, String msg) {
        if(Objects.isNull(obj)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void isTrue(boolean con, String msg) {
        if(!con) {
            throw new IllegalArgumentException(msg);
        }
    }
}
