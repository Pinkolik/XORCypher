package com.github.pinkolik.lab3.common.crypto;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public final class CryptoHelper {

    public static byte[] encrypt(final byte[] key, final byte[] message) {
        return applyXor(key, message);
    }

    private static byte[] applyXor(final byte[] keyBytes, final byte[] messageBytes) {
        final byte[] resultBytes = new byte[messageBytes.length];
        for (int i = 0; i < messageBytes.length; i++) {
            byte resultByte = (byte) (messageBytes[i] ^ keyBytes[i % keyBytes.length]);
            resultBytes[i] = resultByte;
        }
        return resultBytes;
    }

    public static byte[] hexStringToBytes(final String hexString) {
        String hexStringWithoutSpaces = hexString.replace(" ", "");
        int length = hexStringWithoutSpaces.length();
        byte[] result = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hexStringWithoutSpaces.charAt(i), 16) << 4) +
                    Character.digit(hexStringWithoutSpaces.charAt(i + 1), 16));
        }
        return result;
    }

    public static String bytesToHexString(final byte[] hashInBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashInBytes) {
            stringBuilder.append(String.format("%02x", b).toUpperCase()).append(" ");
        }
        return stringBuilder.toString();
    }

    public static byte[] decrypt(final byte[] key, final byte[] message) {
        return applyXor(key, message);
    }

    public static byte[] findFittingKey(final byte[] encryptedMessage, final byte[] decryptedMessage) {
        return applyXor(encryptedMessage, decryptedMessage);
    }

    public static byte[] generateRandomKey(final byte[] message) {
        final byte[] keyBytes = new byte[message.length];
        Random random = new Random();
        random.nextBytes(keyBytes);
        return keyBytes;
    }
}
