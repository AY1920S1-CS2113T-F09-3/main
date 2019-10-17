package eggventory;

import eggventory.items.Stock;
import eggventory.items.StockType;

import java.util.ArrayList;

public class StockList {
    private ArrayList<StockType> stockList;

    /**
     * Constructs a new StockList object using an already existing stockList.
     * @param stockList ArrayList<> of StockType objects. There should already be a default "Uncategorised" StockType.
     */
    public StockList(ArrayList<StockType> stockList) {
        this.stockList = stockList;
    }

    /**
     * Constructs a new StockList object with one default StockType, "Uncategorised".
     */
    public StockList() {
        this.stockList = new ArrayList<>();
        this.stockList.add(new StockType("Uncategorised", false));
    }

    /**
     * Gets the whole stockList. Note: technically doing using this method will violate OOP.
     * @return the list.
     */
    public ArrayList<StockType> getList() {
        return stockList;
    }

    /**
     * Adds a new StockType to the list.
     * @param name Name of new stocktype being added.
     */
    public void addStockType(String name) {
        stockList.add(new StockType(name, false));
    }

    /**
     * Prints every stock within stocklist. Should only be called by Ui.
     * Deletes a StockType object, and all the stocks under it.
     * @param name Name of StockType to be deleted
     * @return The object if it was deleted, null if nothing waas deleted.
     */
    public StockType deleteStockType(String name) {
        StockType deleted;

        for (StockType stocktype : stockList) {
            if (stocktype.getName().equals(name)) {
                deleted = stocktype;
                stockList.remove(stocktype);
                return deleted;
            }
        }

        return null;
    }

    /**
     * Returns a stockType from stockList if it exits else retuns a null StockType.
     * @param stockType The unique string that identifies a stockType
     * @return stockType of stockList
     */
    public StockType getStockType(String stockType) {
        StockType nullType = new StockType("NULL", true);
        for (StockType stType : stockList) {
            if (stType.getName().equals(stockType)) {
                return stType;
            }
        }
        return nullType;
    }

    /**
     * Gets the total number of stockTypes in this stockList. Not to be confused with getStockQuantity.
     * @return the number of stockTypes.
     */
    public int getStockTypeQuantity() { //The number of stockTypes in the list.
        return stockList.size();
    }

    /**
     * Adds a Stock to the specified StockType in the list.
     * @param stockType A String matching exactly the StockType to add the new Stock object under.
     * @param stockCode A unique String that identifies the Stock.
     * @param quantity Quantity of the stock.
     * @param description A String describing the nature of the Stock object.
     */
    public void addStock(String stockType, String stockCode, int quantity, String description) {
        for (StockType listType: stockList) {
            if (listType.getName().equals(stockType)) {
                listType.addStock(stockType, stockCode, quantity, description);
                return;
            }
        }

        // "Uncategorised" is always the first element on stockList.
        stockList.get(0).addStock("Uncategorised", stockCode, quantity, description);
    }

    /**
     * Deletes a Stock object from a list.
     * @param stockCode The unique String that identifies a Stock.
     * @return the stock that was deleted, for printing purposes.
     */
    public Stock deleteStock(String stockCode) {
        Stock deleted;
        for (StockType stockType : stockList) {
            deleted = stockType.deleteStock(stockCode);
            if (deleted !=  null) { //If something WAS deleted
                return deleted;
            }
        }
        return null;
    }

    /**
     * Gets the total number of stocks in this stockList. This sums the number of stocks across stockTypes.
     * @return the total number of stocks.
     */
    public int getStockQuantity() { //The number of stocks in the list, across all stockTypes.
        int total = 0;
        for (StockType stockType : stockList) {
            total += stockType.getQuantity();
        }

        return total;
    }

    /**
     * Prints every stock within stocklist. Should only be called by Cli.
     * @return The string of the stocklist.
     */
    public String toString() {
        String ret = "";
        ret += "CURRENT INVENTORY\n";

        for (StockType stocktype : stockList) {
            ret += "------------------------\n";
            ret += stocktype.toString() + "\n";
        }

        return ret;
    }

    /**
     * Prints every stock within stocklist whose stocktype matches query. Should only be called by Cli.
     * @return The string of the stocklist whose stocktype matches query.
     */
    public String toStocktypeString() {
        String ret = "";
        ret += "QUERY INVENTORY\n";
        for (StockType stocktype : stockList) {
            ret += "------------------------\n";
            ret += stocktype.toString() + "\n";
        }
        return ret;
    }

    /**
     * Prints every stock within stocklist whose stocktype matches query. Should only be called by Cli.
     * @return The string of the stocklist whose stocktype matches query.
     */
    public String toString(String query) {
        String ret = "";
        boolean found = false;
        for (StockType stocktype : stockList) {
            if (stocktype.getName().equals(query)) {
                if (found == false) {
                    ret += "QUERY INVENTORY\n";
                    ret += "------------------------\n";
                    found = true;
                }
                ret += stocktype.toString() + "\n";
            }
        }
        return ret;
    }

    /**
     * Saves the list into a String.
     * @return The String that will be directly saved into file.
     */
    public String saveDetailsString() {
        String details = "";

        for (StockType stocktype : stockList) {
            details += stocktype.saveDetailsString() + "\n";
        }

        return details;
    }

}
