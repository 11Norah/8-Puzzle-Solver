package com.example.demo.Fringe;

import com.example.demo.State;

public interface IFringe {
    void push(State numbers);
    State pop();
    boolean empty();
}
