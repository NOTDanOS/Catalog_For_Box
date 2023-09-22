package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryListTest {
    private Inventory testInventory;
    private Inventory testInventory2;
    private Inventory testInventory3;
    private InventoryList testInventoryList;

    @BeforeEach
    void runBefore(){
        testInventory = new Inventory();
        testInventory.setCompanyName("Roblox");
        testInventory.setDateOfDelivery("2023-02-01");

        testInventory2 = new Inventory();
        testInventory2.setDateOfDelivery("2024-03-02");
        testInventory2.setCompanyName("Roblox-Two");

        testInventory3 = new Inventory();
        testInventory3.setDateOfDelivery("2022-03-02");
        testInventory3.setCompanyName("Electronic Arts");
        testInventoryList = new InventoryList();
    }

    @Test
    void testConstructor() {
        assertEquals (0, testInventoryList.getInventoryList().size());
    }

    @Test
    void testAdding() {
        testInventoryList.addToInventoryList(testInventory);
        assertEquals(1, testInventoryList.getInventoryList().size());
        assertEquals("Roblox", testInventoryList.getInventoryList().get(0).getCompanyName());
    }

    @Test
    void testAddingTwo() {
        testInventoryList.addToInventoryList(testInventory);
        testInventoryList.addToInventoryList(testInventory2);
        assertEquals("Roblox", testInventoryList.getInventoryList().get(0).getCompanyName());
        assertEquals("Roblox-Two", testInventoryList.getInventoryList().get(1).getCompanyName());
        assertEquals(testInventoryList.getInventoryList().get(0).getDesignOfBox(), testInventory.getDesignOfBox());
        assertEquals(testInventoryList.getInventoryList().get(1).getDesignOfBox(), testInventory2.getDesignOfBox());
    }

    @Test
    void testAddingTwoReversed() {
        testInventoryList.addToInventoryList(testInventory2);
        testInventoryList.addToInventoryList(testInventory);
        assertEquals("Roblox-Two", testInventoryList.getInventoryList().get(0).getCompanyName());
        assertEquals(testInventoryList.getInventoryList().get(0).getDesignOfBox(), testInventory2.getDesignOfBox());
        assertEquals(testInventoryList.getInventoryList().get(1).getDesignOfBox(), testInventory.getDesignOfBox());
        assertEquals("Roblox", testInventoryList.getInventoryList().get(1).getCompanyName());
    }

    @Test
    void testAddingThreeRandom() {
        testInventoryList.addToInventoryList(testInventory2);
        testInventoryList.addToInventoryList(testInventory3);
        testInventoryList.addToInventoryList(testInventory);
        assertEquals("Roblox-Two", testInventoryList.getInventoryList().get(0).getCompanyName());
        assertEquals("Electronic Arts", testInventoryList.getInventoryList().get(1).getCompanyName());
        assertEquals("Roblox", testInventoryList.getInventoryList().get(2).getCompanyName());
    }

}
