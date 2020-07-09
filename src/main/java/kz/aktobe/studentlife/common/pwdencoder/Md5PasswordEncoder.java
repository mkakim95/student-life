package kz.aktobe.studentlife.common.pwdencoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;

@Component
@Primary
public class Md5PasswordEncoder implements PasswordEncoder {
    private final Log logger;

    public Md5PasswordEncoder() {
        this.logger = LogFactory.getLog(this.getClass());
    }

    public String encode(CharSequence rawPassword) {
        return crypt(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (hasText(encodedPassword) && hasText(rawPassword)) {
            return crypt(rawPassword.toString()).equals(encodedPassword);
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }

    private String crypt(String str) {
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte aHash : hash) {
                if ((0xff & aHash) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & aHash));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}
