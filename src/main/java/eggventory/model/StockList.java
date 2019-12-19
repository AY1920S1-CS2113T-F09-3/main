package eggventory.model;

import eggventory.commons.enums.StockProperty;
import eggventory.commons.exceptions.BadInputException;
import eggventory.model.items.Stock;
import eggventory.ui.TableStruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

//@@author Deculsion
public class StockList {
    private ArrayList<Stock> stockList;
    private ArrayList<String> stockTypeList;

    /**
     * Constructs a new StockList object using an already existing stockList.
     * @param stockList ArrayList<> of StockType objects. There should already be a default "Uncategorised" StockType.
     */
    public StockList(ArrayList<Stock> stockList) {
        initialiseLists();
        this.stockList.addAll(stockList);

        for (Stock stock : stockList) {
            if (!stockTypeList.contains(stock.getStockType())) {
                stockTypeList.add(stock.getStockType());
            }
        }
    }

    /**
     * Constructs a new StockList object with one default StockType, "Uncategorised".
     */
    public StockList() {
        initialiseLists();
    }

    /**
     * Gets a particular StockType of the list based on the index.
     * @param i The index of the stocktype
     * @return the stocktype which the index references
     */
    public Stock get(int i) {
        return stockList.get(i);
    }

    /**
     * Gets the whole stockList. Note: technically doing using this method will violate OOP.
     * @return the list.
     */
    public ArrayList<Stock> getList() {
        return stockList;
    }

    /**
     * Adds a new stocktype to the list of stocktypes.
     * @param name Name of the new stocktype to add.
     */
    public void addStockType(String name) {
        if (!stockTypeList.contains(name)) {
            stockTypeList.add(name);
        }
    }

    //@@author cyanoei
    /**
     * Deletes a StockType object, and all the stocks under it.
     * @param name Name of StockType to be deleted
     * @return The object if it was deleted, null if nothing waas deleted.
     */
    public StockList deleteStockType(String name) {
        StockList deleted = new StockList();
        stockTypeList.remove(name);

        for (Iterator<Stock> it = stockList.iterator() ; it.hasNext();) {
            Stock currStock = it.next();
            if (currStock.getStockType().equals(name)) {
                deleted.addStock(currStock);
                it.remove();
            }
        }

        return deleted;
    }

    //@@author

    /**
     * Returns all stocks corresponding to a stockType specified.
     * @param stockType The unique string of the stockType of Stocks to search for
     * @return ArrayList of Stock objects
     */
    public ArrayList<Stock> getStockType(String stockType) {
        ArrayList<Stock> stocks = new ArrayList<>();
        for (Stock stock : stockList) {
            if (stock.getStockType().equals(stockType)) {
                stocks.add(stock);
            }
        }
        return stocks;
    }

    /**
     * Gets the total number of stockTypes in this stockList. Not to be confused with getStockQuantity.
     * @return the number of stockTypes.
     */
    public int getStockTypeQuantity() { //The number of stockTypes in the list.
        return stockTypeList.size();
    }

    /**
     * Adds a Stock to the list.
     * @param stockType A String matching exactly the StockType to add the new Stock object under.
     * @param stockCode A unique String that identifies the Stock.
     * @param quantity Quantity of the stock.
     * @param description A String describing the nature of the Stock object.
     */
    public void addStock(String stockType, String stockCode, int quantity, String description)
            throws BadInputException {

        stockList.add(new Stock(stockType, stockCode, quantity, description));

    }

    private void addStock(Stock stock) {
        stockList.add(stock);
    }

    //@@author cyanoei
    /**
     * Deletes a Stock object from a list.
     * @param stockCode The unique String that identifies a Stock.
     * @return the stock that was deleted, for printing purposes.
     */
    public Stock deleteStock(String stockCode) {
        Stock deleted = null;

        for (Stock stock : stockList) {
            deleted = stock;
        }

        return deleted;
    }

    /**
     * Formats an error message for the case of editing to a repeated stockCode.
     * @param newStockCode the new stockCode chosen by the user.
     * @return the error message.
     */
    public String repeatedStockCodeOutput(String newStockCode) {
        return String.format("Sorry, the stock code \"%s\" is already assigned to a stock in the system. "
                + "Please enter a different stock code.", newStockCode);
    }

    /**
     * Formats an error message for the case of trying to edit a nonexistent stockCode.
     * @param stockCode the stockCode which does not exist in the system.
     * @return the error message.
     */
    public String nonexistentStockCodeOutput(String stockCode) {
        return String.format("Sorry, the stock code \"%s\" cannot be found in the system. "
                + "Please enter a different stock code.", stockCode);
    }

    //@@author patwaririshab
    /**
     * Edits a stock in the stocklist.
     * @param stockCode The unique String that identifies a Stock.
     * @param property The attribute of the Stock that needs to be modified (Note: for now only 1).
     * @param newValue  The new value of the property we want to edit.
     * @return the stock before edits, for printing purposes.
     */
    public Stock setStock(String stockCode, StockProperty property, String newValue)
            throws BadInputException {
        Stock updatedStock = null;

        //Error: StockCode not found.
        if (!isExistingStockCode(stockCode)) {
            throw new BadInputException(nonexistentStockCodeOutput(stockCode));
        }

        //Error: New StockCode is already used.
        if (property == StockProperty.STOCKCODE && isExistingStockCode(newValue)) {
            throw new BadInputException(repeatedStockCodeOutput(newValue));
        }

        for (Stock stock : stockList) {
            if (stock.getStockCode().equals(stockCode)) {
                updatedStock = stock.setProperty(property, newValue);
                break;
            }
        }
        return updatedStock;
    }

    /**
     * Edits the name of all stocks with the current stockTypeName to a newName.
     * @param stockTypeName The unique String that identifies a StockType.
     * @param newName The newName of the StockType.
     * @return An ArrayList of all stock objects changed.
     */
    public ArrayList<Stock> setStockType(String stockTypeName, String newName) {
        ArrayList<Stock> updated = new ArrayList<>();
        stockTypeList.remove(stockTypeName);
        stockTypeList.add(newName);

        for (Stock stock : stockList) {
            if (stock.getStockType().equals(stockTypeName)) {
                stock.setStockType(newName);
                updated.add(stock);
            }
        }
        return updated;
    }

    /**
     * Gets the total number of stocks in this stockList. This sums the number of stocks across stockTypes.
     * @return the total number of stocks.
     */
    public int getTotalNumberOfStocks() { //The number of stocks in the list, across all stockTypes.
        return stockList.size();
    }

    //@@author cyanoei

    /**
     * Obtains the quantity of a Stock based on its StockCode.
     * @param stockCode the StockCode of the Stock.
     * @return the quantity of the stock, if found, otherwise -1. 
     */
    public int getStockQuantity(String stockCode) {
        Stock stock = findStock(stockCode);
        if (stock == null) {
            return -1;
        } else {
            return stock.getQuantity();
        }
    }


    /**
     * Determines if any of the stocks in this stockList have the same stockCode.
     * @param stockCode the queried stockCode.
     * @return true if a stock in this stockList has that stockCode and false if none of the stocks have this stockCode.
     */
    public boolean isExistingStockCode(String stockCode) {
        for (Stock stock : stockList) {
            if (stock.getStockCode().equals(stockCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the queried stockType already exists in the system.
     * @param stockTypeName the new name for a stockType that the user wants to add/edit.
     * @return true if the stockType is already implemented, false if it is new.
     */
    public boolean isExistingStockType(String stockTypeName) {
        return stockTypeList.contains(stockTypeName);
    }

    /**
     * Searches for a stock in the stockList which has that stockCode.
     * @param stockCode the stockCode being queried.
     * @return the stock in question.
     */
    public Stock findStock(String stockCode) {
        for (Stock stock : stockList) {
            if (stock.getStockCode().equals(stockCode)) {
                return stock;
            }
        }
        return null;
    }

    /**
     * Prints every stock within stocklist whose stocktype matches query. Should only be called by Cli.
     * @pre Stocktype has been checked to exist and contains at least one stock.
     * @return The string of the stocktype whose stocktype matches query.
     */
    public StockList queryStockType(String query) {
        StockList stocks = new StockList();
        for (Stock stock : stockList) {
            if (stock.getStockType().equals(query)) {
                stocks.addStock(stock);
            }
        }
        return stocks;
    }

    /**
     * Checks the entire StockType if any of the stocks contains a description equal to query.
     * @param query The word to search for in the description
     * @return A StockList object of stock objects for the stock whose query is within the description.
     *         If there are no stock which matches, an empty ArrayList is returned.
     *
     */
    public StockList queryStocksDescription(String query) {
        StockList outputList = new StockList();
        for (Stock stock: stockList) {
            if (stock.containDescription(query)) {
                outputList.addStock(stock);
            }
        }
        return outputList;
    }

    //@@author yanprosobo
    /**
     * Returns whether there are any stocktypes stored in the list.
     */
    public boolean isEmpty() {
        return stockList.isEmpty();
    }

    /**
     * Clears the list of all elements.
     */
    public void clearList() {
        initialiseLists();
    }

    /**
     * Checks if a given stocktype has no stocks with its stocktype.
     * @param stockTypeName The name of the stocktype to be queried
     * @return True if the stocktype has no stocks, false otherwise.
     */
    public boolean isStocktypeZeroQuantity(String stockTypeName) {
        for (Stock stock: stockList) {
            if (stock.getStockType().equals(stockTypeName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints all the stocktypes that are currently handled by Eggventory. Should only be called by Cli.
     * @return The string of all the stocktypes
     */
    public String toStocktypeString() {
        ArrayList<String> stockTypes = new ArrayList<>();
        StringBuilder ret = new StringBuilder();
        ret.append("LISTING STOCKTYPES\n");
        for (Stock stock : stockList) {
            if (!stockTypes.contains(stock.getStockType())) {
                stockTypes.add(stock.getStockType());

                ret.append("------------------------\n");
                ret.append(stock.getStockType()).append("\n");
            }
        }
        return ret.toString();
    }

    /**
     * Prints every stock within stocklist. Should only be called by Cli.
     * @return The string of the stocklist.
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        int count = 1;

        for (Stock stock : stockList) {
            ret.append(count++).append(". ");
            ret.append(stock.toString()).append("\n");
        }

        return ret.toString();
    }

    /**
     * Saves the list into a String.
     * @return The String that will be directly saved into file.
     */
    public String saveDetailsString() {
        StringBuilder details = new StringBuilder();
        for (Stock stock: stockList) {
            details.append(stock.saveDetailsString()).append("\n");
        }

        return details.toString();
    }

    //@@author patwaririshab
    /**
     * Saves the stocktypes into a String.
     * @return The String will be directly saved into a saved_stocktypes file.
     */
    public String saveStockTypesString() {
        StringBuilder stockTypesString = new StringBuilder();

        for (String stockType : stockTypeList) {
            stockTypesString.append(stockType).append("\n");
        }
        return stockTypesString.toString();
    }

    //@@author Raghav-B
    /**
     * Returns TableStruct containing data on all stocks contained by StockList. This
     * TableStruct is read by the GUI table.
     * @return TableStruct with data.
     */
    public TableStruct getAllStocksStruct() {
        TableStruct tableStruct = new TableStruct("Stock List");
        tableStruct.setTableColumns("Stock Type", "Stock Code", "Total", "Description", "Minimum", "Loaned");

        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        for (Stock stock : stockList) {
            dataArray.add(stock.getDataAsArray());
        }
        tableStruct.setTableData(dataArray);

        return tableStruct;
    }

    /**
     * Returns TableStruct containing data on all stocktypes contained by StockList. This
     * TableStruct is read by the GUI table.
     * @return TableStruct with data.
     */
    public TableStruct getAllStockTypesStruct() {
        TableStruct tableStruct = new TableStruct("Stocktype List");
        tableStruct.setTableColumns("Stock Type");

        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        for (String stockType: stockTypeList) {
            dataArray.add(new ArrayList<>(Collections.singletonList(stockType)));
        }
        tableStruct.setTableData(dataArray);

        return tableStruct;
    }

    /**
     * Returns TableStruct containing data on all stocks under a specific stocktype. This
     * TableStruct is read by the GUI table.
     * @param stockTypeName Name of stocktype under which all stocks will be listed.
     * @return TableStruct with data.
     */
    public TableStruct getAllStocksInStockTypeStruct(String stockTypeName) {
        TableStruct tableStruct = new TableStruct("Stock List: " + stockTypeName);
        tableStruct.setTableColumns("Stock Type", "Stock Code", "Quantity", "Description", "Minimum", "Loaned");

        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        for (Stock stock : stockList) {
            if (stock.getStockType().equals(stockTypeName)) {
                dataArray.add(stock.getDataAsArray());
            }
        }
        tableStruct.setTableData(dataArray);

        return tableStruct;
    }

    private void initialiseLists() {
        stockList = new ArrayList<>();
        stockTypeList = new ArrayList<>();
        stockTypeList.add("Uncategorised");
    }

}
