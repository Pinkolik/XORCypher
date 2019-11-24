package com.github.pinkolik.lab3.common.keygen;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractKeyGenerator {

    public List<byte[]> generateEquivalentKeys(byte[] originalKey, int count) {
        return IntStream.range(0, count).mapToObj(i -> generateEquivalentKey(originalKey)).collect(Collectors.toList());
    }

    public abstract byte[] generateEquivalentKey(byte[] originalKey);

    public abstract byte[] decryptKey(byte[] encryptedKey);
}
