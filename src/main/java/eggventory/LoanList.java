package eggventory;

import eggventory.loans.Loan;
import eggventory.loans.LoanPair;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

//@@author cyanoei
/**
 * LoanList stores both a list of all the loans and their details, and a list of pairs containing
 * the relationships between the Stocks and Persons loaning them.
 * Adding, editing and deleting loans updates both these lists.
 */
public class LoanList {
    private ArrayList<LoanPair> loanTable;
    private Map<LoanPair, Loan> loanList;

    public LoanList() {

    }

    public void addLoan(String stockCode, String matricNo, int quantity, Calendar loanDate, Calendar returnDate) {
        //Add one set of information to the table, and one to the list.
        LoanPair loanPair = new LoanPair(stockCode, matricNo);
        loanTable.add(loanPair);
        loanList.put(loanPair, new Loan(stockCode, matricNo, quantity, loanDate, returnDate);
        //Print something.
    }

    public void deleteLoan(String stockCode, String matricNo) {
        //Should first check if the pair is valid.
        //if invalid, throw exception.
        LoanPair loanPair = new LoanPair(stockCode, matricNo);
        loanTable.remove(loanPair);
        loanList.remove(loanPair);
    }

    public ArrayList<String> getPersonLoans(String person) {
        //for stock/person pair if (person) in the loantable then access the loanlist.
    }

    public ArrayList<String> getStockLoans(String stockCode) {
        //for stock/person pair if (stock) in the loantable then access the loanlist.
    }

}
