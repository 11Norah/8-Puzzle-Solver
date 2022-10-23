package com.example.demo.Fringe;

import com.example.demo.States.State;

import java.util.HashMap;
import java.util.PriorityQueue;

public class AStarFringe implements IFringe {
    private final PriorityQueue<State> priorityQueue;
    private final HashMap<String,State> pqEntries;

    public AStarFringe() {
        priorityQueue = new PriorityQueue<>();
        pqEntries = new HashMap<>();
    }

    public State containState(State obj) {
        if(pqEntries.containsKey(obj.getNumbers())) {
            return pqEntries.get(obj.getNumbers());
        }
        return null;
    }

    @Override
    public void push(State numbers) {
        priorityQueue.add(numbers);
        pqEntries.put(numbers.getNumbers(),numbers);
    }

    @Override
    public State pop() {
        State peek = priorityQueue.poll();
        assert peek != null;
        pqEntries.remove(peek.getNumbers());
        return peek;
    }

    @Override
    public boolean empty() {
        return priorityQueue.isEmpty();
    }

}
