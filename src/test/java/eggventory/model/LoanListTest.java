package eggventory.model;

import eggventory.model.loans.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanListTest {

    private final String testStockCode = "R5";
    private final String testMatricNo = "A1";
    private final int testQuantity = 10;

    Loan loan;

    @BeforeEach
    void resetLoanObject() {
        loan = new Loan(testMatricNo, testStockCode, testQuantity);
    }

    @Test
    void deleteLoan_LoanExists_ReturnTrue() {
        LoanList.addLoan(testMatricNo, testStockCode, testQuantity);
        Assertions.assertTrue(LoanList.deleteLoan(testMatricNo, testStockCode));
    }

    @Test
    void deleteLoan_LoanDoesNotExist_ReturnFalse() {
        LoanList.addLoan(testMatricNo, testStockCode, testQuantity);
        Assertions.assertFalse(LoanList.deleteLoan("A2", testStockCode));

        Assertions.assertFalse(LoanList.deleteLoan(testMatricNo, "R1"));

        Assertions.assertFalse(LoanList.deleteLoan("a2", testStockCode));
    }

    @Test
    void getStockLoanedQuantity_StockDoesNotExist_ReturnZero() {
        Assertions.assertEquals(0, LoanList.getStockLoanedQuantity("not stockcode"));
        Assertions.assertEquals(0, LoanList.getStockLoanedQuantity(""));
    }

    @Test
    void getStockLoanedQuantity_StockExists_ReturnQuantity() {
        Assertions.assertEquals(0, LoanList.getStockLoanedQuantity("abc"));
        LoanList.addLoan(testMatricNo, "abc", 100);
        Assertions.assertEquals(100, LoanList.getStockLoanedQuantity("abc"));
    }
}
