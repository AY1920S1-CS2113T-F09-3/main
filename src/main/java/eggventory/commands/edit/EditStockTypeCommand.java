package eggventory.commands.edit;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.items.StockType;
import eggventory.ui.Cli;

public class EditStockTypeCommand extends Command {

    private String stockType;
    private String newName;

    public EditStockTypeCommand(CommandType type, String stockType, String newValue){
        super(type);
        this.stockType = stockType;
        this.newName = newValue;
    }


    @Override
    public String execute (StockList list, Cli cli, Storage storage) {
        String output;
        StockType edited = list.setStockType(stockType, newName);
        output = String.format("Awesome! I have successfully updated the following stockType name: %s\n",
                edited.getName());
        storage.save(list);
        cli.print(output);
        return output;
    }
}
