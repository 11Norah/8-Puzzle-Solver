package com.example.demo.Heuristics;

import com.example.demo.States.GoalState;

import java.awt.*;
import java.util.HashMap;

public class Manhattan implements IHeuristic {
    private double heuristic;
    private final HashMap<Integer, Point> goal;

    public Manhattan(String numbers, GoalState goal) {
        this.goal = goal.getGoal();
        heuristic = 0;
        generateHeuristic(numbers);
    }

    @Override
    public Manhattan setHeuristic(String numbers) {
        heuristic = 0;
        generateHeuristic(numbers);
        return this;
    }

    @Override
    public double getHeuristic() {
        return heuristic;
    }


    private void generateHeuristic(String numbers) {
        int number;
        Point goalIndex;
        for (int i = 0; i < 9; i++) {
            number = numbers.charAt(i);
            int xIndex = i / 3;
            int yIndex = i % 3;
            goalIndex = goal.get(number - '0');
            heuristic += manhattanDistance(xIndex, yIndex, goalIndex.x, goalIndex.y);
        }
    }


    private double manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
