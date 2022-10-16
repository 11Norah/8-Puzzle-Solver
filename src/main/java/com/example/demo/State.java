package com.example.demo;
import com.example.demo.Heuristics.IHeuristic;

import java.awt.*;
import java.util.ArrayList;

public class State implements Comparable<State> {
    private final int[][] matrix;
    private final int[] xChange = {1, -1, 0, 0};
    private final int[] yChange = {0, 0, 1, -1};
    private final int[] numbers;
    private final ArrayList<int[]> adjacent;
    private double heuristicCost;
    private final State parent;
    private int emptySlot;



    public State(int[] numbers, State parent, IHeuristic heuristic) {
        this.numbers = numbers;
        this.matrix = new int[3][3];
        adjacent = new ArrayList<>();
        this.parent = parent;
        heuristicCost = heuristic.getHeuristic();
        getMatrix();
        detectEmptySlot();
        generateAdjacent();
    }

    public State(int[] numbers, State parent) {
        this.numbers = numbers;
        this.matrix = new int[3][3];
        adjacent = new ArrayList<>();
        this.parent = parent;
        getMatrix();
        detectEmptySlot();
        generateAdjacent();
    }

    private boolean check(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private void copy(int[][] matrix1, int[][] matrix2) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matrix2[i], 0, matrix1[i], 0, 3);
        }
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    private void generateAdjacent() {
        Point emptySlotInMatrix = getIndexInMatrix(this.emptySlot);
        for (int i = 0; i < 4; i++) {
            int tempX = emptySlotInMatrix.x + xChange[i];
            int tempY = emptySlotInMatrix.y + yChange[i];
            if (check(tempX, tempY)) {
                int[][] tempNumbers = new int[3][3];
                copy(tempNumbers, this.matrix);
                tempNumbers[tempX][tempY] = 0;
                tempNumbers[emptySlotInMatrix.x][emptySlotInMatrix.y] = this.matrix[tempX][tempY];
                adjacent.add(switchMatrixToArray(tempNumbers));
            }
        }
    }

    public State getParent() {
        return parent;
    }

    private int[] switchMatrixToArray(int[][] matrix) {
        int[] res = new int[9];
        int c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[c++] = matrix[i][j];
            }
        }
        return res;

    }

    public int[] getNumbers() {
        return numbers;
    }

    private void detectEmptySlot() {
        for (int i = 0; i < 9; i++) {
            if (numbers[i] == 0) {
                emptySlot = i;
                break;
            }
        }
    }


    public ArrayList<int[]> getAdjacent() {
        return adjacent;
    }


    private Point getIndexInMatrix(int index) {
        int x = index / 3;
        int y = index % 3;
        return new Point(x, y);
    }

    private void getMatrix() {
        for (int i = 0; i < 9; i++) {
            Point index = getIndexInMatrix(i);
            matrix[index.x][index.y] = numbers[i];
        }
    }

    public boolean isGoalState() {
        int turn = 0;
        for(int num : numbers) {
            if(turn != num) return false;
            turn++;
        }
        return true;
    }

    @Override
    public int compareTo(State o) {
        return Double.compare(this.heuristicCost, o.heuristicCost);

    }
}
