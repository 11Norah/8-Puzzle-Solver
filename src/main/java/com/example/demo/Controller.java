package com.example.demo;

import com.example.demo.Algorithms.AStarSolver;
//import com.example.demo.Algorithms.bfs;
//import com.example.demo.Algorithms.dfs;
import com.example.demo.Algorithms.AStarWrapper;
import com.example.demo.Algorithms.BfsSolver;
import com.example.demo.Algorithms.DfsSolver;
import com.example.demo.Heuristics.Euclidean;
import com.example.demo.Heuristics.IHeuristic;

import com.example.demo.Heuristics.Manhattan;
import com.example.demo.States.State;
import javafx.fxml.FXML;
import javafx.scene.control.*;
//import com.example.demo.Algorithms.puzzleSolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import static java.lang.Math.pow;

public class Controller {
    ArrayList<String> result;
    int exploredd;
    long startTime, endTime, totalTime;
    @FXML
    private Label zero;

    @FXML
    private Label one;

    @FXML
    private Label two;
    @FXML
    private Label three;
    @FXML
    private Label four;
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
    private Button view;
    @FXML
    private ComboBox AlgorithmsCombo;
    @FXML
    private Label eight;
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
    protected void display() {
        // populate algorithm combo box with item choices.
        AlgorithmsCombo.getItems().setAll("DFS", "BFS", "A*(Manhattan)", "A*(Euclidean)");
    }

    private String state;

    public boolean checkIfSolvable(String initialState) {
        int inversions = 0;
        for (int i = 0; i < 8; i++)
            for (int j = i + 1; j < 9; j++)
                if (initialState.charAt(i) != '0' && initialState.charAt(j) != '0' && (int) (initialState.charAt(i)) > (int) initialState.charAt(j))
                    inversions++;
        return inversions % 2 == 0;
    }

    private void alert_error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input ");
        alert.setHeaderText("Try again !");
        alert.setContentText(message);
        alert.show();
    }


    private boolean inputValidation() {

        //if(!textfield.getText().isEmpty() && Pattern.matches("([0-8],){8}[0-8]",textfield.getText())){
        if (!textfield.getText().isEmpty() && Pattern.matches("([0-8]){9}", textfield.getText())) {
            System.out.println("correct");
            this.state = textfield.getText();
            //checking repetition
            for (int i = 0; i < 9; i++) {
                char searching = this.state.charAt(i);
                for (int j = 0; j < 9; j++) {
                    if (j != i) {
                        if (this.state.charAt(j) == searching) {
                            alert_error("Repeated numbers are not allowed in initial state of 8-puzzle");
                            return false; //error repeated number found
                        }
                    }
                }
            }
            //check if algorithm is chosen
            if (AlgorithmsCombo.getValue() == null) {
                alert_error("Choose algorithm ,please");
                return false;
            }
            return true;
        } else {
            alert_error("Invalid Initial state ! Enter it as following (ex :123468705)");
            System.out.println("incorrect");
            return false;
        }
    }

    void display_values() {
        nodesExpanded.setText(String.valueOf(this.exploredd));//values from algorithm is applied
        runningTime.setText(String.valueOf(this.totalTime));
        searchDepth.setText(String.valueOf(this.result.size() - 1));
        cost.setText(String.valueOf(this.result.size() - 1));
    }

    void display_array(String arr) {
        zero.setVisible(true);
        one.setVisible(true);
        two.setVisible(true);
        three.setVisible(true);
        four.setVisible(true);
        five.setVisible(true);
        six.setVisible(true);
        seven.setVisible(true);
        eight.setVisible(true);
        int zero_index = -1;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.charAt(i) == '0') {
                zero_index = i;
                break;
            }

        }
        switch (zero_index) {
            case 0:
                zero.setVisible(false);
                break;
            case 1:
                one.setVisible(false);
                break;
            case 2:
                two.setVisible(false);
                break;
            case 3:
                three.setVisible(false);
                break;
            case 4:
                four.setVisible(false);
                break;
            case 5:
                five.setVisible(false);
                break;
            case 6:
                six.setVisible(false);
                break;
            case 7:
                seven.setVisible(false);
                break;
            case 8:
                eight.setVisible(false);
                break;
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

    int counter = 0;

    @FXML
    protected void next() {
        this.counter++;
        if (this.counter < this.result.size()) {
            //System.out.println("nexxxxxxxxxxxxxxxt "+this.counter);
            display_array(this.result.get(this.counter));
            // next.setDisable(false);
        }
        //at the last step,there is a flag to allow the program take the values needed from the algorithm function
        //then displaying these values

        if (this.counter == this.result.size() - 1) {
            next.setDisable(true);
        }
        if (this.counter != 0) {
            previous.setDisable(false);
        }

    }

    @FXML
    protected void previous() {
        this.counter--;

        if (this.counter < this.result.size() && this.counter > -1) {

            display_array(this.result.get(this.counter));
        }
        if (this.counter == 0) {
            previous.setDisable(true);
        }
    }

    int count = 0;

    @FXML
    protected void SetInitialState() {

        //if input is correct and
        // array is inserted correctly,
        // start sending it to functions and display initial state
        this.counter = 0;
        if (inputValidation()) {
            if (checkIfSolvable(state)) {
                textfield.setText("");
                String alg = AlgorithmsCombo.getValue().toString();
                view.setDisable(false);
                if (alg.equals("BFS")) {
                    System.out.println("ana gwa");
                    System.out.println(this.state.length());
                    BfsSolver solver = new BfsSolver();
                    startTime = System.nanoTime() / (long) pow(10, 3);
                    solver.solve(state);
                    endTime = System.nanoTime() / (long) pow(10, 3);
                    this.totalTime = endTime - startTime;
                    this.result = solver.getSolution();
                    System.out.println("Size result :" + this.result.size());
                    this.exploredd = solver.getExploredSize();

                } else if (alg.equals("A*(Manhattan)")) {
                    System.out.println("ana gwa");
                    IHeuristic manh = new Manhattan();
                    AStarWrapper a_result = new AStarWrapper(manh);
                    a_result.solve(this.state);
                    System.out.println(this.state.length());
                    startTime = System.nanoTime() / (long) pow(10, 3);
                    a_result.solve(this.state);
                    this.result = a_result.getSolution();
                    endTime = System.nanoTime() / (long) pow(10, 3);
                    this.totalTime = endTime - startTime;
                    Collections.reverse(this.result);
                    this.exploredd = a_result.getExploredSize();
                    //System.out.println("Size result :" + this.result.size());

                } else if (alg.equals("A*(Euclidean)")) {
                    System.out.println("ana gwa");
                    IHeuristic Euc = new Euclidean();
                    AStarWrapper a_result = new AStarWrapper(Euc);
                    a_result.solve(this.state);
                    //calculating running time
                    startTime = System.nanoTime() / (long) pow(10, 3);
                    a_result.solve(this.state);
                    this.result = a_result.getSolution();
                    endTime = System.nanoTime() / (long) pow(10, 3);
                    this.totalTime = endTime - startTime;
                    Collections.reverse(this.result);
                    this.exploredd = a_result.getExploredSize();
                    System.out.println("Size result :" + this.result.size());

                } else if (alg.equals("DFS")) {
                    System.out.println("ana gwa");
                    DfsSolver solver = new DfsSolver();

                    System.out.println(this.state.length());
                    startTime = System.nanoTime() / (long) pow(10, 3);
                    solver.solve(state);

                    endTime = System.nanoTime() / (long) pow(10, 3);
                    this.result = solver.getSolution();
                    this.totalTime = endTime - startTime;
                    System.out.println("Size result :" + this.result.size());
                    this.exploredd = solver.getExploredSize();
                }

                display_array(this.state);

                //enabling next button
                next.setDisable(false);
                //enabling values
                display_values();
            } else {
                alert_error("Insolvable initial state");
            }


            //path to goal written in text file
            try {
                count++;
                File myObj = new File("Example " + count + ".txt");
                FileWriter myWriter = new FileWriter("Example " + count + ".txt");
                for (int i = 0; i < this.result.size(); i++) {
                    String temp = this.result.get(i);
                    myWriter.write("{ " + temp.charAt(0) + ", " + temp.charAt(1) + ", " + temp.charAt(2) + ", " +
                            temp.charAt(3) + ", " + temp.charAt(4) + ", " + temp.charAt(5) +
                            ", " + temp.charAt(6) + ", " + temp.charAt(7) + ", " + temp.charAt(8) + "}");
                    myWriter.write("\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }


    @FXML
    protected void view() {
        AnchorPane secondaryLayout = new AnchorPane();
        // secondaryLayout.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        // secondaryLayout.setStyle("-fx-background-image: url('https://c4.wallpaperflare.com/wallpaper/651/896/1021/minimalist-purple-wallpaper-thumb.jpg')");
        secondaryLayout.setPrefSize(1530, 800);
        secondaryLayout.autosize();
        secondaryLayout.getBorder();
        secondaryLayout.maxHeight(1000);
        secondaryLayout.maxWidth(1000);

        ScrollPane sp = new ScrollPane();
        Group root = new Group();
        root.getChildren().addAll(sp);
        sp.setPrefSize(1530, 800);
        sp.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        Scene secondScene = new Scene(root, 1530, 800);
        for (int i = 0; i < result.size(); i++) {
            Rectangle rec = new Rectangle();
            rec.setWidth(80);
            rec.setHeight(110);
            rec.setX(700.0f);
            rec.setY(35.0f + 150 * i);
            rec.setFill(Color.TRANSPARENT);
            rec.setStroke(Color.BLACK);
            Label b1 = new Label();
            Label b2 = new Label();
            Label b3 = new Label();
            Label b4 = new Label();
            Label b5 = new Label();
            Label b6 = new Label();
            Label b7 = new Label();
            Label b8 = new Label();
            Label b9 = new Label();
            Button downbutton = new Button();
            downbutton.setMaxWidth(70);
            downbutton.setMaxHeight(70);
            downbutton.setStyle("-fx-background-color: -fx-mark-highlight-color, -fx-mark-color;-fx-background-insets: 0 0 -1 0, 0;-fx-padding: 0.25em;-fx-shape: \"M 0 -3.5 v 7 l 4 -3.5 z\";");
            downbutton.setTranslateX(740.0f);
            downbutton.setTranslateY(150.0f + 150 * i);
            downbutton.setRotate(90);
            Text text = new Text();
            text.setFill(Color.WHITE);
            Line l = new Line();
            l.setStartX(740);
            l.setStartY(110);
            l.setEndX(740);
            l.setEndY(150);
            b1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b1.setFont(Font.font("Forte"));
            b1.setFont(Font.font(20));
            b1.setPrefSize(20, 20);
            b1.setAlignment(Pos.BASELINE_CENTER);
            // b1.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");

            b2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b2.setFont(Font.font("Forte"));
            b2.setFont(Font.font(20));
            b2.setPrefSize(20, 20);
            b2.setAlignment(Pos.BASELINE_CENTER);
            //   b2.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b3.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b3.setFont(Font.font("Forte"));
            b3.setFont(Font.font(20));
            b3.setPrefSize(20, 20);
            b3.setAlignment(Pos.BASELINE_CENTER);
            // b3.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b4.setFont(Font.font("Forte"));
            b4.setFont(Font.font(20));
            b4.setPrefSize(20, 20);
            b4.setAlignment(Pos.BASELINE_CENTER);
            //   b4.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b5.setFont(Font.font("Forte"));
            b5.setFont(Font.font(20));
            b5.setPrefSize(20, 20);
            b5.setAlignment(Pos.BASELINE_CENTER);
            //   b5.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b6.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b6.setFont(Font.font("Forte"));
            b6.setFont(Font.font(20));
            b6.setPrefSize(20, 20);
            b6.setAlignment(Pos.BASELINE_CENTER);
            //  b6.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b7.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b7.setFont(Font.font("Forte"));
            b7.setFont(Font.font(20));
            b7.setPrefSize(20, 20);
            b7.setAlignment(Pos.BASELINE_CENTER);
            //  b7.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b8.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b8.setFont(Font.font("Forte"));
            b8.setFont(Font.font(20));
            b8.setPrefSize(20, 20);
            b8.setAlignment(Pos.BASELINE_CENTER);
            //  b8.setStyle("-fx-background-image: url('https://c0.wallpaperflare.com/preview/607/614/426/4k-wallpaper-background-brown-hardwood.jpg')");


            b9.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            b9.setFont(Font.font("Forte"));
            b9.setFont(Font.font(20));
            b9.setPrefSize(20, 20);
            b9.setAlignment(Pos.BASELINE_CENTER);


            b1.setText(String.valueOf(result.get(i).charAt(0)));
            b1.setTranslateX(705.0f);
            b1.setTranslateY(40.0f + 150 * i);
            if (result.get(i).charAt(0) == '0') {
                b1.setVisible(false);
            }

            b2.setText(String.valueOf(result.get(i).charAt(1)));
            b2.setTranslateX(730.0f);
            b2.setTranslateY(40.0f + 150 * i);
            if (result.get(i).charAt(1) == '0') {
                b2.setVisible(false);
            }

            b3.setText(String.valueOf(result.get(i).charAt(2)));
            b3.setTranslateX(755.0f);
            b3.setTranslateY(40.0f + 150 * i);
            if (result.get(i).charAt(2) == '0') {
                b3.setVisible(false);
            }

            b4.setText(String.valueOf(result.get(i).charAt(3)));
            b4.setTranslateX(705.0f);
            b4.setTranslateY(75.0f + 150 * i);
            if (result.get(i).charAt(3) == '0') {
                b4.setVisible(false);
            }

            b5.setText(String.valueOf(result.get(i).charAt(4)));
            b5.setTranslateX(730.0f);
            b5.setTranslateY(75.0f + 150 * i);
            if (result.get(i).charAt(4) == '0') {
                b5.setVisible(false);
            }

            b6.setText(String.valueOf(result.get(i).charAt(5)));
            b6.setTranslateX(755.0f);
            b6.setTranslateY(75.0f + 150 * i);
            if (result.get(i).charAt(5) == '0') {
                b6.setVisible(false);
            }

            b7.setText(String.valueOf(result.get(i).charAt(6)));
            b7.setTranslateX(705.0f);
            b7.setTranslateY(110.0f + 150 * i);
            if (result.get(i).charAt(6) == '0') {
                b7.setVisible(false);
            }

            b8.setText(String.valueOf(result.get(i).charAt(7)));
            b8.setTranslateX(730.0f);
            b8.setTranslateY(110.0f + 150 * i);
            if (result.get(i).charAt(7) == '0') {
                b8.setVisible(false);
            }

            b9.setText(String.valueOf(result.get(i).charAt(8)));
            b9.setTranslateX(755.0f);
            b9.setTranslateY(110.0f + 150 * i);
            if (result.get(i).charAt(8) == '0') {
                b9.setVisible(false);
            }
            secondaryLayout.getChildren().add(rec);

            secondaryLayout.getChildren().add(b1);
            secondaryLayout.getChildren().add(b2);
            secondaryLayout.getChildren().add(b3);
            secondaryLayout.getChildren().add(b4);
            secondaryLayout.getChildren().add(b5);
            secondaryLayout.getChildren().add(b6);
            secondaryLayout.getChildren().add(b7);
            secondaryLayout.getChildren().add(b8);
            secondaryLayout.getChildren().add(b9);
            secondaryLayout.getChildren().add(downbutton);

            sp.setContent(secondaryLayout);


        }


        //secondaryLayout.getChildren().add(rec);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.show();


    }
}
