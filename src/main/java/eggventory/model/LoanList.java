package eggventory.model;

import eggventory.model.loans.Loan;
import eggventory.model.loans.LoanPair;
import eggventory.ui.TableStruct;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

//@@author cyanoei
/**
 * The LoanList class stores:
 * 1) A list of Loan objects containing individual Loan details, and
 * 2) and a list of pairs showing the relationships between the Stocks and Persons loaning them.
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
        //Add one set of information to the table, and one to the list.
        Loan loan = new Loan(stockCode, matricNo, quantity, loanDate, returnDate);

        loansList.add(loan);
        updateStockLoaned(stockCode, quantity);
    }

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
     * Adds a new stock to track in LoanList.
     * @param stockCode The code of the stock
     * @param quantity The starting quantity of the stock
     */
    public static void addStock(String stockCode, int quantity) {
        updateStockLoaned(stockCode, quantity);
    }

    /**
     * Returns the quantity of a certain Stock that a Person has loaned out.
     * @param stockCode the stockCode of the Stock involved.
     * @param matricNo the matric number of the Person involved.
     * @return the quantity loaned out by a person.
     */
    public static int getPersonLoanQuantity(String stockCode, String matricNo) {
        Loan loan = findLoan(stockCode, matricNo);
        if (loan == null) {
            return -1;
        }
        return loan.getQuantity();
    }

    /**
     * Returns the total quantity of a certain Stock has been loaned out.
     * @param stockCode stockCode of the Stock to check
     * @return The quantity loaned out
     */
    public static int getStockLoanedQuantity(String stockCode) {
        if (stockLoaned.get(stockCode) == null) {
            return -1;
        }
        return stockLoaned.get(stockCode);
    }

    private static Loan findLoan(String stockCode, String matricNo) {
        for (Loan loan : loansList) {
            if (loan.getStockCode().equals(stockCode) && loan.getMatricNo().equals(matricNo)) {
                return loan;
            }
        }

        return null;
    }

    private static void updateStockLoaned(String stockCode, int quantity) {
        if (stockLoaned.containsKey(stockCode)) {
            quantity += stockLoaned.get(stockCode);
        }
        stockLoaned.put(stockCode, quantity);
    }

    private static ArrayList<Loan> getPersonLoans(String matricNo) {
        ArrayList<Loan> loans = new ArrayList<Loan>();
        for (Loan loan : loansList) {
            if (loan.getMatricNo().equals(matricNo)) {
                loans.add(loan);
            }
        }

        return loans;
    }

    //TODO: Add getters for the loan and return dates also.
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

    public static TableStruct getAllLoans() {
        TableStruct dataTable = new TableStruct("Loan list");
        dataTable.setTableColumns("Matric No.", "Name", "StockCode", "Quantity");
    }


    /**
     * Returns all of a person's loans as a TableStruct.
     * @param matricNo Matriculation number of person to output.
     */
    public static TableStruct getPersonLoansStruct(String matricNo) {
        TableStruct dataTable = new TableStruct("Loans of " + matricNo);
        dataTable.setTableColumns("Stock Code", "Quantity Loaned");

        ArrayList<Loan> loans = getPersonLoans(matricNo);
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();

        for (Loan loan : loans) {
            dataList.add(loan.getStockDataAsArray());
        }

        dataTable.setTableData(dataList);

        return dataTable;
    }

    //@@author

}
