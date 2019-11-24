package com.github.pinkolik.lab3.common.keygen;

/**
 * Created by IntelliJ IDEA.
 * User: mrpin
 * Date: 11/24/2019
 * Time: 1:52 PM
 */
public final class KeyGeneratorStorage {

    private static final AbstractKeyGenerator[] KEY_GENERATORS =
            new AbstractKeyGenerator[] {new AlternateXorKeyGenerator(), new PIKeyGenerator()};

    public static AbstractKeyGenerator[] getKeyGenerators() {
        return KEY_GENERATORS;
    }
}
