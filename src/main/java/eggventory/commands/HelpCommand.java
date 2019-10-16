package eggventory.commands;

import eggventory.Ui;
import eggventory.Storage;
import eggventory.StockList;
import eggventory.enums.CommandType;
import eggventory.commands.Command;

public class HelpCommand extends Command {
    private CommandType type;
    private String options;

    public HelpCommand(CommandType type) {
        this.type = type;
    }

    public HelpCommand(CommandType type, String options) {
        this.type = type;
        this.options = options;
    }
}