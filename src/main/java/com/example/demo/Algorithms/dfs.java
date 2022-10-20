package com.example.demo.Algorithms;

import com.example.demo.Fringe.StackFringe;

import java.util.ArrayList;

public class dfs implements puzzleSolver {
    public ArrayList<String> solve(String initialPuzzle) {
        return UninformedSearch.solve(initialPuzzle, new StackFringe());
    }

    public static void main(String[] args) {
        String puzzle = "125340678";
        System.out.println("DFS Start!!");
        ArrayList<String> solution = new dfs().solve(puzzle);
        for(String state : solution) {
            System.out.println(state);
        }
    }

}
