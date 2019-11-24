package com.github.pinkolik.lab3.common.keygen;


import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class AlternateXorKeyGenerator implements IKeyGenerator {

    @Override
    public List<byte[]> generateEquivalentKeys(final byte[] originalKey, final int count) {
        return IntStream.range(0, count).mapToObj(i -> generateEquivalentKey(originalKey)).collect(Collectors.toList());
    }

    @Override
    public byte[] generateEquivalentKey(final byte[] originalKey) {
        final byte key = (byte) new Random().nextInt();
        final byte[] result = new byte[originalKey.length + 1];
        result[0] = key;
        boolean isOdd = key % 2 == 1;
        for (int i = 0; i < originalKey.length; i++) {
            byte originalByte = originalKey[i];
            byte resultByte;
            if (originalByte % 2 == 0) {
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

    public byte[] decryptKey(final byte[] encryptedKey) {
        final byte key = encryptedKey[0];
        final byte[] result = new byte[encryptedKey.length - 1];
        boolean isOdd = key % 2 == 1;
        for (int i = 1; i < encryptedKey.length; i++) {
            byte encryptedByte = encryptedKey[i];
            byte resultByte;
            if (encryptedByte % 2 == 0) {
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
        return (byte) (((firstByte >>> 4) ^ (secondByte & 0x0F)) | ((firstByte << 4) ^ (secondByte & 0xF0)));
    }

    @Override
    public String toString() {
        return "Alternate Xor Key Generator";
    }
}
