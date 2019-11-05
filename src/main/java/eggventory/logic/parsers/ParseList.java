package eggventory.logic.parsers;

import eggventory.commons.exceptions.InsufficientInfoException;
import eggventory.logic.commands.Command;
import eggventory.logic.commands.CommandDictionary;
import eggventory.logic.commands.list.ListPersonCommand;
import eggventory.logic.commands.list.ListPersonLoansCommand;
import eggventory.logic.commands.list.ListStockCommand;
import eggventory.logic.commands.list.ListStockTypeCommand;
import eggventory.logic.commands.list.ListPersonLoansCommand;
import eggventory.logic.commands.list.ListPersonCommand;
import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;

//@@author yanprosobo
public class ParseList {

    private Command processListStock(String input) throws BadInputException {
        String[] inputArr = input.split(" ");
        if (inputArr.length > 1) { // Checking if anything extraneous is after stock
            throw new BadInputException(CommandDictionary.getCommandUsage("list stock"));
        }
        return new ListStockCommand(CommandType.LIST);
    }

    private Command processListStockType(String input) throws BadInputException {
        String[] inputArr = input.split(" ");
        if (inputArr.length > 1) { // Checking for extra arguments
            throw new BadInputException(CommandDictionary.getCommandUsage("list stocktype"));
        }
        return new ListStockTypeCommand(CommandType.LIST, inputArr[0]);
    }

    private Command processListPerson(String input) throws BadInputException {
        String[] inputArr = input.split(" ");

        if (inputArr.length > 1) {
            throw new BadInputException(CommandDictionary.getCommandUsage("list person"));
        }

        return new ListPersonCommand(CommandType.LIST);
    }

    private Command processListLoan(String input) throws BadInputException {
        String[] inputArr = input.split(" +");

        switch (inputArr.length) {
        case 1:
            throw new BadInputException(CommandDictionary.getCommandUsage("list loan"));
            //return new ListLoanCommand(CommandType.LIST);
        case 2:
            return new ListPersonLoansCommand(CommandType.LIST, inputArr[1]);

        default:
            throw new BadInputException(CommandDictionary.getCommandUsage("list loan"));
        }
    }

    /**
     * Processes a user command that began with the word "list".
     * Used to differentiate between the different elements the user is able to list (stock, stocktype, etc),
     * and create a Command object to execute the listing of the element.
     * @param inputString String input that was given after the word "list".
     *              Describes what the user wants to list, and any other details.
     * @return a Command object which will execute the desired command.
     * @throws BadInputException If the input format was not adhered to.
     */
    public Command parse(String inputString) throws BadInputException, InsufficientInfoException {
        String[] inputArr = inputString.split(" ", 2);

        Command listCommand;

        switch (inputArr[0]) {
        case "stock":
            listCommand = processListStock(inputArr[0]);
            break;

        case "stocktype":
            if (!Parser.isCommandComplete(inputString, 1)) {
                throw new InsufficientInfoException(CommandDictionary.getCommandUsage("list stocktype"));
            }
            listCommand = processListStockType(inputArr[1]);
            break;

        case "person":
            listCommand = processListPerson(inputArr[0]);
            break;

        case "loan":
            listCommand = processListLoan(inputString);
            break;

        default:
            throw new BadInputException(CommandDictionary.getCommandUsage("list"));
        }

        return listCommand;
    }

}
//@@author
