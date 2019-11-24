package com.github.pinkolik.lab3.common.keygen;

/**
 * Created by IntelliJ IDEA.
 * User: mrpin
 * Date: 11/24/2019
 * Time: 1:52 PM
 */
public final class KeyGeneratorStorage {

    private static final IKeyGenerator[] KEY_GENERATORS = new IKeyGenerator[] {new AlternateXorKeyGenerator()};

    public static IKeyGenerator[] getKeyGenerators() {
        return KEY_GENERATORS;
    }
}
