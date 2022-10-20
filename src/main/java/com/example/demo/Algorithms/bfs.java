package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.Fringe.QueueFringe;

import java.util.ArrayList;

public class bfs implements puzzleSolver {
    public ArrayList<String> solve(String initialPuzzle) {
        return UninformedSearch.solve(initialPuzzle, new QueueFringe());
    }
    public static void main(String[] args) {
        String puzzle = "125340678";
        System.out.println("BFS Start!!");
        ArrayList<String> solution = new bfs().solve(puzzle);
        for(String state : solution) {
            System.out.println(state);
        }
    }
}
