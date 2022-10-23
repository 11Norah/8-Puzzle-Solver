package com.example.demo.Algorithms;

import com.example.demo.Fringe.StackFringe;

import java.util.ArrayList;

public class DfsSolver implements puzzleSolver {

    UninformedSearcher searcher;

    public DfsSolver() {
        searcher = new UninformedSearcher(new StackFringe());
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
