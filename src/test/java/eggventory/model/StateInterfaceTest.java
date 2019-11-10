package eggventory.model;

import eggventory.commons.exceptions.BadInputException;
import org.junit.jupiter.api.Test;



class StateInterfaceTest {
    StockList testStockList = new StockList();
    LoanList testLoanList = new LoanList();
    PersonList testPersonList = new PersonList();
    TemplateList testTemplateList = new TemplateList();
    StateInterface testInterface = new StateInterface(testStockList, testLoanList, testPersonList, testTemplateList);

    @Test
    void pushStateHistoryList() throws BadInputException {
        testStockList.addStockType("TT");
        testStockList.addStock("TT", "T1", 100,
                "Test");
        testInterface.pushStateHistoryList();
        assertEquals("TT,T1,100,Test,0\n", testInterface.getHistoryList().popStockSave());
        assertEquals("Uncategorised\nTT", testInterface.getHistoryList().popStockTypeSave());
    }

    private void assertEquals(String s, String popStockSave) {
    }

    @Test
    void pushStateFutureList() {
    }

    @Test
    void executeUndoCommand() {
    }

    @Test
    void executeRedoCommand() {
    }

    @Test
    void updateStateHistory() {
    }
}