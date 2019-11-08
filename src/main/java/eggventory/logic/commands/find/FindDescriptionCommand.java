package eggventory.logic.commands.find;

import eggventory.ui.Ui;
import eggventory.model.items.StockType;
import eggventory.model.items.Stock;
import eggventory.logic.commands.Command;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.commons.enums.CommandType;

//@@author yanprosobo
/**
 * Command objects for searching for stocks by filter then the search query.
 */
public class FindDescriptionCommand extends Command {
    private String search;

    public FindDescriptionCommand(CommandType type, String search) {
        super(type);
        this.search = search;
    }

    /**
     * Allows the user to search for stock descriptions that match a given string.
     * Prints the list of stocks that match. Alternatively prints a message if none are found.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;
        int stockTypeQuantity = list.getStockTypeQuantity();
        boolean found = false;
        //int counter = 1;

        //TableStruct tableStruct = new TableStruct("Find");
        //tableStruct.setTableColumns("Stock Type", "Stock Code", "Quantity", "Description");
        //ArrayList<ArrayList<String>> dataArray = new ArrayList<>();

        String findList = "";
        //for each stocktype
        for (int i = 0; i < stockTypeQuantity; i++) {
            StockType currStockType = list.get(i);
            findList += currStockType.queryAllStocksDescription(search);
        }

        //condition is false if listString had no changes.
        if (!findList.equals("")) {
            found = true;
        }

        if (!found) {
            output = "Sorry, I could not find any stock containing the description \""
                    + search + "\".\nPlease try a different search string.";
            ui.print(output);
        } else {
            output = findList;
            ui.print(output);
            //ui.drawTable(findList.getAllFindStruct())
        }
        return output;
    }
}
//@@author
