package com.example.demo.States;

import com.example.demo.Heuristics.IHeuristic;

import java.awt.*;
import java.util.ArrayList;

public class State implements Comparable<State> {

    private final String numbers;
    private double costG;
    private double totalCost;
    private final State parent;
    private int emptySlot;


    public State(String numbers, State parent) {
        this.numbers = numbers;
        this.parent = parent;
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

    public State(String numbers, State parent, double costG, double heuristicCost) {
        long startTime = System.nanoTime();
        this.numbers = numbers;
        this.parent = parent;
        this.costG = costG;
        this.totalCost = costG + heuristicCost;
        detectEmptySlot();
        long endTime = System.nanoTime();
    //    System.out.println("state constructor =" + ((endTime - startTime) / 1000));
    }


    public ArrayList<String> generateAdjacent() {
        ArrayList<String> adjacent = new ArrayList<>();
        long startTime = System.nanoTime();
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
      //  System.out.println("generateAdjacent = " + (((System.nanoTime() - startTime) / 1000)));
        return adjacent;

    }

    public State getParent() {
        return parent;
    }


    public String getNumbers() {
        return numbers;
    }

    private void detectEmptySlot() {
      //  long startTime = System.nanoTime();
        for (int i = 0; i < 9; i++) {
            if ((int) numbers.charAt(i) == '0') {
                emptySlot = i;
                break;
            }
        }
     //   System.out.println("DetectEmptySlot = " + (((System.nanoTime() - startTime) / 1000)));
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