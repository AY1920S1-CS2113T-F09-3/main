package eggventory.logic.commands.find;

import java.util.ArrayList;
import eggventory.ui.TableStruct;
import eggventory.ui.Ui;
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
     * Prints the list of stocks that matches. Alternatively prints a message if none are found.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;

        //for UI
        StockList findList;
        findList = list.queryStocksDescription(search);

        //for GUI
        TableStruct tableStruct = new TableStruct("Query for: " + search);
        tableStruct.setTableColumns("Stock Type", "Stock Code", "Quantity", "Description");
        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();

        if (findList.isEmpty()) {
            output = "Sorry, I could not find any stock containing the description \""
                    + search + "\".\nPlease try a different search string.";
            ui.print(output);
            return output;
        }

        //Format and prints ui
        output = findList.toString();
        ui.print(output);

        //Format and prints gui
        ui.drawTable(findList.getAllStocksStruct());

        return output;
    }
}
//@@author
