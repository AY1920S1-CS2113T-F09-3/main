package eggventory.model.loans;

import java.util.Calendar;

//@@author cyanoei
public class Loan {

    private String stockCode;
    private String matricNo;
    private int quantity;
    private Calendar loanDate;
    private Calendar returnDate;

    /**
     * Temporary shorter constructor used to test the non-date attributes.
     * @param stockCode the stockCode of the stock being loaned.
     * @param matricNo the matric number of the person making the loan.
     * @param quantity the quantity being loaned out.
     */
    public Loan(String stockCode, String matricNo, int quantity) {
        //AddLoanCommand should have determined beforehand that the Person and Stock being referred to
        //are existing entries.
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = quantity;
    }

    /**
     * Constructor for Loan class.
     * @param stockCode the stockCode of the stock being loaned.
     * @param matricNo the matric number of the person making the loan.
     * @param quantity the quantity being loaned out.
     * @param loanDate the date the loan was processed.
     * @param returnDate the date the loans have to be returned by.
     */
    public Loan(String stockCode, String matricNo, int quantity, Calendar loanDate, Calendar returnDate) {
        //AddLoanCommand should have determined beforehand that the Person and Stock being referred to
        //are existing entries.
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = quantity;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    /**
     * Gets the stockcode of the Stock being loaned.
     * @return the stockcode.
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Gets the matric number of the person loaning the stock.
     * @return the matric number.
     */
    public String getMatricNo() {
        return matricNo;
    }

    /**
     * Gets the quantity of Stock that this Person loaned.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the date that this Loan was made.
     * @return the loan date.
     */
    public Calendar getLoanDate() {
        return loanDate;
    }

    /**
     * Gets the date that this loan should be returned by.
     * @return the return date.
     */
    public Calendar getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the new quantity of the Stock loaned.
     * @param quantity the new amount that is loaned.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Determines if this Loan matches both the queried Stock and Person.
     * @param stockCode the stockCode of the Stock.
     * @param matricNo the matric number of the Person.
     * @return whether they are both equal.
     */
    public boolean loanEquals(String stockCode, String matricNo) {
        return this.stockCode.equals(stockCode) && this.matricNo.equals(matricNo);
    }

    /**
     * Formats the details of this loan in a user-friendly print statement.
     * @return the string containing the details.
     */
    @Override
    public String toString() {
        return getMatricNo() + " loaned " + getQuantity() + " of " + getStockCode() + ".";
    }

    //@@author


}

