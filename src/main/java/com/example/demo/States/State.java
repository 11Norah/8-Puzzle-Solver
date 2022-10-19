package com.example.demo.States;

import com.example.demo.Heuristics.IHeuristic;

import java.awt.*;
import java.util.ArrayList;

public class State implements Comparable<State> {

    private final String numbers;
    private final ArrayList<String> adjacent;
    private final double heuristicCost;
    private double costG;
    private double totalCost;
    private final State parent;
    private int emptySlot;


    public State(String numbers, State parent, IHeuristic heuristic, double costG) {
        this.numbers = numbers;
        this.adjacent = new ArrayList<>();
        this.parent = parent;
        this.heuristicCost = heuristic.getHeuristic();
        this.costG = costG;
        this.totalCost = costG + heuristicCost;
        detectEmptySlot();
        generateAdjacent();
    }

    private boolean check(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private String writeState(String state, int emptyIndex, int indexToSwap) {
        char swapped = state.charAt(indexToSwap);
        String newState = "";
        for (int i = 0; i <= 8; i++) {
            //If current index is empty, replace swapped with it
            if (i == emptyIndex) {
                newState += swapped;
            }
            //If current index is swapped char, reverse it with empty one
            else if (i == indexToSwap) {
                newState += "0";
            }
            //Otherwise, place the character at the current index at the same index (does not move)
            else {
                newState += state.charAt(i);
            }
        }

        return newState;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    private void generateAdjacent() {
        int[] adj = new int[]{1, -1, 3, -3};
        char numToSwap;
        int tempInd;
        String adjacentState;
        for (int i = 0; i < 4; i++) {
            tempInd = emptySlot + adj[i];
            if (tempInd >= 0 && tempInd < 9 && !((adj[i] == 1 || adj[i] == -1 )&&(((tempInd) / 3) != (emptySlot / 3)))) {
                numToSwap = this.numbers.charAt(tempInd);
                adjacentState = numbers.substring(0, emptySlot) + numToSwap + numbers.substring(emptySlot + 1);
                adjacentState = adjacentState.substring(0, tempInd) + '0' + adjacentState.substring(tempInd + 1);
                this.adjacent.add(adjacentState);
            }
        }
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


    public ArrayList<String> getAdjacent() {
        return adjacent;
    }

    public boolean isGoalState() {
        char turn = '0';
        for (int i = 0; i < numbers.length(); i++) {
            if (turn != numbers.charAt(i)) return false;
            turn++;
        }
        return true;
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