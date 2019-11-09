package eggventory.model;

import eggventory.commons.exceptions.BadInputException;
import eggventory.model.states.FutureList;
import eggventory.model.states.HistoryList;
import eggventory.model.states.State;
import eggventory.storage.Storage;
import eggventory.storage.StorageStub;

public class StateInterface {
    private HistoryList historyList;
    private FutureList futureList;
    private State currentState;

    public StateInterface(StockList stockList, LoanList loanList, PersonList personList, TemplateList templateList) {
        this.historyList = new HistoryList();
        this.futureList = new FutureList();
        this.currentState = new State(stockList, loanList, personList, templateList);
    }

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


        //Testing purpose
        System.out.println(historyList.stockHistory.peek());
    }

    public void pushStateFutureList() {
        StockList stockList = currentState.getStockList();
        LoanList loanList = currentState.getLoanList();
        PersonList personList = currentState.getPersonList();
        TemplateList templateList = currentState.getTemplateList();

        //Push current state into historyList
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

    public void executeUndoCommand() throws BadInputException {
        if (historyList.isEmpty()) {
            throw new BadInputException("The UNDO command could not be executed.\n");
        }
        pushStateFutureList();
        StockList updatedStockList = new StorageStub().loadStockList(historyList.popStockSave(),
                historyList.popStockTypeSave());
        LoanList updatedLoanList = new StorageStub().loadLoanList(historyList.popLoanListSave());
        PersonList updatedPersonList = new StorageStub().loadPersonList(historyList.popPersonListSave());
        TemplateList updatedTemplateList = new StorageStub().loadTemplateList(historyList.popTemplateListSave());

        currentState.setStockList(updatedStockList);
        currentState.setLoanList(updatedLoanList);
        currentState.setPersonList(updatedPersonList);
        currentState.setTemplateList(updatedTemplateList);

        System.out.println(currentState.getStockList().toString());
    }

    public void executeRedoCommand() throws BadInputException {
        if (futureList.isEmpty()) {
            throw new BadInputException("The REDO command could not be executed.\n");
        }
    }

    public void updateStateHistory() {
        futureList = new FutureList();
        pushStateHistoryList();
    }
}
