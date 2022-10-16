package com.example.demo.Fringe;

import com.example.demo.State;

import java.util.Stack;

public class StackFringe implements IFringe {
    Stack<State> fringe;

    public StackFringe() {
        fringe = new Stack<>();
    }

    public void push(State state) {
        fringe.push(state);
    }

    public State pop() {
        return fringe.pop();
    }

    public boolean empty() {
        return fringe.empty();
    }

}
