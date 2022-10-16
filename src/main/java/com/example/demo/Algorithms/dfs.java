package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.Fringe.StackFringe;

import java.util.ArrayList;

public class dfs implements puzzleSolver {
    public ArrayList<int[]> solve(int[] initialPuzzle) {
        IFringe stack = new StackFringe();
        return UninformedSearch.solve(initialPuzzle, stack);
    }

}
