package com.example.demo.Algorithms;

import com.example.demo.Heuristics.IHeuristic;
import com.example.demo.State;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSolver implements puzzleSolver {

    IHeuristic heuristic;

    public AStarSolver(IHeuristic heuristic) {
        this.heuristic = heuristic;
    }

    boolean compareArrays(int[] arr1, int[] arr2) {
        for (int i = 0; i < 9; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    boolean notExplored(ArrayList<State> explored, int[] arr) {
        for (State i : explored) {
            if (compareArrays(i.getNumbers(), arr)) return false;
        }
        return true;
    }

    public boolean checkGoalState(int[] numbers) {
        int c = 0;
        for (int i : numbers) {
            if (i != c++) return false;
        }
        return true;
    }

    @Override
    public ArrayList<int[]> solve(int[] initialState) {
        ArrayList<State> explored = new ArrayList<>();
        ArrayList<int[]> res;
        PriorityQueue<State> fringe = new PriorityQueue<>();
        State state = new State(initialState, null);
        fringe.add(state);
        while (!fringe.isEmpty()) {
            State st = fringe.poll();
            explored.add(st);
            if (checkGoalState(st.getNumbers())) break;
            for (int[] i : st.getAdjacent()) {
                if (notExplored(explored, i)) fringe.add(new State(i, st, heuristic.setHeuristic(i)));
            }
        }
        res = getGoalPath(explored);
        return res;
    }


    private ArrayList<int[]> getGoalPath(ArrayList<State> explored) {
        ArrayList<int[]> res = new ArrayList<>();
        State parent;
        int i = explored.size() - 1;
        while (i >= 0) {
            res.add(explored.get(i).getNumbers());
            parent = explored.get(i).getParent();
            i--;
            while (i >= 0 && explored.get(i) != parent) {
                i--;
            }
        }
        return res;
    }


}