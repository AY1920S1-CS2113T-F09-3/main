package eggventory.model.states;

import eggventory.model.states.State;
import java.util.Stack;

public class HistoryList {
    public Stack<String> stockHistory;
    private Stack<String> stockTypeHistory;
    private Stack<String> loanListHistory;
    private Stack<String> templateListHistory;
    private Stack<String> personListHistory;

    public HistoryList() {
        this.stockHistory = new Stack<>();
        this.stockTypeHistory = new Stack<>();
        this.loanListHistory = new Stack<>();
        this.templateListHistory = new Stack<>();
        this.personListHistory = new Stack<>();
    }

    public boolean isEmpty() {
        return (stockHistory.empty() || stockTypeHistory.empty() || loanListHistory.empty());
    }

    public void pushStockSave(String stockStateSave) {
        stockHistory.push(stockStateSave);
    }

    public void pushStockTypeSave(String stockTypeSave) {
        stockTypeHistory.push(stockTypeSave);
    }

    public void pushLoanListSave(String loanListSave) {
        loanListHistory.push(loanListSave);
    }

    public void pushTemplateListSave(String templateListSave) {
        templateListHistory.push(templateListSave);
    }

    public void pushPersonListSave(String personListSave) {
        personListHistory.push(personListSave);
    }

    public String popStockSave() {
        return stockHistory.pop();
    }

    public String popStockTypeSave() {
        return stockTypeHistory.pop();
    }

    public String popLoanListSave() {
        return loanListHistory.pop();
    }

    public String popPersonListSave() {
        return personListHistory.pop();
    }

    public String popTemplateListSave() {
        return templateListHistory.pop();
    }



}
