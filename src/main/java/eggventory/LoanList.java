package eggventory;

import eggventory.loans.Loan;
import eggventory.loans.LoanPair;

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
public class LoanList {
    private ArrayList<LoanPair> loanPairs;
    private HashMap<LoanPair, Loan> loanList;

    public LoanList() {
        loanPairs = new ArrayList<LoanPair>();
        loanList = new HashMap<LoanPair, Loan>();
    }

    /**
     * Adds a Loan object to both the LoanList and LoanPairs.
     * @param stockCode the stockCode of the Stock loaned.
     * @param matricNo the matric number of the Person who is loaning.
     * @param quantity the quantity loaned out.
     * @param loanDate the date the loan was made.
     * @param returnDate the date the loan is to be returned.
     */
    public void addLoan(String stockCode, String matricNo, int quantity, Calendar loanDate, Calendar returnDate) {
        //Add one set of information to the table, and one to the list.
        LoanPair loanPair = new LoanPair(stockCode, matricNo);
        loanPairs.add(loanPair);
        Loan loan = new Loan(stockCode, matricNo, quantity, loanDate, returnDate);
        loanList.put(loanPair,loan);
        //Print something.
    }

    /**
     * Deletes a Loan object from the system. Removes both records of the loan.
     * @param stockCode the stockCode of the Stock.
     * @param matricNo the matric number of the Person.
     */
    public void deleteLoan(String stockCode, String matricNo) {
        //Should first check if the pair is valid.
        //if invalid, throw exception.
        LoanPair loanPair = new LoanPair(stockCode, matricNo);
        loanPairs.remove(loanPair);
        loanList.remove(loanPair);
    }

    /**
     * Returns a list of all the Loans of a single Person.
     * @param matricNo the matric number of the person, used to uniquely identify them.
     * @return the list of all Loans involving that Person.
     */
    private ArrayList<Loan> getPersonLoans(String matricNo) {
        ArrayList<Loan> queriedList = new ArrayList<Loan>();

        for (LoanPair pair : loanPairs) {
            if (pair.getMatricNo().equals(matricNo)) {
                queriedList.add(loanList.get(pair)); //Adds that Loan item, retrieved using the pair as key.
            }
        }
        return queriedList;
    }

    /**
     * Means of obtaining all the Loans of a single type of Stock.
     * @param stockCode the unique identifier of the Stock.
     * @return the list of Loans involving that Stock.
     */
    private ArrayList<Loan> getStockLoans(String stockCode) {
        ArrayList<Loan> queriedList = new ArrayList<Loan>();

        for (LoanPair pair : loanPairs) {
            if (pair.getStockCode().equals(stockCode)) {
                queriedList.add(loanList.get(pair)); //Adds that Loan item, retrieved using the pair as key.
            }
        }
        return queriedList;
    }

    /**
     * Returns a string containing all the loan entries with their details.
     * @return the print string.
     */
    public String printLoans() {
        String output = "Here are all the Loans: \n";

        for (LoanPair loanPair : loanPairs) {
            output += loanList.get(loanPair).toString() + "\n";
        }

        return output;
    }

    /**
     * Returns a string containing all the Loans made by a single Person (identified by matric number).
     * @param matricNo the string that identifies the Person making the Loan.
     * @return the print string.
     */
    public String printPersonLoans(String matricNo) {
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
    public String printStockLoans(String stockCode) {
        String output = "Here are all Loans of " + stockCode + ": \n";

        ArrayList<Loan> loans = getStockLoans(stockCode);
        for (Loan loan : loans) {
            output += loan.toString() + "\n";
        }

        return output;
    }

    //@@author

}
