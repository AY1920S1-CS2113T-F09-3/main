package eggventory;

import eggventory.logic.commands.ByeCommand;
import eggventory.logic.commands.Command;
import eggventory.commons.enums.CommandType;
import eggventory.logic.parsers.Parser;
import eggventory.model.*;
import eggventory.model.states.FutureList;
import eggventory.model.states.HistoryList;
import eggventory.model.states.State;
import eggventory.storage.Storage;
import eggventory.ui.Cli;
import eggventory.ui.Gui;
import eggventory.ui.Ui;


/**
 * Eggventory is a task list that supports 3 types of classes - Todos, deadlines and events.
 * Tasks can be added, marked as done, searched for, and deleted.
 * Tasks are loaded from and saved to file.
 */
public class Eggventory {
    private static Storage storage;
    private static Parser parser;
    private static Ui ui;
    private static State state;
    private static StateInterface stateInterface;

    /**
     * Sets up the frontend, the Gui and the event handlers. This will create an instance of the
     * backend, the Eggventory class, and will use that to control the Gui.
     * @param args Can input `--args=cli` after `gradlew run` to startup in Cli mode.
     */
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String stockFilePath = currentDir + "/data/saved_stocks.csv";
        String stockTypesFilePath = currentDir + "/data/saved_stocktypes.csv";
        String loanListFilePath = currentDir + "/data/saved_loanlist.csv";
        String personListFilePath = currentDir + "/data/saved_personlist.csv";
        String templateListFilePath = currentDir + "/data/saved_templatelist.csv";

        storage = new Storage(stockFilePath, stockTypesFilePath, loanListFilePath, personListFilePath,
                templateListFilePath);
        parser = new Parser();
        stateInterface = new StateInterface(storage.load(), storage.loadLoanList(), storage.loadPersonList(),
                storage.loadTemplateList());



        /*
        Calendar date = Calendar.getInstance();
        loanList.addLoan("R500", "A123", 100, date, date);
        loanList.addLoan("R500", "A6000", 100, date, date);
        loanList.addLoan("ARDUINO", "A123", 100, date, date);
        loanList.addLoan("NO", "A12", 100, date, date);

        System.out.print("All: \n" + loanList.printLoans());
        System.out.print("A123: \n" + loanList.printPersonLoans("A123"));
        System.out.print("R500: \n" + loanList.printStockLoans("R500"));
        */


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
            //TODO: Check whether SLAP is violated
            Command command = parser.parse(userInput);
            if (command.getType().equals(CommandType.BYE)) {
                ((ByeCommand) command).executeSaveMoreLists(stateInterface.getStockList(), ui, storage,
                        stateInterface.getLoanList(), stateInterface.getPersonList(),
                        stateInterface.getTemplateList());
            }
            command.updateState(stateInterface);
            command.execute(stateInterface.getStockList(), ui, storage);



            if (command.getType() == CommandType.BYE) {
                System.exit(0);
            }
        } catch (Exception e) {
            ui.printError(e);
        }
    }
}
