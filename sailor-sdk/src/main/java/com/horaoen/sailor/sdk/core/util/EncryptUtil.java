package com.horaoen.sailor.sdk.core.util;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.algorithms.Type;
import com.amdelamar.jhash.exception.InvalidHashException;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@NoArgsConstructor
public class EncryptUtil {

    public static String encrypt(String password) {
        char[] chars = password.toCharArray();
        return Hash.password(chars).algorithm(Type.PBKDF2_SHA256).create();
    }

    public static boolean verify(String encryptedPassword, String plainPassword) {
        char[] chars = plainPassword.toCharArray();

        try {
            return Hash.password(chars).algorithm(Type.PBKDF2_SHA256).verify(encryptedPassword);
        } catch (InvalidHashException var4) {
            return false;
        }
    }
}
