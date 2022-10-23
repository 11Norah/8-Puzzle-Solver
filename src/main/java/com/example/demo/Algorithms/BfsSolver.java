package com.example.demo.Algorithms;

import com.example.demo.Fringe.QueueFringe;

import java.util.ArrayList;

public class BfsSolver implements puzzleSolver {

    UninformedSearcher searcher;

    public BfsSolver() {
        searcher = new UninformedSearcher(new QueueFringe());
    }

    public void solve(String initialPuzzle) {
        searcher.solve(initialPuzzle);
    }

    public ArrayList<String> getSolution() {
        return searcher.getSolution();
    }

    public int getExploredSize() {
        return searcher.getExplored().size();
    }

    public int getMaxDepth() {
        return searcher.getMaxDepth();
    }

}
