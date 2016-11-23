package com.epam.onlineshop.encryption;

import com.epam.onlineshop.constants.DefaultValueConstants;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The PasswordEncryption class, through which implemented password encryption system users.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class PasswordEncryption {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(PasswordEncryption.class);

    /**
     * This method that encrypts passwords based on the encryption algorithm MD5.
     *
     * @param st is the password which entered when registering the user.
     * @return String is encrypted password.
     */
    public static String encryptMD5(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance(DefaultValueConstants.DEFAULT_ENCRYPTING_ALGORITHM);
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}
