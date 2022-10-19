package com.example.demo.Fringe;

import com.example.demo.States.State;

public interface IFringe {
    void push(State numbers);

    State pop();

    boolean empty();

    void remove(State st);
}
