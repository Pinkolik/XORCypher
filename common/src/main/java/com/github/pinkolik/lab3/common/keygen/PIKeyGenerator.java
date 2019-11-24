package com.github.pinkolik.lab3.common.keygen;

import com.github.pinkolik.lab3.common.keygen.util.PIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 1 байт - смещение от точки числа пи в символах шестнадцетеричной системы
 */
public class PIKeyGenerator extends AbstractKeyGenerator {

    @Override
    public byte[] generateEquivalentKey(final byte[] originalKey) {
        final byte key = (byte) new Random().nextInt(128);
        final List<Byte> result = new ArrayList<>();
        result.add(key);
        for (byte originalKeyByte : originalKey) {
            byte lowByte = (byte) (originalKeyByte & 0x0F);
            byte highByte = (byte) (originalKeyByte >>> 4 & 0x0F);
            byte lowByteOffset = PIUtil.findFirstAppearance(key, lowByte);
            byte highByteOffset = PIUtil.findFirstAppearance(key, highByte);
            result.add(lowByteOffset);
            result.add(highByteOffset);
        }
        final byte[] resultArray = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    @Override
    public byte[] decryptKey(final byte[] encryptedKey) {
        final byte key = encryptedKey[0];
        final List<Byte> result = new ArrayList<>();
        for (byte i = 1; i < encryptedKey.length - 1; i += 2) {
            byte lowByteOffset = encryptedKey[i];
            byte highByteOffset = encryptedKey[i + 1];
            byte lowByte = PIUtil.computePiNthDigit(key + lowByteOffset);
            byte highByte = (byte) (PIUtil.computePiNthDigit(key + highByteOffset) << 4);
            result.add((byte) (lowByte | highByte));
        }
        final byte[] resultArray = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    @Override
    public String toString() {
        return "PI Key Generator";
    }
}
