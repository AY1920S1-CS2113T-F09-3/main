package eggventory.model.states;

import eggventory.model.LoanList;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.model.TemplateList;

import java.util.Stack;

public class StateList {
    public Stack<String> stockStates;
    private Stack<String> stockTypeStates;
    private Stack<String> loanListStates;
    private Stack<String> templateListStates;
    private Stack<String> personListStates;

    /**
     * Initialises stacks to store historical states of model components.
     */
    public StateList() {
        this.stockStates = new Stack<>();
        this.stockTypeStates = new Stack<>();
        this.loanListStates = new Stack<>();
        this.templateListStates = new Stack<>();
        this.personListStates = new Stack<>();
    }

    public boolean isEmpty() {
        return (stockStates.empty() || stockTypeStates.empty() || loanListStates.empty());
    }

    private void pushStockSave(String stockStateSave) {
        stockStates.push(stockStateSave);
    }

    private void pushStockTypeSave(String stockTypeSave) {
        stockTypeStates.push(stockTypeSave);
    }

    private void pushLoanListSave(String loanListSave) {
        loanListStates.push(loanListSave);
    }

    private void pushTemplateListSave(String templateListSave) {
        templateListStates.push(templateListSave);
    }

    private void pushPersonListSave(String personListSave) {
        personListStates.push(personListSave);
    }

    /**
     * Push all state components to statelist.
     */
    public void pushAllStatesSave(State currentState) {
        StockList stockList = currentState.getStockList();
        LoanList loanList = currentState.getLoanList();
        PersonList personList = currentState.getPersonList();
        TemplateList templateList = currentState.getTemplateList();

        pushStockSave(stockList.saveDetailsString());
        pushStockTypeSave(stockList.saveStockTypesString());
        pushLoanListSave(loanList.saveLoanListString());
        pushPersonListSave(personList.savePersonListString());
        pushTemplateListSave(templateList.saveTemplateListString());
    }


    public String popStockSave() {
        return stockStates.pop();
    }

    public String popStockTypeSave() {
        return stockTypeStates.pop();
    }

    public String popLoanListSave() {
        return loanListStates.pop();
    }

    public String popPersonListSave() {
        return personListStates.pop();
    }

    public String popTemplateListSave() {
        return templateListStates.pop();
    }
}
