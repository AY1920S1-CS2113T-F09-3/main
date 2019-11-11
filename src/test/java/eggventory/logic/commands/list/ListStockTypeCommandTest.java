package eggventory.logic.commands.list;

import eggventory.logic.commands.list.ListStockTypeCommand;
import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.stubs.StorageStub;
import eggventory.stubs.UiStub;
import eggventory.ui.Ui;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListStockTypeCommandTest {
    private StockList testStockList = new StockList();
    private Ui testCli = new UiStub();
    private Storage testStorage = new StorageStub();

    @Test
    void execute_InvalidStocktype_ThrowsBadInputException() {
        String invalid = "Invalidtype";
        String valid = "Validtype";
        String alsoValid = "AlsoValidType";

        testStockList.addStockType(valid);
        testStockList.addStockType(alsoValid);
        assertDoesNotThrow(() -> ListStockTypeCommand(CommandType.LIST, valid).execute(testStockList, testCli, testStorage));
        assertThrows(BadInputException.class, () -> ListStockTypeCommand(CommandType.LIST, invalid)
                .execute(testStockList, testCli, testStorage));
    }
}