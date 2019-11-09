package eggventory.model.states;

import eggventory.model.states.State;
import java.util.Stack;

public class HistoryList {
    private Stack<State> historyList = new Stack<State>();


    public State addLatestState(State newState) {
        historyList.push(newState);
        return historyList.peek();
    }

    public Stack<State> getHistoryList() {
        return historyList;
    }

    public int getStackSize() {
        return historyList.size();
    }

    public State popLastState() {
        State current = historyList.peek();
        historyList.pop();
        return current;
    }

    public void pushState(State newState) {
        historyList.push(newState);
    }


}
