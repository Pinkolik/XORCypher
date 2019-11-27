package com.github.pinkolik.lab3.task1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.UnsupportedEncodingException;

import static com.github.pinkolik.lab3.common.crypto.CryptoHelper.*;

public class Controller {

    private static final String DEFAULT_CHARSET = "windows-1251";

    @FXML
    private TextField keyTextField;

    @FXML
    private TextField fittingKeyTextField;

    @FXML
    private TextArea decryptedTextArea;

    @FXML
    private TextArea encryptedTextArea;

    @FXML
    public void initialize() {

    }

    @FXML
    private void onEncodeButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        encryptedTextArea.clear();
        String key = keyTextField.getText();
        String message = decryptedTextArea.getText();
        encryptedTextArea.setText(bytesToHexString(encrypt(hexStringToBytes(key), message.getBytes(DEFAULT_CHARSET))));
    }

    @FXML
    private void onDecodeButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        decryptedTextArea.clear();
        String key = keyTextField.getText();
        String message = encryptedTextArea.getText();
        decryptedTextArea.setText(new String(decrypt(hexStringToBytes(key), hexStringToBytes(message)), DEFAULT_CHARSET));
    }

    @FXML
    private void findKeyButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        fittingKeyTextField.clear();
        String encryptedMessage = encryptedTextArea.getText();
        String decryptedMessage = decryptedTextArea.getText();
        fittingKeyTextField.setText(
                bytesToHexString(findFittingKey(hexStringToBytes(encryptedMessage), decryptedMessage.getBytes(DEFAULT_CHARSET))));
    }
}
