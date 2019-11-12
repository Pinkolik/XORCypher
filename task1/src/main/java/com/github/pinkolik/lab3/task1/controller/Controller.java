package com.github.pinkolik.lab3.task1.controller;

import com.github.pinkolik.lab3.common.crypto.CryptoHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.UnsupportedEncodingException;

public class Controller {

    @FXML
    public TextField keyTextField;

    @FXML
    public TextField fittingKeyTextField;

    @FXML
    public TextArea decryptedTextArea;

    @FXML
    public TextArea encryptedTextArea;

    @FXML
    public Button encodeButton;

    @FXML
    public Button decodeButton;

    @FXML
    public Button findKeyButton;

    @FXML
    public void initialize() {

    }

    @FXML
    public void onEncodeButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        encryptedTextArea.clear();
        String key = keyTextField.getText();
        String message = decryptedTextArea.getText();
        encryptedTextArea.setText(CryptoHelper.encrypt(key, message));
    }

    @FXML
    public void onDecodeButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        decryptedTextArea.clear();
        String key = keyTextField.getText();
        String message = encryptedTextArea.getText();
        decryptedTextArea.setText(CryptoHelper.decrypt(key, message));
    }

    @FXML
    public void findKeyButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        fittingKeyTextField.clear();
        String encryptedMessage = encryptedTextArea.getText();
        String decryptedMessage = decryptedTextArea.getText();
        fittingKeyTextField.setText(CryptoHelper.findFittingKey(encryptedMessage, decryptedMessage));
    }
}
