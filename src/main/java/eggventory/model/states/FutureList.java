package eggventory.model.states;

import eggventory.model.states.State;
import java.util.Stack;

public class FutureList {
    private Stack<String> stockFuture;
    private Stack<String> stockTypeFuture;
    private Stack<String> loanListFuture;
    private Stack<String> templateListFuture;
    private Stack<String> personListFuture;

    public FutureList() {
        this.stockFuture = new Stack<>();
        this.stockTypeFuture = new Stack<>();
        this.loanListFuture= new Stack<>();
        this.templateListFuture = new Stack<>();
        this.personListFuture = new Stack<>();
    }

    public boolean isEmpty() {
        return (stockFuture.empty() || stockTypeFuture.empty() || loanListFuture.empty());
    }

    public void pushStockSave(String stockStateSave) {
        stockFuture.push(stockStateSave);
    }

    public void pushStockTypeSave(String stockTypeSave) {
        stockTypeFuture.push(stockTypeSave);
    }

    public void pushLoanListSave(String loanListSave) {
        loanListFuture.push(loanListSave);
    }

    public void pushTemplateListSave(String templateListSave) {
        templateListFuture.push(templateListSave);
    }

    public void pushPersonListSave(String personListSave) {
        personListFuture.push(personListSave);
    }

    public String popStockSave() {
        return stockFuture.pop();
    }

    public String popStockTypeSave() {
        return stockTypeFuture.pop();
    }

    public String popLoanListSave() {
        return loanListFuture.pop();
    }

    public String popPersonListSave() {
        return personListFuture.pop();
    }

    public String popTemplateListSave() {
        return templateListFuture.pop();
    }
}
