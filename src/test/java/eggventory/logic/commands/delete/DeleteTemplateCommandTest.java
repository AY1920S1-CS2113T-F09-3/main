package eggventory.logic.commands.delete;

import eggventory.commons.enums.CommandType;
import eggventory.model.TemplateList;
import eggventory.model.loans.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTemplateCommandTest {

    private Loan[] loans = new Loan[1];
    private String testTemplateName = "Test Template";

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