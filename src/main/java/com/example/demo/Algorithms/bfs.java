package com.example.demo.Algorithms;

import com.example.demo.Fringe.IFringe;
import com.example.demo.Fringe.QueueFringe;

import java.util.ArrayList;

public class bfs implements puzzleSolver {
    public ArrayList<int[]> solve(int[] initialPuzzle) {
        IFringe queue = new QueueFringe();
        return UninformedSearch.solve(initialPuzzle, queue);
    }
    public static void main(String[] args) {
        int[] puzzle = {1, 5, 3, 4, 7, 8, 6, 2, 0};
        System.out.println("Start!!");
        ArrayList<int[]> solution = new bfs().solve(puzzle);
        for(int[] state : solution) {
            for(int num : state) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
