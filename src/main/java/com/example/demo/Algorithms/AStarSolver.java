package com.example.demo.Algorithms;

import com.example.demo.Fringe.AStarFringe;
import com.example.demo.Heuristics.IHeuristic;
import com.example.demo.States.State;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarSolver {
     public int exp_count=0;


    // @Override
    public ArrayList<String> solve(String initialState, IHeuristic heuristic) {
        HashMap<String, State> explored = new HashMap<>();
        ArrayList<String> res;
        AStarFringe fringe = new AStarFringe();
       // long start = System.nanoTime();
        State state = new State(initialState, null, 0, heuristic.generateHeuristic(initialState));
       // long end = System.nanoTime();
      //  System.out.println("State Creation = " + ((end - start) / 1000));
        fringe.push(state);
        while (!fringe.empty()) {
            State st = fringe.pop();
            explored.put(st.getNumbers(),st);
            if (st.isGoalState()) break;
            ArrayList<String> adjacents = st.generateAdjacent();
            for (String i : adjacents) {
                State temp = new State(i, st, st.getCostG() + 1, heuristic.generateHeuristic(i));
                State inFringe = fringe.containState(temp);
                if (!explored.containsKey(i) && inFringe == null) {
                    fringe.push(temp);
                } else if (inFringe != null) {
                    if (inFringe.getTotalCost() > temp.getTotalCost()) {
                        //start = System.nanoTime();
                        fringe.remove(inFringe);
                       // end = System.nanoTime();
                        //System.out.println("Fringe removal = " + ((end - start) / 1000));
                       // start = System.nanoTime();
                        fringe.push(temp);
                      //  end = System.nanoTime();
                     //   System.out.println("Fringe push = " + ((end - start) / 1000));
                    }
                }
            }
        }
        this.exp_count= explored.size();
        res = getGoalPath(explored);
        return res;
    }

    private ArrayList<String> getGoalPath(HashMap<String,State> explored) {
        ArrayList<String> res =new ArrayList<>();
        res.add("012345678");
        State parent = explored.get("012345678").getParent();
        while(parent != null){
            res.add(parent.getNumbers());
            parent = parent.getParent();
        }
        return res;
    }


}
