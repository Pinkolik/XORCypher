package com.github.pinkolik.lab3.task2.controller;

import com.github.pinkolik.lab3.common.crypto.CryptoHelper;
import com.github.pinkolik.lab3.common.keygen.AlternateXorKeyGenerator;
import com.github.pinkolik.lab3.common.keygen.IKeyGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.github.pinkolik.lab3.common.crypto.CryptoHelper.*;

public class Controller {

    @FXML
    public TextField keyTextField;

    @FXML
    public TextArea decryptedTextArea;

    @FXML
    public TextArea encryptedTextArea;

    @FXML
    public ComboBox keyGeneratorsComboBox;

    @FXML
    public void onEncodeButtonClicked(final MouseEvent mouseEvent) {

    }

    @FXML
    public void onDecodeButtonClicked(final MouseEvent mouseEvent) {

    }

    @FXML
    public void findKeyButtonClicked(final MouseEvent mouseEvent) {

    }

    @FXML
    public void onGenerateKeysButton(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        byte[] messageBytes = decryptedTextArea.getText().getBytes("windows-1251");
        byte[] originalKey = generateRandomKey(messageBytes);
        IKeyGenerator keyGenerator = new AlternateXorKeyGenerator();
        List<byte[]> generatedKeys = keyGenerator.generateEquivalentKeys(originalKey, 10);
        encryptedTextArea.clear();
        generatedKeys.forEach(k -> encryptedTextArea.appendText(bytesToHex(k) + "\n"));
    }
}
