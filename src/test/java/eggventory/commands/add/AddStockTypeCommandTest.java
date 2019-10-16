package eggventory.commands.add;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.Ui;
import eggventory.commands.AddCommand;
import eggventory.enums.CommandType;
import eggventory.exceptions.BadInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddStockTypeCommandTest {

    private StockList testStockList = new StockList();
    private Ui testUi = new Ui();
    private Storage testStorage = new Storage("");

    @Test
    void testExecute_AddStocktypeSuccess() throws BadInputException {
        StockList testList = new StockList();
        testList.addStockType("testStockType");

        String output = new AddStockTypeCommand(CommandType.ADD, "testStockType").execute(testList, testUi, testStorage);

        assertEquals(String.format("Nice! I have successfully added the stocktype: testStockType"), output);
    }
}