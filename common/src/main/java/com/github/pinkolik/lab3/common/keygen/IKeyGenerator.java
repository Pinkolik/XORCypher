package com.github.pinkolik.lab3.common.keygen;

import java.util.List;

public interface IKeyGenerator {

    List<byte[]> generateEquivalentKeys(byte[] originalKey, int count);

    byte[] generateEquivalentKey(byte[] originalKey);

    byte[] decryptKey(byte[] encryptedKey);
}
