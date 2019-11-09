package eggventory.logic.commands;

import eggventory.stubs.StorageStub;
import eggventory.stubs.UiStub;
import eggventory.storage.Storage;
import eggventory.ui.Ui;
import eggventory.model.StockList;
import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HelpCommandTest {
    StockList testStockList = new StockList();
    Ui testCli = new UiStub();
    Storage testStorage = new StorageStub();

    @Test
    public void testExecuteHelpCommand_InvalidCommand_ThrowsBadInputException() throws BadInputException {
        assertThrows(BadInputException.class, () -> new HelpCommand(CommandType.HELP, "invalid")
                .execute(testStockList,testCli,testStorage));
    }
}