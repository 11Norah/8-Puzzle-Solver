package com.example.demo.Algorithms;

import com.example.demo.Fringe.QueueFringe;

import java.util.ArrayList;
import java.util.HashSet;

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

    public HashSet<String> getExplored() {
        return searcher.getExplored();
    }

    public static void main(String[] args) {
        String puzzle = "125340678";
        System.out.println("BFS Start!!");
        BfsSolver solver = new BfsSolver();
        solver.solve(puzzle);
        ArrayList<String> solution = solver.getSolution();
        for(String state : solution) {
            System.out.println(state);
        }
    }
}
