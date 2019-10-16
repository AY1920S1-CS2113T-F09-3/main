package eggventory.commands;

import eggventory.Ui;
import eggventory.Storage;
import eggventory.StockList;
import eggventory.enums.CommandType;
import eggventory.commands.Command;

public class HelpCommand extends Command {
    private String options;

    public HelpCommand(CommandType type) {
        super(type);
        this.options = null;
    }

    public HelpCommand(CommandType type, String options) {
        super(type);
        this.options = options;
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;
        if(this.options == null) {
            output = "JUST HELP";
            ui.print(output);
        } else {
            output = "HELP " + this.options;
            ui.print(output);
        }
        return output;
    }
}