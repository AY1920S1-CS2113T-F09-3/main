package eggventory.logic.parsers;

import eggventory.commons.exceptions.InsufficientInfoException;
import eggventory.logic.commands.Command;
import eggventory.logic.commands.CommandDictionary;
import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.logic.commands.find.FindDescriptionCommand;


//@@author yanprosobo
public class ParseFind {

    private Command processFindDescription(String searchQuery) throws BadInputException {
        return new FindDescriptionCommand(CommandType.FIND, searchQuery);
    }

    /*private Command processListStockType(String input) throws BadInputException {
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
                return new ListLoanCommand(CommandType.LIST);
            case 2:
                return new ListPersonLoansCommand(CommandType.LIST, inputArr[1]);

            default:
                throw new BadInputException(CommandDictionary.getCommandUsage("list loan"));
        }

    }

    private Command processListTemplate(String input) throws BadInputException {
        String[] inputArr = input.split(" +");
        switch (inputArr.length) {
            case 1:
                return new ListTemplatesAllCommand(CommandType.LIST);
            case 2:
                return new ListTemplateCommand(CommandType.LIST, inputArr[1]);
            default:
                throw new BadInputException(CommandDictionary.getCommandUsage("list template"));
        }
    }*/

    /**
     * Processes a user command that began with the word "find".
     * Used to differentiate between the different elements the user is able to find (stock, stocktype, etc),
     * and create a Command object to execute the finding of the query.
     * @param inputString String input that was given after the word "find".
     *              Describes what the user wants to filter, and the word query to search for.
     * @return a Command object which will execute the desired command.
     * @throws BadInputException If the input format was not adhered to.
     */
    public Command parse(String inputString) throws BadInputException, InsufficientInfoException {
        String[] inputArr = inputString.split(" ", 2);
        String option = inputArr[0];
        Command findCommand;

        switch (option) {
            case "description":
                if (!Parser.isCommandComplete(inputString, 1)) {
                    throw new InsufficientInfoException(CommandDictionary.getCommandUsage("find description"));
                }
                String searchQuery = inputArr[1];
                findCommand = processFindDescription(searchQuery);
                break;

            /*case "stocktype":
                //Required: stockType <name>
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

            case "template":
                listCommand = processListTemplate(inputString);
                break;*/

            default:
                throw new BadInputException(CommandDictionary.getCommandUsage("find"));
        }

        return findCommand;
    }

}
//@@author
