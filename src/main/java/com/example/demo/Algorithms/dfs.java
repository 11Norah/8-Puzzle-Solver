package com.example.demo.Algorithms;

import com.example.demo.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class dfs {

    public ArrayList<int[]> solve(int[] initialPuzzle) {
        Stack<State> fringe = new Stack<>();
        State initialState = new State(initialPuzzle, null);
        State goalState = null;
        fringe.push(initialState);
        while(!fringe.empty()) {
            State currState = fringe.pop();
            if(currState.isGoalState()) {
                goalState = currState;
                break;
            }
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
