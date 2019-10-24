package eggventory.commands.edit;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.items.StockType;
import eggventory.ui.Gui;

/**
 * Edits the attributes of StockType.
 */
public class EditStockTypeCommand extends Command {

    private String stockType;
    private String newName;

    /**
     * Initializes the attributes of StockType.
     * @param type The type of Command.
     * @param stockType The String that uniquely identifies a StockType.
     * @param newValue The new value of the property of StockType.
     */
    public EditStockTypeCommand(CommandType type, String stockType, String newValue) {
        super(type);
        this.stockType = stockType;
        this.newName = newValue;
    }

    /**
     * Executes the actual edit of the StockType attribute.
     * @param list The StockList containing all the StockType.
     * @param gui Gui object instance to display output to.
     * @param storage Storage object to handle saving and loading of any data.
     * @return
     */
    @Override
    public String execute(StockList list, Gui gui, Storage storage) {
        String output;
        StockType edited = list.setStockType(stockType, newName);
        output = String.format("Awesome! I have successfully updated the following stockType name: %s\n",
                edited.getName());
        storage.save(list);
        gui.print(output);
        return output;
    }
}
