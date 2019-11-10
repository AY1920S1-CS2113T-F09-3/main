package eggventory.model.states;

import eggventory.model.LoanList;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.model.TemplateList;
import eggventory.storage.Storage;
import eggventory.storage.StorageLoadStub;

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

    /**
     * Clears current state components and updates with new component value.
     * @param stockList new stockList containing stock and stocktype state.
     * @param loanList new loanList containing loanList state.
     * @param personList new personList containing personlist state.
     * @param templateList new templatelist containing templatelist state.
     */
    public void setAllStates(StockList stockList, LoanList loanList, PersonList personList, TemplateList templateList) {
        clearAllStates();
        this.stockList = stockList;
        this.loanList = loanList;
        this.personList = personList;
        this.templateList = templateList;
    }

    /**
     * Clears all statelists.
     */
    private void clearAllStates() {
        stockList.getList().clear();
        loanList.getLoansList().clear();
        personList.getPersonList().clear();
        templateList.getTemplates().clear();
    }
}
