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

    public HashSet<String> getExplored() {
        return searcher.getExplored();
    }

    public static void main(String[] args) {
        String puzzle = "125340678";
        System.out.println("DFS Start!!");
        DfsSolver solver = new DfsSolver();
        solver.solve(puzzle);
        ArrayList<String> solution = solver.getSolution();
        for(String state : solution) {
            System.out.println(state);
        }
    }

}
