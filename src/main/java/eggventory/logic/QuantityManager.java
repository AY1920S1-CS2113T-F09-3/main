package eggventory.logic;

import eggventory.model.LoanList;
import eggventory.model.items.Stock;

//@@author cyanoei
public class QuantityManager {

    private static boolean isMinimumFulfilled(int totalQuantity, int loaned, int lost, int minimum) {
        return totalQuantity - loaned - lost >= minimum;
    }

    private static int calculateRemaining(int totalQuantity, int loaned, int lost) {
        return totalQuantity - loaned - lost;
    }

    public static String checkMinimum(Stock stock) {
        int totalQuantity = stock.getQuantity();
        int loaned = LoanList.getStockLoanedQuantity(stock.getStockCode());
        int lost = stock.getLost();
        int minimum = stock.getMinimum();

        int newTotal = calculateRemaining(totalQuantity, loaned, lost);

        if (!isMinimumFulfilled(totalQuantity, loaned, lost, minimum)) {
            return String.format("\nThe stock \"%s\" is currently below minimum quantity. "
                    + "Available quantity: %d. Minimum quantity: %d.", stock.getDescription(), newTotal, minimum);
        } else {
            return ""; //No need for a warning message if quantity is ok.
        }

    }

}
