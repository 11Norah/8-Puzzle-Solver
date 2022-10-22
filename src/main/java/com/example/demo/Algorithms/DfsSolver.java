package com.example.demo.Algorithms;

import com.example.demo.Fringe.StackFringe;

import java.util.ArrayList;
import java.util.HashSet;

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


}
