package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.States.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class UninformedSearch {
    public static ArrayList<String> solve(String initialPuzzle, IFringe fringe) {

        //set containing explored states.
        HashSet<String> visited = new HashSet<>();

        //set keeping track of states inside the fringe.
        HashSet<String> fringeSet = new HashSet<>();

        State initialState = new State(initialPuzzle, null);

        State goalState = null;

        fringe.push(initialState);
        fringeSet.add(initialPuzzle);

        while(!fringe.empty()) {

            State currState = fringe.pop();
            visited.add(currState.getNumbers());
            fringeSet.remove(currState.getNumbers());

            if(currState.isGoalState()) {
                goalState = currState;
                break;
            }

            ArrayList<String> nextStates = currState.generateAdjacent();
            for(String nextState : nextStates) {
                if(!visited.contains(nextState) && !fringeSet.contains(nextState)) {
                    State state = new State(nextState, currState);
                    fringe.push(state);
                    fringeSet.add(nextState);
                }
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
