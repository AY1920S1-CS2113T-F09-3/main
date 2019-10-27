package eggventory.parsers;

import eggventory.commands.Command;
import eggventory.commands.delete.DeleteLoanCommand;
import eggventory.commands.delete.DeleteStockCommand;
import eggventory.commands.delete.DeleteStockTypeCommand;
import eggventory.enums.CommandType;
import eggventory.exceptions.BadInputException;
import eggventory.exceptions.InsufficientInfoException;

//@@author cyanoei
public class ParseDelete {

    private Command processDeleteLoan(String input) throws InsufficientInfoException {

        String[] deleteInput = input.split(" +");

        if (deleteInput.length < 2) {
            throw new InsufficientInfoException("Please enter information after the 'delete' command in"
                    + " this format:\ndelete loan <StockCode> <MatricNo>");
        } else if (deleteInput[0].isBlank() | deleteInput[1].isBlank()) {
            throw new InsufficientInfoException("Please enter information after the 'delete' command in"
                    + " this format:\ndelete loan <StockCode> <MatricNo>");
        }

        return new DeleteLoanCommand(CommandType.DELETE, deleteInput[0], deleteInput[1]);
    }

    /**
     * Processes a user command that began with the word "add".
     * Used to differentiate between the different elements the user is able to add (stock, stocktype, loan),
     * and create a command object to execute the adding of the element.
     *
     * @param inputString The input that was given after the word add.
     *                    Describes what the user wants to add, and any other details.
     * @return a Command object which will execute the desired command.
     * @throws InsufficientInfoException if not all compulsory attributes were specified.
     */
    public Command parse(String inputString) throws InsufficientInfoException, BadInputException {

        String[] deleteInput = inputString.split(" +", 2); //Obtains the first word of the input.

        Command deleteCommand;

        switch (deleteInput[0]) {

        case "stock":
            deleteInput[1] = deleteInput[1].strip();
            deleteCommand = new DeleteStockCommand(CommandType.DELETE, deleteInput[1]);
            break;

        case "stocktype":
            deleteCommand = new DeleteStockTypeCommand(CommandType.DELETE, deleteInput[1]);
            break;

        case "loan":
            deleteCommand = processDeleteLoan(deleteInput[1]);
            break;

        default:
            throw new BadInputException("Unexpected value: " + deleteInput[0]);
        }

        return deleteCommand;

    }
}
