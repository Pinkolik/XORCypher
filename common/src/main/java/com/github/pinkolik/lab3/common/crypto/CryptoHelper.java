package com.github.pinkolik.lab3.common.crypto;

import java.io.UnsupportedEncodingException;

public final class CryptoHelper {

    public static String encrypt(final String key, final String message) throws UnsupportedEncodingException {
        final byte[] keyBytes = hexStringToBytes(key.replace(" ", ""));
        final byte[] messageBytes = message.getBytes("windows-1251");
        return bytesToHex(applyXor(keyBytes, messageBytes));
    }

    private static byte[] hexStringToBytes(final String key) {
        int length = key.length();
        byte[] result = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            result[i / 2] = (byte) ((Character.digit(key.charAt(i), 16) << 4) + Character.digit(key.charAt(i + 1), 16));
        }
        return result;
    }

    private static String bytesToHex(final byte[] hashInBytes) {

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashInBytes) {
            stringBuilder.append(String.format("%02x", b).toUpperCase()).append(" ");
        }
        return stringBuilder.toString();

    }

    private static byte[] applyXor(final byte[] keyBytes, final byte[] messageBytes) {
        final byte[] resultBytes = new byte[messageBytes.length];
        for (int i = 0; i < messageBytes.length; i++) {
            byte resultByte = (byte) (messageBytes[i] ^ keyBytes[i % keyBytes.length]);
            resultBytes[i] = resultByte;
        }
        return resultBytes;
    }

    public static String decrypt(final String key, final String message) throws UnsupportedEncodingException {
        final byte[] keyBytes = hexStringToBytes(key.replace(" ", ""));
        final byte[] messageBytes = hexStringToBytes(message.replace(" ", ""));
        return new String(applyXor(keyBytes, messageBytes), "windows-1251");
    }

    public static String findFittingKey(final String encryptedMessage, final String decryptedMessage)
            throws UnsupportedEncodingException {
        final byte[] encryptedMessageBytes = hexStringToBytes(encryptedMessage.replace(" ", ""));
        final byte[] decryptedMessageBytes = decryptedMessage.getBytes("windows-1251");
        return bytesToHex(applyXor(encryptedMessageBytes, decryptedMessageBytes));
    }
}
