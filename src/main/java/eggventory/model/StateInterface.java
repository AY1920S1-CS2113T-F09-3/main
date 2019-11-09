package eggventory.model;

import eggventory.commons.exceptions.BadInputException;
import eggventory.model.states.FutureList;
import eggventory.model.states.HistoryList;
import eggventory.model.states.State;

public class StateInterface {
    private HistoryList historyList;
    private FutureList futureList;
    private State currentState;

    public StateInterface(StockList stockList, LoanList loanList, PersonList personList, TemplateList templateList) {
        this.historyList = new HistoryList();
        this.futureList = new FutureList();
        this.currentState = new State(stockList, loanList, personList, templateList);
    }

    public StockList getStockList() {
        return currentState.getStockList();
    }

    public LoanList getLoanList() {
        return currentState.getLoanList();
    }

    public PersonList getPersonList() {
        return currentState.getPersonList();
    }

    public TemplateList getTemplateList() {
        return currentState.getTemplateList();
    }

    public void executeUndoCommand() throws BadInputException {
        if (historyList.getStackSize() == 0) {
            throw new BadInputException("The UNDO command could not be executed.\n");
        }
        futureList.pushState(currentState);
        currentState = historyList.popLastState();
        //TODO: Remove after debugging
        System.out.println(currentState.getStockList().toString());
    }

    public void executeRedoCommand() throws BadInputException {
        if (futureList.getStackSize() == 0) {
            throw new BadInputException("The REDO command could not be executed.\n");
        }
        historyList.pushState(currentState);
        currentState = futureList.popLastState();
    }

    public void updateStateHistory() {
        futureList = new FutureList();
        historyList.pushState(currentState);
    }
}
