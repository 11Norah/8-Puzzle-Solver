package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label zero ;

    @FXML
    private Label one ;

    @FXML
    private Label two ;
    @FXML
    private Label three ;
    @FXML
    private Label four ;
    @FXML
    private Label five;
    @FXML
    private Label six;

    @FXML
    private Label seven;

    @FXML
    private Label eight ;

    @FXML
    protected void onHelloButtonClick() {
        zero.setText(""); //first num displayed from algorithm

        one.setText("1");
        two.setText("2");
        three.setText("3");
        four.setText("4");
        five.setText("5");
        six.setText("6");
        seven.setText("7");
        eight.setText("8");
        ;
    }
}