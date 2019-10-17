package eggventory.commands.edit;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.enums.Property;
import eggventory.items.Stock;
import eggventory.ui.Cli;

/**
 * Command objects for editing stocks
 * Requires the stockCode (as listed by the system) of the stock.
 */
public class EditStockCommand extends Command {

    private String stockCode;
    private Property property; //Stores the property you want to edit
    private String newValue; //Stores the newValue you want

    public EditStockCommand(CommandType type, String stockCode, Property property, String newValue) {
        super(type);
        this.stockCode = stockCode;
        this.property = property;
        this.newValue = newValue;
    }

    @Override
    public String execute (StockList list, Cli cli, Storage storage) {
        String output;
        Stock edited = list.setStock(stockCode, property, newValue);
        output = String.format("Awesome! I have successfully updated the following stock: %s | %s | %d | %s\n",
                edited.getStockType(), edited.getStockCode(), edited.getQuantity(),
                edited.getDescription());
        storage.save(list);
        cli.print(output);
        return output;
    }
}
