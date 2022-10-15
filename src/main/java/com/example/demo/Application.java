package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("puzzle-gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 590);
        stage.setTitle("8-Puzzle Solver");

        stage.getIcons().add(new Image("https://www.nsbpictures.com/wp-content/uploads/2020/08/COLORFULL-GRADIENTS-2.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}