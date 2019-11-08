package eggventory.logic;

import eggventory.model.LoanList;
import eggventory.model.StockList;
import eggventory.model.items.Stock;
import eggventory.model.items.StockType;
import eggventory.ui.TableStruct;

import java.util.ArrayList;

//@@author cyanoei
public class QuantityManager {

    /**
     * Obtains the loaned quantity of the given stock.
     * @param stock the stock in question.
     * @return the quantity of it that has been loaned.
     */
    private static int getLoanedQuantity(Stock stock) {
        String stockCode = stock.getStockCode();
        int loaned = LoanList.getStockLoanedQuantity(stockCode);
        return loaned;
    }

    /**
     * Checks of minimum required quantity is fulfilled.
     * @param stock the stock to check.
     * @return true if minimum required is met, false otherwise.
     */
    private static boolean isLessThanMinimum(Stock stock) {
        int totalQuantity = stock.getQuantity();
        int loaned = getLoanedQuantity(stock);
        int lost = stock.getLost();
        int minimum = stock.getMinimum();

        return totalQuantity - loaned - lost < minimum;
    }

    /**
     * Calculates the available stock.
     * @param stock the stock to check.
     * @return the calculated value.
     */
    public static int calculateRemaining(Stock stock) {
        int totalQuantity = stock.getQuantity();
        int loaned = getLoanedQuantity(stock);
        int lost = stock.getLost();

        return totalQuantity - loaned - lost;
    }

    /**
     * Checks if minimum required quantity of the stock is fulfilled.
     * @param stock the stock to check.
     * @return the output string if the stock has less than the minimum required, a blank string otherwise.
     */
    public static String checkMinimum(Stock stock) {
        int minimum = stock.getMinimum();
        int newTotal = calculateRemaining(stock);

        if (isLessThanMinimum(stock)) {
            return String.format("\nThe stock \"%s\" is currently below minimum quantity. "
                    + "Available quantity: %d. Minimum quantity: %d.", stock.getDescription(), newTotal, minimum);
        } else {
            return ""; //No need for a warning message if quantity is ok.
        }

    }

    /**
     * Creates a list of all stocks that are below their minimum required quantity.
     * @param list the StockList.
     * @return a string containing the formatted list.
     */
    private static ArrayList<Stock> lessThanMinimumList(StockList list) {
        ArrayList<Stock> minimumList = new ArrayList<>();

        for (StockType stockType : list.getList()) {
            for (Stock stock : stockType.getStockList()) {
                if (isLessThanMinimum(stock)) {
                    minimumList.add(stock);
                }
            }
        }
        return minimumList;
    }

    /**
     * Augments the Stock's toString to show meaningful info about the minimum quantity in CLI.
     * @param stock the Stock.
     * @param loaned the amount loaned.
     * @return the formatted String.
     */
    private static String formatStock(Stock stock, int loaned) {
        return stock.toString() + " | Loaned: " + Integer.toString(loaned);
    }

    /**
     *
     * @param minimumList
     * @return
     */
    private static String lessThanMinimumOutput(ArrayList<Stock> minimumList) {
        String output = "";

        if (minimumList.size() == 0) {
            output = "No stocks are below their minimum quantity! All's good!";
        } else {
            for (Stock stock : minimumList) {
                int loaned = getLoanedQuantity(stock);
                output += formatStock(stock, loaned) + "\n";
            }
        }
        return output; //But this is only the string output. Need the tablestruct also.
    }

    private static TableStruct getLessThanMinimumStocksStruct(ArrayList<Stock> minimumList) {
        TableStruct tableStruct = new TableStruct("List of Stocks below Minimum Quantity");
        tableStruct.setTableColumns("Stock Type", "Stock Code", "Total Quantity", "Description", "Minimum", "Loaned");

        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        for (Stock stock : minimumList) {

            //Add extra info.
            ArrayList<String> stockDescription = stock.getDataAsArray();
            stockDescription.add(String.valueOf(stock.getMinimum()));
            stockDescription.add(String.valueOf(getLoanedQuantity(stock)));

            //Add into the main array.
            dataArray.add(stockDescription);
        }
        tableStruct.setTableData(dataArray);

        return tableStruct;
    }

    public static String printMinimumOutput(StockList list) {
        return lessThanMinimumOutput(lessThanMinimumList(list));
    }

    public static TableStruct printMinimumTable(StockList list) {
        return getLessThanMinimumStocksStruct(lessThanMinimumList(list));
    }


}
