package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class UninformedSearch {
    private static ArrayList<Integer> convertArrayToList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int num : array) {
            list.add(num);
        }
        return list;
    }

    public static ArrayList<int[]> solve(int[] initialPuzzle, IFringe fringe) {
        HashSet<ArrayList<Integer>> visited = new HashSet<>();
        State initialState = new State(initialPuzzle, null);
        State goalState = null;
        fringe.push(initialState);
        while(!fringe.empty()) {
            State currState = fringe.pop();
            if(currState.isGoalState()) {
                goalState = currState;
                break;
            }
            if(visited.contains(convertArrayToList(currState.getNumbers()))) continue;
            visited.add(convertArrayToList(currState.getNumbers()));
            ArrayList<int[]> nextStates = currState.getAdjacent();
            for(int[] nextState : nextStates) {
                State state = new State(nextState, currState);
                fringe.push(state);
            }
        }
        ArrayList<int[]> solution = new ArrayList<>();
        State currState = goalState;
        while(currState != null) {
            solution.add(currState.getNumbers());
            currState = currState.getParent();
        }
        Collections.reverse(solution);
        return solution;
    }
}
