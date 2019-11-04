package eggventory.logic.commands;

import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.ui.Ui;

public class EchoCommand extends Command {

    String echo;

    public EchoCommand(CommandType type, String echo) {
        super(type);
        this.echo = echo;
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        ui.print(echo);
        return echo;
    }
}
