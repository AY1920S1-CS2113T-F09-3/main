package eggventory;

import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.parsers.Parser;
import eggventory.ui.Cli;
import eggventory.ui.Gui;
import eggventory.ui.Ui;

import java.util.Calendar;


/**
 * Eggventory is a task list that supports 3 types of classes - Todos, deadlines and events.
 * Tasks can be added, marked as done, searched for, and deleted.
 * Tasks are loaded from and saved to file.
 */
public class Eggventory {
    private static Storage storage;
    private static Parser parser;
    private static Ui ui;
    private static StockList stockList;
    private static PersonList personList;
    private static LoanList loanList;

    /**
     * Sets up the frontend, the Gui and the event handlers. This will create an instance of the
     * backend, the Eggventory class, and will use that to control the Gui.
     * @param args Can input `--args=cli` after `gradlew run` to startup in Cli mode.
     */
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/data/saved_tasks.txt";

        storage = new Storage(filePath);
        parser = new Parser();
        stockList = storage.load();
        loanList = new LoanList();

        Calendar date = Calendar.getInstance();
        loanList.addLoan("R500", "A123", 100, date, date);
        loanList.addLoan("R500", "A6000", 100, date, date);
        loanList.addLoan("ARDUINO", "A123", 100, date, date);
        loanList.addLoan("NO", "A12", 100, date, date);

        System.out.print("All: \n" + loanList.printLoans());
        System.out.print("A123: \n" + loanList.printPersonLoans("A123"));
        System.out.print("R500: \n" + loanList.printStockLoans("R500"));



        if (args.length >= 1 && args[0].equals("cli")) {
            ui = new Cli();
        } else {
            ui = new Gui();
        }

        ui.initialize(Eggventory::userInteraction);
    }

    /**
     * Main REPL loop.
     */
    private static void userInteraction() {
        try {

            String userInput = ui.read();

            Command command = parser.parse(userInput);
            command.execute(stockList, ui, storage);

            if (command.getType() == CommandType.BYE) {
                System.exit(0);
            }
        } catch (Exception e) {
            ui.printError(e);
        }
    }
}
