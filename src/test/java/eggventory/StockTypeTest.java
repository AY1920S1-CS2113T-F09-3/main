package eggventory;

import eggventory.enums.Property;
import eggventory.items.Stock;
import eggventory.items.StockType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



class StockTypeTest {

    /*
    @Test
    void getStockList() {
        ArrayList<Stock> testList = new ArrayList<>();
        testList.add(new Stock("Resistor", "R50", 500, "Test Resistor"));
        StockType testStockType = new StockType("Resistor");
        testStockType.addStock("Resistor", "R50", 500, "Test Resistor");
        assertEquals(testList, testStockType.getStockList());
    }
    */

    @Test
    void getQuantity() {
        StockType testStockType = new StockType("Resistor");
        assertEquals(0,testStockType.getQuantity());
        testStockType.addStock("Resistor", "R50", 500, "Test Resistor");
        assertEquals(1,testStockType.getQuantity());
    }

    @Test
    void addStock_SUCCESS() {
        StockType testStockType = new StockType("Resistor");
        assertTrue(testStockType.addStock("Resistor", "R50", 500,"Test Resistor"));

    }

    @Test
    void getStock() {
        StockType testStockType = new StockType("Resistor");
        testStockType.addStock("Resistor", "R50", 500, "Test Resistor");
        assertEquals("Test Resistor", testStockType.getStock(0).getDescription());
    }

    @Test
    void deleteStock() {
        ArrayList<Stock> testList = new ArrayList<>();
        testList.add(new Stock("Resistor", "R50", 500, "Test Resistor"));
        StockType testStockType = new StockType("Resistor");
        testStockType.addStock("Resistor", "R50", 500, "Test Resistor");
        assertEquals(1,testStockType.getQuantity());
        testStockType.deleteStock("R50");
        assertEquals(0,testStockType.getQuantity());
    }

    @Test
    void setStock_STOCKCODE_SUCCESS() {
        StockType testStockType = new StockType("testList");
        testStockType.addStock("testList", "R50", 500, "Test Resistor");
        testStockType.setStock("R50", Property.STOCKCODE, "R500");
        assertEquals("R500",testStockType.getStock("R500").getStockCode());
        assertEquals(null, testStockType.getStock("R50"));
    }

    @Test
    void setStock_QUANTITY_SUCCESS() {
        StockType testStockType = new StockType("testList");
        testStockType.addStock("testList", "R50", 500, "Test Resistor");
        testStockType.setStock("R50", Property.QUANTITY, "1950");
        assertEquals(1950, testStockType.getStock("R50").getQuantity());
    }

    @Test
    void setStock_LOANED_SUCCESS() {

    }

    @Test
    void setStock_LOST_SUCCESS() {

    }

    @Test
    void setStock_DESCRIPTION_SUCCESS() {

    }

    @Test
    void setStock_MINIMUM_SUCCESS() {

    }
}