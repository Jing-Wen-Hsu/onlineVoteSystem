package onlineVoteSystem.onlineVoteSystem.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 加密密碼
    public static String encodePassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    // 比對密碼
    public static boolean matchPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
