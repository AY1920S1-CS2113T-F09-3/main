package eggventory.logic.commands.add;

//@@author Deculsion

import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.logic.commands.Command;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.ui.Ui;

public class AddPersonCommand extends Command{
    private String name;
    private String matricNo;

    public AddPersonCommand(CommandType type, String matricNo, String name) {
        super(type);
        this.name = name;
        this.matricNo = matricNo;
    }

    /**
     * Method to execute the command.
     * @param list the stocklist
     * @param ui the ui to print to
     * @param storage to storage to save data
     * @return The string that is output to ui.
     */
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException{
        PersonList.add(matricNo, name);

        String output = String.format("Nice, I have added this person for you.\n" +
                "Matriculation No.: %s | Name: %s", matricNo, name);

        ui.print(output);
        return output;
    }
}
