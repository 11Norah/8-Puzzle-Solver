package com.example.demo.Heuristics;

public class Euclidean implements IHeuristic {
    private final int[][] goal;

    public Euclidean() {
        goal = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    }


    public double generateHeuristic(String numbers) {
        int number;
        int[] goalIndex;
        double heuristic = 0;
        for (int i = 0; i < 9; i++) {
            number = numbers.charAt(i);
            if (number != '0') {
                int xIndex = i / 3;
                int yIndex = i % 3;
                goalIndex = goal[number - '0'];
                heuristic += euclideanDistance(xIndex, yIndex, goalIndex[0], goalIndex[1]);
            }
        }

        return heuristic;
    }

    private double euclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }


}
