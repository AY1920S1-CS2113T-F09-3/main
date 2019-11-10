package eggventory.logic.commands.delete;

import eggventory.commons.enums.CommandType;
import eggventory.model.StockList;
import eggventory.model.TemplateList;
import eggventory.model.loans.Loan;
import eggventory.storage.Storage;
import eggventory.stubs.StorageStub;
import eggventory.stubs.UiStub;
import eggventory.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTemplateCommandTest {

    private Loan[] loans = new Loan[1];
    private String testTemplateName = "Test Template";

    private StockList testStockList = new StockList();
    private Ui testCli = new UiStub();
    private Storage testStorage = new StorageStub();

    DeleteTemplateCommandTest() {
        loans[0] = new Loan("TestCode", "1");
    }

    @Test
    void execute_TemplateExists_ReturnsSuccessString() {
        DeleteTemplateCommand commandUnderTest = new DeleteTemplateCommand(CommandType.DELETE, testTemplateName);
        TemplateList.addTemplate(testTemplateName, loans);
        assertEquals("Nice, I have deleted this template for you: Test Template", commandUnderTest.execute());
    }
}