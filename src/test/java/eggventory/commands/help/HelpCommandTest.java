package eggventory.commands.help;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.ui.Cli;
import eggventory.ui.Ui;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@@author patwaririshab
class HelpCommandTest {
    Ui testCli = new Cli();
    Storage testStorage = new Storage("");
    StockList testStockList = new StockList();
    private static String OS = System.getProperty("os.name").toLowerCase();

    /*
     * Tests output when User enters "delete" option after help.
     * This ensures that the switch block is working correctly.
     * ie. "help delete".
     * @throws IOException when filePath has an error.
     */
    @Test
    void execute_Null_Success() throws IOException { //User enters "help"
        Command help = new HelpCommand(CommandType.EDIT, null);
        String output = help.execute(testStockList, testCli, testStorage);
        String filename;

        if (OS.indexOf("win") >= 0) {
            filename = "\\src\\main\\java\\eggventory\\commands\\help\\Help.txt";
        } else {
            filename = "/src/main/java/eggventory/commands/help/Help.txt";
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), filename);
        String testoutput = Files.readString(filePath); //default UTF-8 charset.
        assertEquals(testoutput, output);
    }

    /*
     * Tests output when User enters no option after help.
     * This ensures that the switch block is working correctly.
     * ie. "help".
     * @throws IOException when filePath has an error.
     */
    @Test
    void execute_Add_Success() throws IOException { //User enters "help add"
        Command help = new HelpCommand(CommandType.EDIT, "add");
        String output = help.execute(testStockList, testCli, testStorage);
        String filename;

        if (OS.indexOf("win") >= 0) {
            filename = "\\src\\main\\java\\eggventory\\commands\\help\\Helpadd.txt";
        } else {
            filename = "src/main/java/eggventory/commands/help/Helpadd.txt";
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), filename);
        String testoutput = Files.readString(filePath); //default UTF-8 charset.
        assertEquals(testoutput, output);
    }

    /*
     * Tests output when User enters "add" option after help.
     * This ensures that the switch block is working correctly.
     * ie. "help add".
     * @throws IOException when filePath has an error.
     */
    @Test
    void execute_Edit_Success() throws IOException { //User enters "help edit"
        Command help = new HelpCommand(CommandType.EDIT, "edit");
        String output = help.execute(testStockList, testCli, testStorage);
        String filename;

        if (OS.indexOf("win") >= 0) {
            filename = "\\src\\main\\java\\eggventory\\commands\\help\\Helpedit.txt";
        } else {
            filename = "src/main/java/eggventory/commands/help/Helpedit.txt";
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), filename);
        String testoutput = Files.readString(filePath); //default UTF-8 charset.
        assertEquals(testoutput, output);
    }

    /*
     * Tests output when User enters "edit" option after help.
     * This ensures that the switch block is working correctly.
     * ie. "help edit".
     * @throws IOException when filePath has an error.
     */
    @Test
    void execute_Delete_Success() throws IOException {
        Command help = new HelpCommand(CommandType.EDIT, "delete");
        String output = help.execute(testStockList, testCli, testStorage);
        String filename;

        if (OS.indexOf("win") >= 0) {
            filename = "\\src\\main\\java\\eggventory\\commands\\help\\Helpdelete.txt";
        } else {
            filename = "src/main/java/eggventory/commands/help/Helpdelete.txt";
        }
        Path filePath = Paths.get(System.getProperty("user.dir"), filename);
        String testoutput = Files.readString(filePath); //default UTF-8 charset.
        assertEquals(testoutput, output);
    }

    /*
     * Tests output when User enters an option other than add,edit or delete.
     * ie. "help abc123".
     */
    @Test
    void execute_WrongOption_Success() {
        Command help = new HelpCommand(CommandType.EDIT, "abc123");
        String expected = "Your help command is not defined. Please enter 'help' for reference.";
        String output = help.execute(testStockList, testCli, testStorage);
        assertEquals(expected, output);
    }

    @Test
    void execute_WrongFilePathHelp_Fail() {
        String expected = "Error in reading help.txt";

    }

}