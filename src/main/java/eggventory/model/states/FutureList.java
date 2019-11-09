package eggventory.model.states;

import eggventory.model.states.State;
import java.util.Stack;

public class FutureList {
    private Stack<State> futureList = new Stack<State>();


    public Stack<State> getFutureList() {
        return futureList;
    }

    public void setFutureList(Stack<State> futureList) {
        this.futureList = futureList;
    }

    public void pushState(State newState) {
        futureList.push(newState);
    }

    public int getStackSize() {
        return futureList.size();
    }

    public State popLastState() {
        return futureList.pop();
    }
}
