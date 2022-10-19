package com.example.demo.Algorithms;

import com.example.demo.Fringe.AStarFringe;
import com.example.demo.Heuristics.IHeuristic;
import com.example.demo.States.State;

import java.util.ArrayList;

public class AStarSolver implements puzzleSolver {

    IHeuristic heuristic;
    ArrayList<State> explored;
    ArrayList<String> res;

    public AStarSolver(IHeuristic heuristic) {
        this.heuristic = heuristic;
        this.explored = new ArrayList<>();
    }

    boolean notExplored(ArrayList<State> explored, String numbers) {
        for (State i : explored) {
            if (i.getNumbers().equals(numbers)) return false;
        }
        return true;
    }


   // @Override
    public ArrayList<String> solve(String initialState) {
        AStarFringe fringe = new AStarFringe();
        State state = new State(initialState, null, this.heuristic, 0);
        fringe.push(state);
        while (!fringe.empty()) {
            State st = fringe.pop();
            explored.add(st);
            if (st.isGoalState()) break;
            for (String i : st.getAdjacent()) {
                State temp = new State(i, st, heuristic.setHeuristic(i), st.getCostG() + 1);
                State inFringe = fringe.containState(temp);
                if (notExplored(explored, i) && inFringe == null) {
                    fringe.push(temp);
                } else if (inFringe != null) {
                    if (inFringe.getTotalCost() > temp.getTotalCost()) {
                        fringe.remove(inFringe);
                        fringe.push(temp);
                    }
                }
            }
        }
        res = getGoalPath(explored);
        return res;
    }

    private ArrayList<String> getGoalPath(ArrayList<State> explored) {
        ArrayList<String> res = new ArrayList<>();
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