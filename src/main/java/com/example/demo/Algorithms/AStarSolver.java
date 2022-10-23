package com.example.demo.Algorithms;

import com.example.demo.Fringe.AStarFringe;
import com.example.demo.Heuristics.IHeuristic;
import com.example.demo.States.State;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarSolver {


    public HashMap<String, State> solve(String initialState, IHeuristic heuristic) {
        HashMap<String, State> explored = new HashMap<>();
        AStarFringe frontier = new AStarFringe();
        explored.put("maxDepth", new State(0));
        State state = new State(initialState, null, 0, heuristic.generateHeuristic(initialState));
        frontier.push(state);
        while (!frontier.empty()) {
            State st = frontier.pop();
            if (explored.get("maxDepth").getCostG() < st.getCostG()) explored.put("maxDepth", st);
            explored.put(st.getNumbers(), st);
            if (st.isGoalState()) break;
            ArrayList<String> adjacents = st.generateAdjacent();
            for (String i : adjacents) {
                State temp = new State(i, st, st.getCostG() + 1, heuristic.generateHeuristic(i));
                State inFrontier = frontier.containState(temp);
                if (!explored.containsKey(i) && inFrontier == null) {
                    frontier.push(temp);
                } else if (inFrontier != null) {
                    if (inFrontier.getTotalCost() > temp.getTotalCost()) {
                        frontier.remove(inFrontier);
                        frontier.push(temp);
                    }
                }
            }
        }

        return explored;
    }

    public ArrayList<String> getGoalPath(HashMap<String, State> explored) {
        ArrayList<String> res = new ArrayList<>();
        res.add("012345678");
        State parent = explored.get("012345678").getParent();
        while (parent != null) {
            res.add(parent.getNumbers());
            parent = parent.getParent();
        }
        return res;
    }


}
