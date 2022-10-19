package com.example.demo.Fringe;

import com.example.demo.States.State;

import java.util.Iterator;
import java.util.PriorityQueue;

public class AStarFringe implements IFringe {
    private PriorityQueue<State> priorityQueue;

    public AStarFringe() {
        priorityQueue = new PriorityQueue<>();
    }

    public State containState(State obj) {
        State temp;
        Iterator<State> node = priorityQueue.iterator();
        while (node.hasNext()) {
            temp = node.next();
            boolean flag = true;
            String i = temp.getNumbers(), comparison = obj.getNumbers();
            for (int k = 0; k < 9; k++) {
                if (i.charAt(k) != comparison.charAt(k)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return temp;
        }
        return null;
    }

    @Override
    public void push(State numbers) {
        priorityQueue.add(numbers);
    }

    @Override
    public State pop() {
        return priorityQueue.poll();
    }

    @Override
    public boolean empty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public void remove(State st) {
        priorityQueue.remove(st);
    }
}
