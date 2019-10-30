package eggventory.logic.commands.add;

import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.commons.exceptions.BadInputException;
import eggventory.stubs.UiStub;
import eggventory.ui.Cli;
import eggventory.commons.enums.CommandType;
import eggventory.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author patwaririshab
public class AddStockCommandTest {
    private StockList testStockList = new StockList();
    private Ui testCli = new UiStub();
    private Storage testStorage = new Storage("");
    String testStockType;
    String testStockCode;
    String testDescription;
    int testQuantity;


    @Test
    void testExecuteAddStock_ValidStock_Succeeds() throws BadInputException {
        testStockList.addStockType("testStockType");
        String output = new AddStockCommand(CommandType.ADD, "testStockType", "t0000", 100,
                "testDescription").execute(testStockList, testCli, testStorage);

        //Check whether a stock is added to the list correctly
        assertEquals("testStockType", testStockList.getStockType("testStockType").getName());

        //Check whether execute sends correct message to print
        assertEquals(String.format("Nice! I have successfully added the stock: StockType: %s StockCode: %s "
                + "Quantity: %d Description: %s","testStockType", "t0000", 100, "testDescription"), output);

    }

    //@@author cyanoei
    @Test
    void testExecuteAddStock_DuplicateStock_ThrowsBadInputException() {
        testStockType = "Uncategorised";
        testStockCode = "R5";
        testQuantity = 1000;
        testDescription = "A resistor";

        testStockList.addStock(testStockType, testStockCode, testQuantity, testDescription);
        Exception exception = assertThrows(BadInputException.class, () -> new AddStockCommand(CommandType.ADD,
                testStockType, testStockCode, 10, "Another resistor").execute(testStockList,
                testCli, testStorage));

        //Check whether execute prints the error message to CLI.
        assertEquals(String.format("Sorry, the stock code \"%s\" is already assigned to a stock in the system. "
                + "Please enter a different stock code.", testStockCode), exception.getMessage());
    }
    //@@author
}
