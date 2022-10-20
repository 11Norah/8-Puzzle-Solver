package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.States.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class UninformedSearch {
    public static ArrayList<String> solve(String initialPuzzle, IFringe fringe) {
        HashSet<String> visited = new HashSet<>();
        State initialState = new State(initialPuzzle, null);
        State goalState = null;
        fringe.push(initialState);
        while(!fringe.empty()) {
            State currState = fringe.pop();
            if(currState.isGoalState()) {
                goalState = currState;
                break;
            }
            String numbers = currState.getNumbers();
            System.out.println("Current State:" + numbers);
            if(visited.contains(numbers)) continue;
            visited.add(numbers);
            ArrayList<String> nextStates = currState.generateAdjacent();
            for(String nextState : nextStates) {
                System.out.println(nextState);
                State state = new State(nextState, currState);
                fringe.push(state);
            }
        }
        ArrayList<String> solution = new ArrayList<>();
        State currState = goalState;
        while(currState != null) {
            solution.add(currState.getNumbers());
            currState = currState.getParent();
        }
        Collections.reverse(solution);
        return solution;
    }
}
