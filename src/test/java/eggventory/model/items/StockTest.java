package eggventory.model.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author patwaririshab
class StockTest {
    Stock testStock = new Stock("TestType", "#T",500, "Test");

    @Test
    void testGetStockType_success() {
        assertEquals("TestType", testStock.getStockType());
    }

    @Test
    void testGetStockCode_success() {
        assertEquals("#T", testStock.getStockCode());
    }

    @Test
    void testSetStockCode_ValidStockCode_success() {
        assertEquals("#T", testStock.getStockCode());
        testStock.setStockCode("#TNEW");
        assertEquals("#TNEW", testStock.getStockCode());
    }

    @Test
    void testGetDescription_success() {
        assertEquals("Test", testStock.getDescription());
    }

    @Test
    void testSetDescription_ValidDescription_success() {
        assertEquals("Test", testStock.getDescription());
        testStock.setDescription("NEW Description");
        assertEquals("NEW Description", testStock.getDescription());
    }

    @Test
    void testGetQuantity_success() {
        assertEquals(500,testStock.getQuantity());
    }

    @Test
    void testSetQuantity_success() {
        assertEquals(500, testStock.getQuantity());
    }

    @Test
    void testGetLoaned_haventBeenSetReturnsZero_success() {
        assertEquals(0, testStock.getLoaned());
    }

    @Test
    void testSetLoaned_ValidSetLoan_success() {
        assertEquals(0, testStock.getLoaned());
        testStock.setLoaned(2000);
        assertEquals(2000, testStock.getLoaned());
    }

    @Test
    void testGetLost_haventBeenSetReturnZero_success() {
        assertEquals(0,testStock.getLost());

    }

    @Test
    void testSetLost_ValidSetLost_success() {
        assertEquals(0, testStock.getLost());
        testStock.setLost(2000);
        assertEquals(2000, testStock.getLost());
    }

    @Test
    void testGetMinimum_haventBeenSetReturnZero_success() {
        assertEquals(0,testStock.getMinimum());
    }

    @Test
    void testSetMinimum_ValidNewMinimum_success() {
        assertEquals(0,testStock.getMinimum());
        testStock.setMinimum(500);
        assertEquals(500,testStock.getMinimum());
    }

    @Test
    void testNumAvailable_noLostOrLoaned_returnTotal() {
        assertEquals(500,testStock.numAvailable());

    }

    @Test
    void testNumAvailable_haveLostOrLoaned_returnTotal() {
        assertEquals(500,testStock.numAvailable());
        testStock.setLost(200);
        assertEquals(300,testStock.numAvailable());
        testStock.setLoaned(100);
        assertEquals(200, testStock.numAvailable());
    }

    @Test
    void testToString() {
        assertEquals("TestType | #T | 500 | Test", testStock.toString());
    }

    @Test
    void saveDetailsString() {
        assertEquals("TestType/#T/500/Test/0", testStock.saveDetailsString());
    }

    @Test
    void getDataAsArray() {
    }
}