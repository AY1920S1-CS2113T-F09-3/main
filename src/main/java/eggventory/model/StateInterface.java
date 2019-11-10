package eggventory.model;

import eggventory.commons.exceptions.BadInputException;
import eggventory.model.states.FutureList;
import eggventory.model.states.HistoryList;
import eggventory.model.states.State;
import eggventory.storage.StorageStub;

//@@author patwaririshab
public class StateInterface {
    private  HistoryList historyList;
    private FutureList futureList;
    private static State currentState;

    /**
     * Initializes the static currentState which contains Model containers and historylist and futurelists.
     */
    public StateInterface(StockList stockList, LoanList loanList, PersonList personList, TemplateList templateList) {
        this.historyList = new HistoryList();
        this.futureList = new FutureList();
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
        historyList.pushStockSave(stockList.saveDetailsString());
        historyList.pushStockTypeSave(stockList.saveStockTypesString());
        historyList.pushLoanListSave(loanList.saveLoanListString());
        historyList.pushPersonListSave(personList.savePersonListString());
        historyList.pushTemplateListSave(templateList.saveTemplateListString());
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
        futureList.pushStockSave(stockList.saveDetailsString());
        futureList.pushStockTypeSave(stockList.saveStockTypesString());
        futureList.pushLoanListSave(loanList.saveLoanListString());
        futureList.pushPersonListSave(personList.savePersonListString());
        futureList.pushTemplateListSave(templateList.saveTemplateListString());
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

        //Push current state information into futurelist and clear current static state variables
        pushStateFutureList();
        getStockList().getList().clear();
        getPersonList().getPersonList().clear();
        getTemplateList().getTemplates().clear();
        getLoanList().getLoansList().clear();

        //Load the newstate information from history list
        StockList updatedStockList = new StorageStub().loadStockList(historyList.popStockSave(),
                historyList.popStockTypeSave());
        LoanList updatedLoanList = new StorageStub().loadLoanList(historyList.popLoanListSave());
        PersonList updatedPersonList = new StorageStub().loadPersonList(historyList.popPersonListSave());
        TemplateList updatedTemplateList = new StorageStub().loadTemplateList(historyList.popTemplateListSave());


        //Update currentstate infromation with new state information
        currentState.setStockList(updatedStockList);
        currentState.setLoanList(updatedLoanList);
        currentState.setPersonList(updatedPersonList);
        currentState.setTemplateList(updatedTemplateList);
    }

    /**
     * Pushes current state into historylist and loads last state from futurelist.
     * @throws BadInputException when future state contains bad input.
     */
    public void executeRedoCommand() throws BadInputException {
        if (futureList.isEmpty()) {
            throw new BadInputException("The REDO command could not be executed.\n");
        }

        //Push current state into history list and clear current state variables
        pushStateHistoryList();
        getStockList().getList().clear();
        getPersonList().getPersonList().clear();
        getTemplateList().getTemplates().clear();
        getLoanList().getLoansList().clear();

        //Load the newstate information from history list
        StockList updatedStockList = new StorageStub().loadStockList(futureList.popStockSave(),
                futureList.popStockTypeSave());
        LoanList updatedLoanList = new StorageStub().loadLoanList(futureList.popLoanListSave());
        PersonList updatedPersonList = new StorageStub().loadPersonList(futureList.popPersonListSave());
        TemplateList updatedTemplateList = new StorageStub().loadTemplateList(futureList.popTemplateListSave());

        //Update currentstate infromation with new stateinformation
        currentState.setStockList(updatedStockList);
        currentState.setLoanList(updatedLoanList);
        currentState.setPersonList(updatedPersonList);
        currentState.setTemplateList(updatedTemplateList);

    }

    /**
     * Pushes current state into historylist and reinitialises a clean futurelist.
     */
    public void updateStateHistory() {
        futureList = new FutureList();
        pushStateHistoryList();
    }

    public HistoryList getHistoryList() {
        return historyList;
    }

    public FutureList getFutureList() {
        return futureList;
    }
}
//@@author
