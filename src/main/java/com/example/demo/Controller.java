package com.example.demo;

import com.example.demo.Algorithms.AStarSolver;
//import com.example.demo.Algorithms.bfs;
//import com.example.demo.Algorithms.dfs;
import com.example.demo.Heuristics.Euclidean;
import com.example.demo.Heuristics.IHeuristic;
//import com.example.demo.Heuristics.Manhattan;
import com.example.demo.Heuristics.Manhattan;
import com.example.demo.States.GoalState;
import com.example.demo.States.State;
import javafx.fxml.FXML;
import javafx.scene.control.*;
//import com.example.demo.Algorithms.puzzleSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class Controller {
    ArrayList<String> result;
    ArrayList<State> exploredd;
    long startTime,endTime,totalTime;
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
        AlgorithmsCombo.getItems().setAll("DFS", "BFS", "A*(Manhattan)","A*(Euclidean)");
    }
    private String state;
    public boolean checkIfSolvable(String  initialState) {
        int inversions = 0;
        for (int i = 0; i < 8; i++)
            for (int j = i + 1; j < 9; j++)
                if (initialState.charAt(i) != '0' && initialState.charAt(j) != '0' && (int)(initialState.charAt(i)) > (int)initialState.charAt(j))
                    inversions++;
        return inversions % 2 == 0;
    }

    private void alert_error(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input ");
        alert.setHeaderText("Try again !");
        alert.setContentText(message);
        alert.show();
    }

    /*private boolean parse_input(){
        if(Pattern.matches("(\\d|,)*",textfield.getText())){
            System.out.println(AlgorithmsCombo.getValue());
            //setting initial array to be displayed
            if(textfield.getText().contains(",,")){alert_error("Two commas aren't allowed"); return false;}
            else if(textfield.getText().length()>17){alert_error("9 numbers only "); return false;}
            else{
            String[] array= ( textfield.getText().split(","));
            for(int i=0;i< array.length;i++){
                arr[i]=Integer.valueOf(array[i]);
            }return true;}}
        //end of checking if the input contain unknown character
        else{
            alert_error("Numbers and commas only are allowed");
            return false;
        }

    }*/
    private boolean inputValidation(){

        //if(!textfield.getText().isEmpty() && Pattern.matches("([0-8],){8}[0-8]",textfield.getText())){
        if(!textfield.getText().isEmpty() && Pattern.matches("([0-8]){9}",textfield.getText())){
            System.out.println("correct");
            this.state=textfield.getText();
            //checking repetition
            for(int i=0;i<9;i++){
                char searching=this.state.charAt(i);
                for(int j=0;j<9;j++){
                    if(j!=i){
                        if(this.state.charAt(j)==searching){
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
        else{alert_error("Invalid Initial state ! Enter it as following (ex :123468705)");
            System.out.println("incorrect");
            return false;
        }
    }

    void display_values(){
        nodesExpanded.setText(String.valueOf(this.exploredd.size()));//values from algorithm is applied
        runningTime.setText(String.valueOf(this.totalTime));
        searchDepth.setText(String.valueOf(this.result.size()-1));
        cost.setText(String.valueOf(this.result.size()-1));
    }

    void display_array(String arr){
        zero.setVisible(true);
        one.setVisible(true);
        two.setVisible(true);
        three.setVisible(true);
        four.setVisible(true);
        five.setVisible(true);
        six.setVisible(true);
        seven.setVisible(true);
        eight.setVisible(true);
        int zero_index=-1;
        for(int i=0;i<arr.length();i++){
            if(arr.charAt(i)=='0'){zero_index=i; break;}

        }
        switch (zero_index){
            case 0: zero.setVisible(false); break;
            case 1: one.setVisible(false);  break;
            case 2: two.setVisible(false);  break;
            case 3: three.setVisible(false);  break;
            case 4: four.setVisible(false);   break;
            case 5: five.setVisible(false);  break;
            case 6: six.setVisible(false);   break;
            case 7: seven.setVisible(false);  break;
            case 8: eight.setVisible(false);  break;
        }
        zero.setText(String.valueOf(arr.charAt(0))); //first num displayed from algorithm

        one.setText(String.valueOf(arr.charAt(1)));
        two.setText(String.valueOf(arr.charAt(2)));
        three.setText(String.valueOf(arr.charAt(3)));
        four.setText(String.valueOf(arr.charAt(4)));
        five.setText(String.valueOf(arr.charAt(5)));
        six.setText(String.valueOf(arr.charAt(6)));
        seven.setText(String.valueOf(arr.charAt(7)));
        eight.setText(String.valueOf(arr.charAt(8)));

    }
    int counter=0;
    @FXML
    protected  void next(){
        this.counter++;
        if(this.counter< this.result.size()){
            //System.out.println("nexxxxxxxxxxxxxxxt "+this.counter);
            display_array(this.result.get(this.counter));
            // next.setDisable(false);
        }
        //at the last step,there is a flag to allow the program take the values needed from the algorithm function
        //then displaying these values

        if(this.counter==this.result.size()-1){
            next.setDisable(true); display_values();}
        if(this.counter!=0){previous.setDisable(false);}

    }
    @FXML
    protected void previous(){
        this.counter--;

        if(this.counter< this.result.size() && this.counter>-1){

            display_array(this.result.get(this.counter));}
        if(this.counter==0){previous.setDisable(true);}
    }
    @FXML
    protected void SetInitialState() {

        //if input is correct and
        // array is inserted correctly,
        // start sending it to functions and display initial state
        this.counter=0;
        if(inputValidation()) {
            if(checkIfSolvable(state)) {
                textfield.setText("");
                String alg = AlgorithmsCombo.getValue().toString();
                if (alg.equals("BFS")) {
                    System.out.println("ana gwa");
                    // puzzleSolver bfs_result = new bfs();
                    System.out.println(this.state.length());
                    startTime = System.nanoTime()/(long)pow(10,3);
                    // this.result = bfs_result.solve(.........);
                    endTime   = System.nanoTime()/(long)pow(10,3);
                    this.totalTime = endTime - startTime;
                    System.out.println("Size result :" + this.result.size());

                }
                else if (alg.equals("A*(Manhattan)")) {
                    System.out.println("ana gwa");
                    GoalState goal=new GoalState();

                    IHeuristic manh = new Manhattan(state,goal);
                    AStarSolver a_result = new AStarSolver(manh);
                    System.out.println(this.state.length());
                    startTime = System.nanoTime()/(long)pow(10,3);
                    this.result = a_result.solve(state);
                    endTime   = System.nanoTime()/(long)pow(10,3);
                    this.totalTime = endTime - startTime;
                    Collections.reverse(this.result);
                    this.exploredd=a_result.explored;
                    //System.out.println("Size result :" + this.result.size());

                }
                else if (alg.equals("A*(Euclidean)")) {
                    System.out.println("ana gwa");
                    GoalState goal=new GoalState();
                    IHeuristic Euc = new Euclidean(state,goal);
                    AStarSolver a_result = new AStarSolver(Euc);
                    //calculating running time
                    startTime = System.nanoTime()/(long)pow(10,3);
                    this.result = a_result.solve(state);
                    endTime   = System.nanoTime()/(long)pow(10,3);
                    this.totalTime = endTime - startTime;
                    Collections.reverse(this.result);
                    this.exploredd=a_result.explored;

                    System.out.println("Size result :" + this.result.size());

                } else if (alg.equals("DFS")) {
                    System.out.println("ana gwa");
                    // puzzleSolver dfs_result = new dfs();
                    System.out.println(this.state.length());
                    startTime = System.nanoTime()/(long)pow(10,3);
                    //this.result = dfs_result.solve(state);
                    endTime   = System.nanoTime()/(long)pow(10,3);
                    this.totalTime = endTime - startTime;
                    System.out.println("Size result :" + this.result.size());
                }
                System.out.println("tl3t");
                display_array(this.state);

                //enabling next button
                next.setDisable(false);
            }else {alert_error("Insolvable initial state");}

        }}
}
