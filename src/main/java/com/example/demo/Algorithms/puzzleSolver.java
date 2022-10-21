package com.example.demo.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;

public interface puzzleSolver {
    void solve(String initialPuzzle);
    ArrayList<String> getSolution();
    HashSet<String> getExplored();
}
