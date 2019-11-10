package eggventory.model;

import eggventory.commons.exceptions.BadInputException;
import eggventory.model.states.State;
import eggventory.model.states.StateList;
import eggventory.storage.StorageLoadStub;

//@@author patwaririshab
public class StateInterface {
    private StateList historyList;
    private StateList futureList;
    private static State currentState;

    /**
     * Initializes the static currentState which contains Model containers and historylist and futurelists.
     */
    public StateInterface(StockList stockList, LoanList loanList, PersonList personList, TemplateList templateList) {
        this.historyList = new StateList();
        this.futureList = new StateList();
        this.currentState = new State(stockList, loanList, personList, templateList);
    }

    /**
     * Pushes state of model components converted into String to historylist.
     */
    public void pushStateHistoryList() {
        StockList stockList = currentState.getStockList();
        LoanList loanList = currentState.getLoanList();
        PersonList personList = currentState.getPersonList();
        TemplateList templateList = currentState.getTemplateList();

        //Push current state into historyList
        historyList.pushAllStatesSave(stockList, loanList, personList, templateList);
    }

    /**
     * Pushes state of model components converted into String to futurelist.
     */
    public void pushStateFutureList() {
        StockList stockList = currentState.getStockList();
        LoanList loanList = currentState.getLoanList();
        PersonList personList = currentState.getPersonList();
        TemplateList templateList = currentState.getTemplateList();

        //Push current state into futurelist
        futureList.pushAllStatesSave(stockList, loanList, personList, templateList);
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

    /**
     * Pushes current state into futurelist and loads last state from historylist.
     * @throws BadInputException when previous state contains bad input.
     */
    public void executeUndoCommand() throws BadInputException {
        if (historyList.isEmpty()) {
            throw new BadInputException("The UNDO command could not be executed.\n");
        }

        //Push current state information into futurelist
        pushStateFutureList();

        //Load the newstate information from history list
        StockList updatedStockList = new StorageLoadStub().loadStockList(historyList.popStockSave(),
                historyList.popStockTypeSave());
        LoanList updatedLoanList = new StorageLoadStub().loadLoanList(historyList.popLoanListSave());
        PersonList updatedPersonList = new StorageLoadStub().loadPersonList(historyList.popPersonListSave());
        TemplateList updatedTemplateList = new StorageLoadStub().loadTemplateList(historyList.popTemplateListSave());

        //Update currentstate infromation with new state information
        currentState.setAllStates(updatedStockList, updatedLoanList, updatedPersonList, updatedTemplateList);
    }

    /**
     * Pushes current state into historylist and loads last state from futurelist.
     * @throws BadInputException when future state contains bad input.
     */
    public void executeRedoCommand() throws BadInputException {
        if (futureList.isEmpty()) {
            throw new BadInputException("The REDO command could not be executed.\n");
        }

        //Push current state into history list
        pushStateHistoryList();

        //Load the newstate information from history list
        StockList updatedStockList = new StorageLoadStub().loadStockList(futureList.popStockSave(),
                futureList.popStockTypeSave());
        LoanList updatedLoanList = new StorageLoadStub().loadLoanList(futureList.popLoanListSave());
        PersonList updatedPersonList = new StorageLoadStub().loadPersonList(futureList.popPersonListSave());
        TemplateList updatedTemplateList = new StorageLoadStub().loadTemplateList(futureList.popTemplateListSave());

        //Update currentstate infromation with new stateinformation
        currentState.setAllStates(updatedStockList, updatedLoanList, updatedPersonList, updatedTemplateList);
    }

    /**
     * Pushes current state into historylist and reinitialises a clean futurelist.
     */
    public void updateStateHistory() {
        futureList = new StateList();
        pushStateHistoryList();
    }

    public StateList getHistoryList() {
        return historyList;
    }

    public StateList getFutureList() {
        return futureList;
    }
}
//@@author
