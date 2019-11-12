package com.github.pinkolik.lab3.common.keygen;


import java.util.List;
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
public class AlternateXorKeyGenerator implements IKeyGenerator {

    public List<byte[]> generateEquivalentKeys(final byte[] originalKey, final int count) {
        return null;
    }

    public byte[] decryptKey(final byte[] encryptedKey) {
        return new byte[0];
    }

    private byte[] generateEquivalentKey(final byte[] originalKey) {
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

                }
            }
            else {
                if (isOdd) {

                }
                else {

                }
            }
        }
        return result;
    }

    private byte reverseXor(final byte firstByte, final byte secondByte) {
        return 0;
    }
}
