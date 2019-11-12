package com.github.pinkolik.lab3.task1;

import com.github.pinkolik.lab3.task1.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(final String[] args) {launch(args);}

    @Override
    public void start(final Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main_form.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Лабораторная работа №3, задание 1");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
