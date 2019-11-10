package eggventory.model.states;

import eggventory.model.LoanList;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.model.TemplateList;

public class State {
    private StockList stockList;
    private LoanList loanList;
    private PersonList personList;
    private TemplateList templateList;

    /**
     * Initializes referrences to static model components.
     */
    public State(StockList stockList, LoanList loanList, PersonList personList, TemplateList templateList) {
        this.stockList = stockList;
        this.loanList = loanList;
        this.personList = personList;
        this.templateList = templateList;
    }

    public StockList getStockList() {
        return stockList;
    }

    public LoanList getLoanList() {
        return loanList;
    }

    public PersonList getPersonList() {
        return personList;
    }

    public TemplateList getTemplateList() {
        return templateList;
    }




    public void setStockList(StockList stockList) {
        this.stockList = stockList;
    }

    public void setLoanList(LoanList loanList) {
        this.loanList = loanList;
    }

    public void setPersonList(PersonList personList) {
        this.personList = personList;
    }

    public void setTemplateList(TemplateList templateList) {
        this.templateList = templateList;
    }
}
