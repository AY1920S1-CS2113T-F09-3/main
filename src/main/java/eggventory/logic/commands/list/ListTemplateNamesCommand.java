package eggventory.logic.commands.list;

import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.logic.commands.Command;
import eggventory.model.StockList;
import eggventory.model.TemplateList;
import eggventory.storage.Storage;
import eggventory.ui.Ui;

public class ListTemplateNamesCommand extends Command {

    public ListTemplateNamesCommand(CommandType type) {
        super(type);
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        String output = TemplateList.printTemplateNames();
        return null;
    }
}
