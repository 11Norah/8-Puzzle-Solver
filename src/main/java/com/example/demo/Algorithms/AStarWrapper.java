package com.example.demo.Algorithms;

import com.example.demo.Heuristics.IHeuristic;
import com.example.demo.States.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AStarWrapper implements puzzleSolver {

    private AStarSolver aStarSolver;
    private IHeuristic heuristic;
    private int exploredSize;

    private HashMap<String, State> explored;
    private ArrayList<String> res = new ArrayList<>();

    private int maxDepth;

    public AStarWrapper(IHeuristic heuristic) {
        aStarSolver = new AStarSolver();
        this.heuristic = heuristic;
        maxDepth = 0;
    }

    @Override
    public void solve(String initialPuzzle) {
        this.explored = aStarSolver.solve(initialPuzzle, this.heuristic);
        this.exploredSize = explored.size();
        this.res = aStarSolver.getGoalPath(this.explored);
        this.maxDepth = (int) this.explored.get("maxDepth").getCostG();
    }

    @Override
    public ArrayList<String> getSolution() {
        return this.res;
    }

    @Override
    public int getExploredSize() {
        return this.explored.size();
    }

    @Override
    public int getMaxDepth() {
        return this.maxDepth;
    }
}
