package eggventory.logic.commands.list;

import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.logic.commands.Command;
import eggventory.commons.enums.CommandType;
import eggventory.ui.Ui;
import eggventory.commons.exceptions.BadInputException;

//@@author yanprosobo

public class ListStockTypeCommand extends Command {
    private String query;

    public ListStockTypeCommand(CommandType type, String query) {
        super(type);
        this.query = query;
    }

    /**
     * Executes the list command.
     * @param list Stocklist object.
     * @param ui ui object.
     * @param storage storage objcet.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        String output = "";

        if (query.equals("all")) { // list stocktype all command
            String listString = "";
            listString = list.toStocktypeString(); //Should contain all the stockTypes already, need not iterate.
            output = listString;
            ui.print(output);
            // Drawing stock data in GUI table.
            ui.drawTable(list.getAllStockTypesStruct());
            return output;
        }

        if (!list.isExistingStockType(query)) {
            throw new BadInputException("Invalid command: No such stocktype exists!");

        } else if (list.isStocktypeZeroQuantity(query)) {
            output = "There are currently 0 stocks with that stocktype.";
            ui.print(output);
            return output;
        }

        // list stocktype <Stock Type> command
        output = String.format("%s INVENTORY\n"
                + "------------------------\n", query);

        output += list.queryStockType(query).toString();

        ui.print(output);
        // Drawing data on stocks under specific stocktype in GUI table.
        ui.drawTable(list.getAllStocksInStockTypeStruct(query));

        return output;
    }
}
//@@author