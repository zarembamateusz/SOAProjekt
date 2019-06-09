package util;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@UtilityClass
public class PasswordUtil {
    public String encode(final String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        val passwordBytes = password.getBytes();
        val hash = md.digest(passwordBytes);
        val encodedBytes = Base64.getEncoder().encodeToString(hash);
        return encodedBytes;
    }
}
