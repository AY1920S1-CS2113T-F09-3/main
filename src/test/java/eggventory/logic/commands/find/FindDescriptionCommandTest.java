package eggventory.logic.commands.find;

import eggventory.commons.enums.CommandType;
import eggventory.stubs.StorageStub;
import eggventory.stubs.UiStub;
import eggventory.ui.Ui;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@@author yanprosobo
class FindCommandTest {
    StockList testStockList = new StockList();
    Ui testCli = new UiStub();
    Storage testStorage = new StorageStub();

    //Test for search returning no result.
    @Test
    public void testExecuteFindDescription_EmptyResult() {
        String search = "search";
        String expected = "Sorry, I could not find any stock containing the description \""
                + search + "\".\nPlease try a different search string.";
        String output = new FindDescriptionCommand(CommandType.FIND, search).execute(testStockList, testCli, testStorage);
        assertEquals(expected, output);
    }
    /*
    //Integration test with add stocktype and add stock. Using "search" as the word to be searching for.
    @Test
    public void testExecuteFindDescription_Success() {
        testStockList.addStockType("TestType");
        testStockList.addStock("TestType", "#T", 1, "Test search");
        testStockList.addStock("TestType", "#T2", 10, "Test query");
        testStockList.addStock("TestType", "#T3", 100, "Test search query");

        String expected = "TestType | #T | 1 | Test search\n" + "TestType | #T3 | 100 | Test search query\n";
        String output = FindDescriptionCommand(CommandType.FIND, "search").execute(testStockList, testCli, testStorage);
        assertEquals(expected, output);
    }*/

    /*
    @Test
    public void execute_Task_success() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        stockType.addItem(TaskType.TODO, "Test FIND");
        new FindCommand(CommandType.FIND, "FIND").execute(stockType, cli, storage);
        assertEquals("1. [T] " + stockType.getTask(0).getStatusIcon() + "Test FIND", os.toString().trim());
    }*/
}
//@@author