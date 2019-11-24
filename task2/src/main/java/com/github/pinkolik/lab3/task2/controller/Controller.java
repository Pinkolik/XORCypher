package com.github.pinkolik.lab3.task2.controller;

import com.github.pinkolik.lab3.common.crypto.CryptoHelper;
import com.github.pinkolik.lab3.common.keygen.IKeyGenerator;
import com.github.pinkolik.lab3.common.keygen.KeyGeneratorStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.github.pinkolik.lab3.common.crypto.CryptoHelper.*;

public class Controller {

    private static final String DEFAULT_CHARSET = "windows-1251";

    @FXML
    private TextField keyTextField;

    @FXML
    private TextArea decryptedTextArea;

    @FXML
    private TextArea encryptedTextArea;

    @FXML
    private ComboBox<IKeyGenerator> keyGeneratorsComboBox;

    @FXML
    public void onEncryptButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        byte[] messageBytes = decryptedTextArea.getText().getBytes(DEFAULT_CHARSET);
        byte[] encryptedKey = hexStringToBytes(keyTextField.getText());
        IKeyGenerator keyGenerator = keyGeneratorsComboBox.getValue();
        byte[] originalKey = keyGenerator.decryptKey(encryptedKey);
        byte[] encryptedMessage = encrypt(originalKey, messageBytes);
        encryptedTextArea.setText(bytesToHexString(encryptedMessage));
    }

    @FXML
    public void onDecryptButtonClicked(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        byte[] encryptedMessageBytes = hexStringToBytes(encryptedTextArea.getText());
        byte[] encryptedKey = hexStringToBytes(keyTextField.getText());
        IKeyGenerator keyGenerator = keyGeneratorsComboBox.getValue();
        byte[] originalKey = keyGenerator.decryptKey(encryptedKey);
        byte[] encryptedMessage = decrypt(originalKey, encryptedMessageBytes);
        decryptedTextArea.setText(new String(encryptedMessage, DEFAULT_CHARSET));
    }

    @FXML
    public void onGenerateKeysButton(final MouseEvent mouseEvent) throws UnsupportedEncodingException {
        byte[] messageBytes = decryptedTextArea.getText().getBytes(DEFAULT_CHARSET);
        byte[] originalKey = generateRandomKey(messageBytes);
        IKeyGenerator keyGenerator = keyGeneratorsComboBox.getValue();
        List<byte[]> generatedKeys = keyGenerator.generateEquivalentKeys(originalKey, 10);
        FileChooser fileChooser = new FileChooser();
        File saveFile = fileChooser.showSaveDialog(null);
        try {
            if (saveFile == null) {
                return;
            }
            saveKeys(saveFile, generatedKeys);
        }
        catch (IOException e) {
            showException(e);
        }
    }

    private void saveKeys(final File keysFile, final List<byte[]> generatedKeys) throws IOException {
        try (FileWriter fileWriter = new FileWriter(keysFile)) {
            for (byte[] key : generatedKeys) {
                fileWriter.write(CryptoHelper.bytesToHexString(key) + "\n");
            }
            fileWriter.flush();
        }
    }

    private void showException(final Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        keyGeneratorsComboBox.getItems().clear();
        keyGeneratorsComboBox.getItems().addAll(KeyGeneratorStorage.getKeyGenerators());
        keyGeneratorsComboBox.setValue(keyGeneratorsComboBox.getItems().get(0));
    }
}
