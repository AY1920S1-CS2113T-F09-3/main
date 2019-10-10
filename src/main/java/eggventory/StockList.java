package eggventory;

import java.util.ArrayList;

import eggventory.items.Stock;
import eggventory.items.StockType;

public class StockList {
    private ArrayList<StockType>stockList;
    private int quantity;

    /**
     * Constructs a new StockList object using an already existing stockList
     * @param stockList ArrayList<> of StockType objects. There should already be a default "Uncategorised" StockType.
     */
    public StockList(ArrayList<StockType> stockList) {
        this.stockList = stockList;
        this.quantity = stockList.size() - 1;
    }

    /**
     * Constructs a new StockList object with one default StockType, "Uncategorised".
     */
    public StockList() {
        this.stockList = new ArrayList<>();
        this.stockList.add(new StockType("Uncategorised", false));
        this.quantity = 0;
    }

    public void addStockType(String name) {
        stockList.add(new StockType(name, false));
    }

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

    public void deleteStock(String stockCode) {
        for (StockType stockType : stockList) {
            stockType.deleteStock(stockCode);
        }
    }

    public String saveDetailsString() {
        String details = "";

        for (StockType stocktype : stockList) {
            details += stocktype.saveDetailsString() + " ";
        }

        return details;
    }

    public ArrayList<StockType> getList() {
        return stockList;
    }

    public int getQuantity() {
        return quantity;
    }

}
