package com.example.demo.Heuristics;

import java.awt.*;
import java.util.HashMap;

public class Euclidean implements IHeuristic {
    private double heuristic;
    private final HashMap<Integer, Point> goal;

    public Euclidean(int[] numbers) {
        goal = new HashMap<>();
        generateGoalState();
        heuristic = 0;
        int[][] matrix = getMatrix(numbers);
        generateHeuristic(matrix);
    }

    @Override
    public Euclidean setHeuristic(int[] numbers) {
        heuristic = 0;
        int[][] matrix = getMatrix(numbers);
        generateHeuristic(matrix);
        return this;
    }

    @Override
    public double getHeuristic() {
        return heuristic;
    }

    private void generateHeuristic(int[][] matrix) {
        int number;
        Point goalIndex;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                number = matrix[i][j];
                goalIndex = goal.get(number);
                heuristic += euclideanDistance(i, j, goalIndex.x, goalIndex.y);
            }
        }
    }


    private void generateGoalState() {
        for (int i = 0; i < 9; i++) {
            Point point = new Point(i / 3, i % 3);
            goal.put(i, point);
        }
    }

    private double euclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }


    private Point getIndexInMatrix(int index) {
        int x = index / 3;
        int y = index % 3;
        return new Point(x, y);
    }

    private int[][] getMatrix(int[] numbers) {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 9; i++) {
            Point index = getIndexInMatrix(i);
            matrix[index.x][index.y] = numbers[i];
        }
        return matrix;
    }

}
