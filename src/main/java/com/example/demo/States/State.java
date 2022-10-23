package com.example.demo.States;

import java.util.ArrayList;

public class State implements Comparable<State> {

    private String numbers;
    private double costG;
    private double totalCost;
    private State parent;
    private int emptySlot;
    private int depth;


    public State(String numbers, State parent, int depth) {
        this.numbers = numbers;
        this.parent = parent;
        this.depth = depth;
        detectEmptySlot();
    }

    public void setCostG(double costG) {
        this.costG = costG;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setEmptySlot(int emptySlot) {
        this.emptySlot = emptySlot;
    }

    public int getDepth() {
        return depth;
    }

    public State(String numbers, State parent, double costG, double heuristicCost) {
        this.numbers = numbers;
        this.parent = parent;
        this.costG = costG;
        this.totalCost = costG + heuristicCost;
        detectEmptySlot();
    }

    public State(double costG) {
        this.costG = costG;
    }

    public ArrayList<String> generateAdjacent() {
        ArrayList<String> adjacent = new ArrayList<>();
        int[] adj = new int[]{1, -1, 3, -3};
        char numToSwap;
        int tempInd;
        String adjacentState;
        for (int i = 0; i < 4; i++) {
            tempInd = emptySlot + adj[i];
            if (tempInd >= 0 && tempInd < 9 && !((adj[i] == 1 || adj[i] == -1) && (((tempInd) / 3) != (emptySlot / 3)))) {
                numToSwap = this.numbers.charAt(tempInd);
                adjacentState = numbers.substring(0, emptySlot) + numToSwap + numbers.substring(emptySlot + 1);
                adjacentState = adjacentState.substring(0, tempInd) + '0' + adjacentState.substring(tempInd + 1);
                adjacent.add(adjacentState);
            }
        }
        return adjacent;

    }

    public State getParent() {
        return parent;
    }


    public String getNumbers() {
        return numbers;
    }

    private void detectEmptySlot() {
        for (int i = 0; i < 9; i++) {
            if ((int) numbers.charAt(i) == '0') {
                emptySlot = i;
                break;
            }
        }
    }



    public boolean isGoalState() {
        final String goal = "012345678";
        return numbers.equals(goal);
    }

    public double getCostG() {
        return costG;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public int compareTo(State o) {
        return Double.compare(this.totalCost, o.totalCost);

    }
}