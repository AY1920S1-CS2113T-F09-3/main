package eggventory.model;

import eggventory.model.loans.Loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

//@@author cyanoei

/**
 * The LoanList class stores:
 * 1) A list of Loan objects containing individual Loan details, and
 * 2) A HashMap mapping StockCode to quantity of Stock loaned out.
 * Adding, editing and deleting loans updates both these lists.
 * Loans can be printed as a complete list of all loans, or only by the Person or Stock involved.
 */
public final class LoanList {

    private static ArrayList<Loan> loansList = new ArrayList<Loan>();
    private static HashMap<String, Integer> stockLoaned = new HashMap<String, Integer>();

    /**
     * Overloaded version of addLoan which does not require Date parameters.
     * Adds a Loan object to both the LoanList and LoanPairs.
     * @param stockCode the stockCode of the Stock loaned.
     * @param matricNo the matric number of the Person who is loaning.
     * @param quantity the quantity loaned out.
     */
    public static void addLoan(String stockCode, String matricNo, int quantity) {
        Loan loan = new Loan(stockCode, matricNo, quantity);

        loansList.add(loan);
        updateStockLoaned(stockCode, quantity);
    }

    /**
     * Adds a Loan object to both the LoanList and LoanPairs.
     * @param stockCode the stockCode of the Stock loaned.
     * @param matricNo the matric number of the Person who is loaning.
     * @param quantity the quantity loaned out.
     * @param loanDate the date the loan was made.
     * @param returnDate the date the loan is to be returned.
     */
    public static void addLoan(String stockCode, String matricNo, int quantity,
                               Calendar loanDate, Calendar returnDate) {
        Loan loan = new Loan(stockCode, matricNo, quantity, loanDate, returnDate);

        loansList.add(loan);
        updateStockLoaned(stockCode, quantity);
    }

    //@@author Deculsion

    /**
     * Deletes a Loan object from the system. Removes both records of the loan.
     * @param stockCode the stockCode of the Stock.
     * @param matricNo the matric number of the Person.
     */
    public static boolean deleteLoan(String stockCode, String matricNo) {
        Loan loan = findLoan(stockCode, matricNo);

        if (loan == null) {
            return false;
        }

        updateStockLoaned(stockCode, loan.getQuantity() * -1);
        loansList.remove(loan);

        return true;
    }

    /**
     * Returns the Loan quantity of a particular queried Loan.
     * @param stockCode the stockCode of the Stock involved.
     * @param matricNo the matric number of the Person involved.
     * @return the quantity of the Stock loaned.
     */
    public static int getLoanQuantity(String stockCode, String matricNo) {
        Loan loan = findLoan(stockCode, matricNo);
        if (loan == null) {
            return -1;
        }
        return loan.getQuantity();
    }

    /**
     * Returns the loaned date of a Loan.
     * @param stockCode the stockCode of the Stock loaned.
     * @param matricNo the matric number of the Person making the Loan.
     * @return the loaned date.
     */
    public static Calendar getStockLoanDate(String stockCode, String matricNo) {
        Loan loan = findLoan(stockCode, matricNo);
        if (loan == null) {
            return null;
        }
        return loan.getLoanDate();
    }

    /**
     * Returns the due date of the Loan.
     * @param stockCode the stockCode of the Stock loaned.
     * @param matricNo the matric number of the Person making the Loan.
     * @return the return date.
     */
    public static Calendar getStockReturnDate(String stockCode, String matricNo) {
        Loan loan = findLoan(stockCode, matricNo);
        if (loan == null) {
            return null;
        }
        return loan.getReturnDate();
    }

    /**
     * Returns the total quantity of a Stock that is on loan.
     * @param stockCode the stockCode of the queried stock.
     * @return the total quantity of that Stock which is on loan.
     */
    public static int getStockLoanedQuantity(String stockCode) {
        return stockLoaned.get(stockCode);
    }


    /**
     * Returns the Loan of a particular Stock to a particular Person.
     * @param stockCode the stockCode of the Stock.
     * @param matricNo the matric number of the Person.
     * @return the Loan object of this particular loan pair.
     */
    private static Loan findLoan(String stockCode, String matricNo) {
        for (Loan loan : loansList) {
            if (loan.loanEquals(stockCode, matricNo)) {
                return loan;
            }
        }

        return null;
    }

    /**
     * Updates the quantity of Stock loaned out. Used when loans are added or deleted/returned.
     * @param stockCode the Stock whose loaned quantity is changed.
     * @param quantity the amount the quantity is changing by.
     */
    private static void updateStockLoaned(String stockCode, int quantity) {
        if (stockLoaned.containsKey(stockCode)) {
            quantity += stockLoaned.get(stockCode);
        }
        stockLoaned.put(stockCode, quantity);
    }

    /**
     * Returns a list of Loans made by a single Person.
     * @param matricNo the matric number of the Person.
     * @return the list.
     */
    private static ArrayList<Loan> getPersonLoans(String matricNo) {
        ArrayList<Loan> loans = new ArrayList<Loan>();
        for (Loan loan : loansList) {
            if (loan.getMatricNo().equals(matricNo)) {
                loans.add(loan);
            }
        }

        return loans;
    }

    /**
     * Means of obtaining all the Loans of a single type of Stock.
     * @param stockCode the unique identifier of the Stock.
     * @return the list of Loans involving that Stock.
     */
    private static ArrayList<Loan> getStockLoans(String stockCode) {
        ArrayList<Loan> loans = new ArrayList<Loan>();
        for (Loan loan : loansList) {
            if (loan.getStockCode().equals(stockCode)) {
                loans.add(loan);
            }
        }

        return loans;

    }

    /**
     * Returns a string containing all the loan entries with their details.
     * @return the print string.
     */
    public static String printLoans() {
        String output = "Here are all the Loans: \n";

        for (Loan loan : loansList) {
            output += loan.toString() + "\n";
        }

        return output;
    }

    /**
     * Returns a string containing all the Loans made by a single Person (identified by matric number).
     * @param matricNo the string that identifies the Person making the Loan.
     * @return the print string.
     */
    public static String printPersonLoans(String matricNo) {
        String output = "Here are all Loans made by " + matricNo + ": \n";

        ArrayList<Loan> loans = getPersonLoans(matricNo);
        for (Loan loan : loans) {
            output += loan.toString() + "\n";
        }

        return output;
    }

    /**
     * Returns a string containing all the Loans of a single stock (identified by stockCode).
     * @param stockCode the string that identifies the Stock loaned.
     * @return the print string.
     */
    public static String printStockLoans(String stockCode) {
        String output = "Here are all Loans of " + stockCode + ": \n";

        ArrayList<Loan> loans = getStockLoans(stockCode);
        for (Loan loan : loans) {
            output += loan.toString() + "\n";
        }

        return output;
    }

    //@@author

}
