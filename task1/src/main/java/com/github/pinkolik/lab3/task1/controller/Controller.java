package com.github.pinkolik.lab3.task1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.UnsupportedEncodingException;

import static com.github.pinkolik.lab3.common.crypto.CryptoHelper.*;

public class Controller {

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
    public void onEncodeButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        encryptedTextArea.clear();
        String key = keyTextField.getText();
        String message = decryptedTextArea.getText();
        encryptedTextArea.setText(bytesToHexString(encrypt(hexStringToBytes(key), message.getBytes("windows-1251"))));
    }

    @FXML
    public void onDecodeButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        decryptedTextArea.clear();
        String key = keyTextField.getText();
        String message = encryptedTextArea.getText();
        decryptedTextArea.setText(new String(decrypt(hexStringToBytes(key), message.getBytes("windows-1251")), "windows-1251"));
    }

    @FXML
    public void findKeyButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        fittingKeyTextField.clear();
        String encryptedMessage = encryptedTextArea.getText();
        String decryptedMessage = decryptedTextArea.getText();
        fittingKeyTextField.setText(
                bytesToHexString(findFittingKey(hexStringToBytes(encryptedMessage), decryptedMessage.getBytes("windows-1251"))));
    }
}
