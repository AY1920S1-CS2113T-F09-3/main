package eggventory.model.items;

import eggventory.commons.enums.StockProperty;

import java.util.ArrayList;

//@@author Deculsion
/**
 * Manages the list of (different types of classes),
 * including all the methods to modify the list:
 * Adding each of the 3 types, print, delete, mark as done, search.
 */

public class StockType {
    private String name;
    private ArrayList<Stock> stocks;


    /**
     * Creates a new StockType object. This overload should only be called from a Storage class.
     * @param name A unique name identifying the StockType.
     * @param savedFile A fully constructed ArrayList of Stock objects.
     */
    public StockType(String name, ArrayList<Stock> savedFile) {
        this.name = name;
        stocks = savedFile;
    }

    /**
     * Creates a new StockType object. StockType should only be instantiated from a StockList class.
     * @param name A unique name identifying the StockType.
     * @param isUniqueStock true if the Stock objects are a UniqueStock.
     */
    public StockType(String name, boolean isUniqueStock) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    /**
     * Creates a new StockType object. StockType should only be instantiated from a StockList class.
     * @param name A unique name identifying the StockType.
     */
    public StockType(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    /**
     * Adds a stock to the stockList.
     * @return True if item was added successfully.
     */
    public boolean addStock(String stockType, String stockCode, int quantity, String description) {
        stocks.add(new CollectiveStock(stockType, stockCode, quantity, description));
        return true;
    }

    /**
     * Deletes a stock of the user's choice.
     *
     * @param stockCode The code of the stock to be deleted.
     * @return true if some stockCode was found and the corresponding stock removed. false if none were found.
     */
    public Stock deleteStock(String stockCode) {

        Stock deletedStock;

        for (Stock stock : stocks) {
            if (stockCode.equals(stock.getStockCode())) {
                deletedStock = stock; //Not sure if this is a copy or not. Assumes unique stockCodes.
                stocks.remove(stock);
                return deletedStock;
            }
        }
        return null;
    }

    /**
     * Accesses and sets a new stockCode of a stock.
     * @param oldStockCode The current stockcode referring to this stock.
     * @param newStockCode The new stockcode to change to.
     */
    public void setStockCode(String oldStockCode, String newStockCode) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(oldStockCode)) {
                stock.setStockCode(newStockCode);
                return;
            }
        }
    }

    /**
     * Accesses and sets the quantity of a Stock.
     * @param stockCode StockCode of the stock to change
     * @param quantity New quantity of the stock to change
     */
    public void setStockQuantity(String stockCode, int quantity) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                stock.setQuantity(quantity);
                return;
            }
        }
    }

    /**
     * Accesses and sets the description of a Stock.
     * @param stockCode StockCode of the stock to change
     * @param description New description of the stock to change
     */
    public void setStockDescription(String stockCode, String description) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                stock.setDescription(description);
                return;
            }
        }
    }

    //@@author
    /**
     * Returns the entire stockList.
     * @return the stockList.
     */
    public ArrayList<Stock> getStockList() {
        return stocks;
    }

    /**
     * Returns a stock of the user's choice.
     * @param i the index of the stock selected.
     */
    public Stock getStock(int i) {
        return stocks.get(i);
    }

    /**
     * Returns a stock of the user's choice.
     * @param stockCode String which uniquely identifies a Stock.
     * @return If Stock exits, the Stock otherwise null.
     */
    public Stock getStock(String stockCode) {
        for (Stock stock: stocks) {
            if (stockCode.equals(stock.getStockCode())) {
                return stock;
            }
        }
        return null;
    }

    //@@author Deculsion
    /**
     * Gets the total number of stocks.
     * @return the number of stocks in this stockType.
     */
    public int getQuantity() {
        return stocks.size();
    }

    /**
     * Gets the name of this stockType.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    //@@author patwaririshab
    /**
     * Updates the name of the StockType.
     * @param newName String which uniquely identifies a StockType.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Updates the values of properties of a Stock.
     * @param stockCode String which uniquely identifies a Stock.
     * @param property The attribute of a Stock we want to update.
     * @param newValue The new value of the attribute to be updated.
     * @return The unedited Stock, for printing purpose.
     */
    public Stock setStock(String stockCode, StockProperty property, String newValue) {
        Stock uneditedStock;
        for (Stock stock: stocks) {
            if (stockCode.equals(stock.getStockCode())) {
                uneditedStock = stock;
                switch (property) {
                case STOCKCODE:
                    stock.setStockCode(newValue);
                    break;
                case QUANTITY:
                    stock.setQuantity(Integer.parseInt(newValue));
                    break;
                case LOANED:
                    stock.setLoaned(Integer.parseInt(newValue));
                    break;
                case LOST:
                    stock.setLost(Integer.parseInt(newValue));
                    break;
                case DESCRIPTION:
                    stock.setDescription(newValue);
                    break;
                case MINIMUM:
                    stock.setMinimum(Integer.parseInt(newValue));
                    break;
                default:
                }
                return uneditedStock;
            }
        }
        return null;
    }

    //@@author cyanoei
    /**
     * Determines if any of the stocks in this stockType have the same stockCode.
     * @param stockCode the queried stockCode.
     * @return true if a stock in this stockType has that stockCode and false if none of the stocks have this stockCode.
     */
    public boolean isExistingStockCode(String stockCode) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                return true;
            }
        }
        return false; //If none of the stocks had the same code.
    }

    //@@author Deculsion
    /**
     * A string of all the stock objects within this stocktype. Should only be called by Cli and StockList.
     * @return A string list of all the stock objects and their details.
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        int i = 1;

        for (Stock stock : stocks) {
            ret.append(String.format("%d. ", i++)).append(stock.toString()).append("\n");
        }

        return ret.toString();

    }

    /**
     * Creates a String of all Stock objects under this StockType.
     * @return The String of all Stock objects.
     */
    public String saveDetailsString() {
        StringBuilder details = new StringBuilder();
        for (Stock stock : stocks) {
            details.append(stock.saveDetailsString()).append("\n");
        }
        return details.toString();
    }

    //@@author Raghav-B
    /**
     * Returns ArrayList of data of all stocks within this stocktype to be read
     * by GUI table.
     * @return ArrayList of data of stocks in this stocktype.
     */
    public ArrayList<ArrayList<String>> getDataAsArray() {
        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        for (Stock stock : stocks) {
            dataArray.add(stock.getDataAsArray());
        }
        return dataArray;
    }
    //@@author
}
