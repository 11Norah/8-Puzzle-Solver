package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class Controller {
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
    private Button next;
    @FXML
    private Button previous;
    @FXML
    private Label seven;
    @FXML
    private ComboBox AlgorithmsCombo;
    @FXML
    private Label eight ;
    @FXML
    private TextField textfield;
    @FXML
    private Label cost;
    @FXML
    private Label runningTime;
    @FXML
    private Label searchDepth;
    @FXML
    private Label nodesExpanded;
    @FXML
     protected void display(){
        // populate algorithm combo box with item choices.
        AlgorithmsCombo.getItems().setAll("DFS", "BFS", "A*");
    }

    private int[] arr=new int[9];

    private int last_step=1;
    void display_values(){
        nodesExpanded.setText("123");//values from algorithm is applied
        runningTime.setText("17118");
        searchDepth.setText("1771");
        cost.setText("118");
    }
    @FXML
    protected  void next(){




        //at the last step,there is a flag to allow the program take the values needed from the algorithm function
       //then displaying these values
        if(last_step==1){next.setDisable(true); display_values();}
    }
    @FXML
    protected void previous(){

    }
    @FXML
    protected void SetInitialState() {
         System.out.println(AlgorithmsCombo.getValue());
        //setting initial array to be displayed
        String[] array= ( textfield.getText().split(","));
        for(int i=0;i< array.length;i++){
            arr[i]=Integer.valueOf(array[i]);
        }
        textfield.setText("");
        zero.setText(String.valueOf(arr[0])); //first num displayed from algorithm

        one.setText(String.valueOf(arr[1]));
        two.setText(String.valueOf(arr[2]));
        three.setText(String.valueOf(arr[3]));
        four.setText(String.valueOf(arr[4]));
        five.setText(String.valueOf(arr[5]));
        six.setText(String.valueOf(arr[6]));
        seven.setText(String.valueOf(arr[7]));
        eight.setText(String.valueOf(arr[8]));


        //enabling next button
        next.setDisable(false);
    }
}