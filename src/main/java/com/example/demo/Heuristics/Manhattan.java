package com.example.demo.Heuristics;

public class Manhattan implements IHeuristic {
    private final int[][] goal;

    public Manhattan() {
        goal = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    }


    public double generateHeuristic(String numbers) {
        long start = System.nanoTime() ;
        int number;
        int[] goalIndex;
        double heuristic = 0;
        for (int i = 0; i < 9; i++) {
            number = numbers.charAt(i);
            int xIndex = i / 3;
            int yIndex = i % 3;
            goalIndex = goal[number - '0'];
            heuristic += manhattanDistance(xIndex, yIndex, goalIndex[0], goalIndex[1]);
        }
        long end = System.nanoTime() ;
      //  System.out.println("Heuristics generation = " + (((end - start) /1000)));
        return heuristic;
    }


    private double manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
