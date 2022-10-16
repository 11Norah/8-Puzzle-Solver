package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.Fringe.QueueFringe;

import java.util.ArrayList;

public class bfs implements puzzleSolver {

    public ArrayList<int[]> solve(int[] initialPuzzle) {
        IFringe queue = new QueueFringe();
        return UninformedSearch.solve(initialPuzzle, queue);
    }

}
