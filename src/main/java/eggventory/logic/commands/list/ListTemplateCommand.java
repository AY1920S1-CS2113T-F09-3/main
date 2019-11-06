package eggventory.logic.commands.list;

import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.logic.commands.Command;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.ui.Ui;

public class ListTemplateCommand extends Command {
    public ListTemplateCommand(CommandType type) {
        super(type);
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        return null;
    }
}
