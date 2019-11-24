package com.github.pinkolik.lab3.common.keygen;


import java.util.Random;

/*
 * Первый байт ключа - его ключ.
 * Если младший разряд первого байта равен 1,
 * то на чётные байты ключа выполняется операция XOR,
 * а на нечётный выполняется две операции XOR:
 * 4 старших разряда ключа на 4 младших разряда кодируемого байта,
 * 4 младших разряда ключа на 4 старших разряда кодируемого байта.
 * Если младший разряд первого байта равен 0,
 * то на нечётные байты ключа выполняется операция XOR,
 * а на чётные выполняется две операции XOR:
 * 4 старших разряда ключа на 4 младших разряда кодируемого байта,
 * 4 младших разряда ключа на 4 старших разряда кодируемого байта.
 * Байт, идущий следующим после ключа считать нулевым, то есть,
 * чётным.
 * */
public class AlternateXorKeyGenerator extends AbstractKeyGenerator {

    @Override
    public byte[] generateEquivalentKey(final byte[] originalKey) {
        final byte key = (byte) new Random().nextInt();
        final byte[] result = new byte[originalKey.length + 1];
        result[0] = key;
        boolean isOdd = key % 2 == 1;
        for (int i = 0; i < originalKey.length; i++) {
            byte originalByte = originalKey[i];
            byte resultByte;
            if (i % 2 == 0) {
                if (isOdd) {
                    resultByte = (byte) (originalByte ^ key);
                }
                else {
                    resultByte = reverseXor(key, originalByte);
                }
            }
            else {
                if (isOdd) {
                    resultByte = reverseXor(key, originalByte);
                }
                else {
                    resultByte = (byte) (originalByte ^ key);
                }
            }
            result[i + 1] = resultByte;
        }
        return result;
    }

    @Override
    public byte[] decryptKey(final byte[] encryptedKey) {
        final byte key = encryptedKey[0];
        final byte[] result = new byte[encryptedKey.length - 1];
        boolean isOdd = key % 2 == 1;
        for (int i = 1; i < encryptedKey.length; i++) {
            byte encryptedByte = encryptedKey[i];
            byte resultByte;
            if ((i - 1) % 2 == 0) {
                if (isOdd) {
                    resultByte = (byte) (encryptedByte ^ key);
                }
                else {
                    resultByte = reverseXor(key, encryptedByte);
                }
            }
            else {
                if (isOdd) {
                    resultByte = reverseXor(key, encryptedByte);
                }
                else {
                    resultByte = (byte) (encryptedByte ^ key);
                }
            }
            result[i - 1] = resultByte;
        }
        return result;
    }

    private byte reverseXor(final byte firstByte, final byte secondByte) {
        return (byte) (((firstByte >>> 4 & 0x0F) ^ (secondByte & 0x0F)) | ((firstByte << 4) ^ (secondByte & 0xF0)));
    }

    @Override
    public String toString() {
        return "Alternate Xor Key Generator";
    }
}
