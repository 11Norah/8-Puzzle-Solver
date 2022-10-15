package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.regex.Pattern;

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
    private void alert_error(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input ");
        alert.setHeaderText("Try again !");
        alert.setContentText(message);
        alert.show();
    }
    private boolean inputValidation(){

        if(!textfield.getText().isEmpty() && Pattern.matches("([0-8],){8}[0-8]",textfield.getText())){
            System.out.println("correct");
            //checking repetition
            for(int i=0;i<9;i++){
                int searching=arr[i];
                for(int j=0;j<9;j++){
                    if(j!=i){
                    if(arr[j]==searching){
                        alert_error("Repeated numbers are not allowed in initial state of 8-puzzle");
                        return false; //error repeated number found
                         }}
                }
            }
            //check if algorithm is chosen
            if(AlgorithmsCombo.getValue()==null){
                alert_error("Choose algorithm ,please"); return false;}
            return true;
        }
        else{alert_error("Invalid Initial state ! Enter it as following (ex :1,2,3,4,6,8,7,0,5)");
            System.out.println("incorrect");
            return false;
        }
    }
 

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

        //if input is correct
        // array will be set ,start sending it to functions and display initial state

        if(inputValidation()) {
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

        }}
}
