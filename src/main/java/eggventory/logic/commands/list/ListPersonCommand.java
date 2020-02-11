package eggventory.logic.commands.list;

import eggventory.commons.enums.CommandType;
import eggventory.logic.commands.Command;
import eggventory.model.PersonList;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.ui.Ui;

//@@author Deculsion
public class ListPersonCommand extends Command {

    public ListPersonCommand(CommandType type) {
        super(type);
    }

    /**
     * Executes the command.
     * @param stockList Stocklist object.
     * @param ui ui object.
     * @param storage storage objcet.
     */
    public String execute(StockList stockList, Ui ui, Storage storage) {
        String output;
        PersonList personList = PersonList.getInstance();

        if (personList.getSize() == 0) {
            output = "There is nobody in the list";
            ui.print(output);
            return output;
        }

        output = personList.listToString();
        ui.print(output);
        ui.drawTable(personList.getAllPersonStruct());

        return output;

    }
}
