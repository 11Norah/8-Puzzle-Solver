package com.example.demo.Algorithms;

import com.example.demo.Heuristics.IHeuristic;
import com.example.demo.States.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AStarWrapper implements puzzleSolver {

    AStarSolver aStarSolver;
    IHeuristic heuristic;

    int exploredSize;

    HashMap<String, State> explored;
    ArrayList<String> res = new ArrayList<>();


    public AStarWrapper(IHeuristic heuristic) {
        aStarSolver = new AStarSolver();
        this.heuristic = heuristic;
    }

    @Override
    public void solve(String initialPuzzle) {
        this.explored = aStarSolver.solve(initialPuzzle, this.heuristic);
        this.exploredSize = explored.size();
        this.res = aStarSolver.getGoalPath(this.explored);
    }

    @Override
    public ArrayList<String> getSolution() {
        return this.res;
    }

    @Override
    public int getExploredSize() {
        return this.explored.size();
    }
}
