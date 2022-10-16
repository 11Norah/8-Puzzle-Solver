package com.example.demo;
import com.example.demo.Heuristics.IHeuristic;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSolver {

    static boolean compareArrays(int[] arr1, int[] arr2) {
        for (int i = 0; i < 9; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    static boolean notExplored(ArrayList<State> explored, int[] arr) {
        for (State i : explored) {
            if (compareArrays(i.getNumbers(), arr)) return false;
        }
        return true;
    }

    static public boolean checkGoalState(int[] numbers) {
        int c = 0;
        for (int i : numbers) {
            if (i != c++) return false;
        }
        return true;
    }

    static public ArrayList<State> AStar(int[] initialState, IHeuristic heuristic) {
        ArrayList<State> explored = new ArrayList<>();
        PriorityQueue<State> fringe = new PriorityQueue<>();
        State state = new State(initialState, null, heuristic);
        fringe.add(state);
        while (!fringe.isEmpty()) {
            State st = fringe.poll();
            explored.add(st);
            if (checkGoalState(st.getNumbers())) break;
            for (int[] i : st.getAdjacent()) {
                if (notExplored(explored, i)) fringe.add(new State(i, st,heuristic.setHeuristic(i)));
            }
        }
        return explored;
    }


    static public ArrayList<int[]> getGoalPath(ArrayList<State> explored) {
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