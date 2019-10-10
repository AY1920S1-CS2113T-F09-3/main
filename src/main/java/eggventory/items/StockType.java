package eggventory.items;

import eggventory.items.CollectiveStock;
import eggventory.items.UniqueStock;

import java.util.ArrayList;

import eggventory.exceptions.BadInputException;
import eggventory.items.Stock;
import eggventory.items.CollectiveStock;

/**
 * Manages the list of (different types of classes),
 * including all the methods to modify the list:
 * Adding each of the 3 types, print, delete, mark as done, search.
 */

public class StockType {
    private String name;
    private ArrayList<Stock> stocks;
    private int quantity;
    private boolean isUniqueStock;

    /**
     * Creates a new StockType object. This overload should only be called from a Storage class.
     * @param name A unique name identifying the StockType.
     * @param savedFile A fully constructed ArrayList of Stock objects.
     */
    public StockType(String name, ArrayList<Stock> savedFile) {
        this.name = name;
        stocks = savedFile;
        quantity = savedFile.size();
        isUniqueStock = isUniqueStock();
    }

    /**
     * Creates a new StockType object. StockType should only be instantiated from a StockList class.
     * @param name A unique name identifying the StockType.
     * @param isUniqueStock true if the Stock objects are a UniqueStock.
     */
    public StockType(String name, boolean isUniqueStock) {
        this.name = name;
        this.stocks = new ArrayList<>();
        this.quantity = 0;
        this.isUniqueStock = isUniqueStock;
    }

    /**
     * Creates a new StockType object. StockType should only be instantiated from a StockList class.
     * @param name A unique name identifying the StockType.
     */
    public StockType(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
        this.quantity = 0;
        this.isUniqueStock = false;
    }

    /**
     * Determines whether this StockType is a collection of UniqueStock or CollectiveStock.
     */
    private boolean isUniqueStock() {
        for (Stock stock : stocks) {
            if (stock instanceof CollectiveStock) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a stock to the stockList.
     * @return True if item was added successfully.
     */
    public boolean addStock(String stockType, String stockCode, int quantity, String description) {
        stocks.add(new CollectiveStock(stockType, stockCode, quantity, description));
        this.quantity++;
        return true;
    }

    /**
     * Deletes a stock of the user's choice.
     *
     * @param stockCode The code of the stock to be deleted.
     */
    public void deleteStock(String stockCode) {
        stocks.removeIf(stock -> stock.getStockCode().equals(stockCode));
        this.quantity--;
    }

    /**
     * Creates a String of all Stock objects under this StockType.
     * @return The String of all Stock objects.
     */
    public String saveDetailsString() {
        String details = "";
        for (Stock stock : stocks) {
            details += stock.saveDetailsString() + " ";
        }
        return details;
    }

    /**
     * Returns the entire stockList.
     * @return the stockList.
     */
    public ArrayList<Stock> getStockList() {
        return stocks;
    }

    /**
     * Returns a stock of the user's choice.
     *
     * @param i the index of the stock selected.
     */
    public Stock getStock(int i) {
        return stocks.get(i);
    }

    /**
     * Gets the number of stocks in the stockList.
     * @return the number of stocks in the stockList.
     */
    public int getSize() {
        return stocks.size();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
