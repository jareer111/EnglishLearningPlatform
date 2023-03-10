package com.jareer.englishlearningplatform.utils;

import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;

public class Encrypt {
    public static String hashPassword(@NonNull String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(@NonNull String password, @NonNull String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
