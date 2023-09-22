package persistence;


import model.Design;
import model.Inventory;
import model.InventoryList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonInventoryTest {
    private Design testDesign;
    private Design testDesign2;
    private Inventory testInventory;
    private Inventory testInventory2;

    void setUp() {
        testDesign = new Design();

        testDesign.frontAndBackCustomizeSheet("SINGLE");
        testDesign.frontAndBackCustomizeMaterial("MEDIUM");
        testDesign.frontAndBackCustomizeAreaDensity(150);

        testDesign.leftAndRightCustomizeSheet("DOUBLE");
        testDesign.leftAndRightCustomizeMaterial("MEDIUM");
        testDesign.leftAndRightCustomizeAreaDensity(200);

        testDesign.topAndBottomCustomizeSheet("SINGLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(200);

        testInventory = new Inventory();
        testInventory.setCompanyName("Test");
        testInventory.setPurpose("Testing");
        testInventory.setQuantity(10);
        testInventory.setDateOfDelivery("2020-02-02");
        testInventory.setShippingLocation("Testing Location");
        testInventory.setDesignOfBox(testDesign);

        testDesign2 = new Design();
        testDesign.frontAndBackCustomizeSheet("DOUBLE");
        testDesign.frontAndBackCustomizeMaterial("MEDIUM");
        testDesign.frontAndBackCustomizeAreaDensity(250);

        testDesign.leftAndRightCustomizeSheet("SINGLE");
        testDesign.leftAndRightCustomizeMaterial("CRAFT");
        testDesign.leftAndRightCustomizeAreaDensity(100);

        testDesign.topAndBottomCustomizeSheet("DOUBLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(100);

        testInventory2 = new Inventory();
        testInventory2.setCompanyName("Test2");
        testInventory2.setPurpose("Testing2");
        testInventory2.setQuantity(100);
        testInventory2.setDateOfDelivery("2022-12-22");
        testInventory2.setShippingLocation("Testing Location 2");
        testInventory2.setDesignOfBox(testDesign2);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            InventoryList il = new InventoryList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyInventoryList() {
        try {
            InventoryList il = new InventoryList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventoryList.json");
            writer.open();
            writer.write(il);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventoryList.json");
            il = reader.read();
            assertTrue(il.getInventoryList().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterStandardInventoryList() {
        try {
            setUp();
            InventoryList il = new InventoryList();
            il.addToInventoryList(testInventory);
            il.addToInventoryList(testInventory2);
            JsonWriter writer = new JsonWriter("./data/testWriterStandardInventoryList.json");
            writer.open();
            writer.write(il);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStandardInventoryList.json");
            il = reader.read();
            List<Inventory> testInventoryList = il.getInventoryList();
            checkNameAndPurpose("Test", "Testing", testInventoryList.get(0));
            checkQuantityAndShippingLocation(10, "Testing Location", testInventoryList.get(0));
            checkDate("2020-02-02", testInventoryList.get(0));
            checkDesign(testDesign, testInventoryList.get(0));

            checkNameAndPurpose("Test2", "Testing2", testInventoryList.get(1));
            checkQuantityAndShippingLocation(100, "Testing Location 2", testInventoryList.get(1));
            checkDate("2022-12-22", testInventoryList.get(1));
            checkDesign(testDesign2, testInventoryList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}