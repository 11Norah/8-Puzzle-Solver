package com.example.demo.Fringe;

import com.example.demo.States.State;

import java.util.LinkedList;
import java.util.Queue;

public class QueueFringe implements IFringe {
    Queue<State> fringe;

    public QueueFringe() {
        fringe = new LinkedList<>();
    }

    public void push(State state) {
        fringe.add(state);
    }

    public State pop() {
        return fringe.poll();
    }

    public boolean empty() {
        return fringe.isEmpty();
    }

}
