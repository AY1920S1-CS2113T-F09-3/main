package eggventory.loans;

//@@author cyanoei
/**
 * Data structure to store the pairs of Person and Stock, used as the key for accessing the relevant Loan.
 * For ease of implementation, LoanPairs' two attributes should never be modified.
 */
public class LoanPair {
    private String stockCode;
    private String matricNo;

    public LoanPair(String stockCode, String matricNo) {
        this.stockCode = stockCode;
        this.matricNo = matricNo;
    }

    /**
     * Gets the stockCode of this pair.
     * @return the stockCode.
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Gets the matricNo of this pair.
     * @return the matric number.
     */
    public String getMatricNo() {
        return matricNo;
    }
}
