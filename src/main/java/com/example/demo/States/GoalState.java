package com.example.demo.States;

import java.awt.*;
import java.util.HashMap;

public class GoalState {
    private HashMap<Integer, Point> goal;

    public GoalState() {
        goal = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Point point = new Point(i / 3, i % 3);
            goal.put(i, point);
        }
    }

    public HashMap<Integer, Point> getGoal() {
        return this.goal;
    }
}
