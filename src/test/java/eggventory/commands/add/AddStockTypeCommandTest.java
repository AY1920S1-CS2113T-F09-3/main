package eggventory.commands.add;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.exceptions.BadInputException;
import eggventory.ui.Cli;
import eggventory.enums.CommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AddStockTypeCommandTest {

    private StockList testStockList = new StockList();
    private Cli testCli = new Cli();
    private Storage testStorage = new Storage("");

    //@@author patwaririshab
    @Test
    void testExecuteAddStockType_ValidStockType_Succeeds() throws BadInputException {
        String output = new AddStockTypeCommand(CommandType.ADD, "testStockType")
                .execute(testStockList, testCli, testStorage);

        assertEquals("Nice! I have successfully added the stocktype: testStockType", output);
    }

    //@@author cyanoei
    @Test
    void testExecuteAddStockType_RepeatedStockType_ThrowsBadInputException() throws BadInputException {
        testStockList.addStockType("testStockType");
        Exception exception = assertThrows(BadInputException.class, () ->
            new AddStockTypeCommand(CommandType.ADD, "testStockType")
                    .execute(testStockList, testCli, testStorage)
        );
        assertEquals(String.format("Sorry, \"testStockType\" is already an existing stock type."),
                exception.getMessage());
    }

}