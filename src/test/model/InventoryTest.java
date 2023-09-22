package model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for Inventory
public class InventoryTest {
    private Inventory testInventory;
    private Design testDesign;

    @BeforeEach
    void runBefore() {
        testInventory = new Inventory();
        testDesign = new Design();
    }

    @Test
    void testConstructor() {
        assertEquals("", testInventory.getCompanyName());
        assertEquals(0, testInventory.getQuantity());
        assertEquals("", testInventory.getShippingLocation());
        assertEquals(LocalDate.now(), testInventory.getDateOfDelivery());

    }

    @Test
    void testSettingAll() {
        testDesign.frontAndBackCustomizeSheet("SINGLE");
        testDesign.frontAndBackCustomizeMaterial("MEDIUM");
        testDesign.frontAndBackCustomizeAreaDensity(250);

        testDesign.leftAndRightCustomizeSheet("SINGLE");
        testDesign.leftAndRightCustomizeMaterial("CRAFT");
        testDesign.leftAndRightCustomizeAreaDensity(100);

        testDesign.topAndBottomCustomizeSheet("SINGLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(100);

        testInventory.setCompanyName("Roblox");
        assertEquals("Roblox", testInventory.getCompanyName());

        testInventory.setQuantity(200);
        assertEquals(200, testInventory.getQuantity());

        testInventory.setShippingLocation("Delta Harbor");
        assertEquals("Delta Harbor", testInventory.getShippingLocation());

        testInventory.setDateOfDelivery("2022-03-04");
        assertEquals(LocalDate.of(2022, 3, 4), testInventory.getDateOfDelivery());

        testInventory.setDesignOfBox(testDesign);
        assertEquals(testDesign, testInventory.getDesignOfBox());
    }

    @Test
    void testChangeName() {
        testInventory.setCompanyName("Roblox");
        assertEquals("Roblox", testInventory.getCompanyName());
        testInventory.setCompanyName("Nestle");
        assertEquals("Nestle", testInventory.getCompanyName());
    }

    @Test
    void testChangeQuantity() {
        testInventory.setQuantity(100);
        assertEquals(100, testInventory.getQuantity());
        testInventory.setQuantity(200);
        assertEquals(200, testInventory.getQuantity());
    }

    @Test
    void testChangeShippingLocation() {
        testInventory.setShippingLocation("California");
        assertEquals("California", testInventory.getShippingLocation());
        testInventory.setShippingLocation("Denmark");
        assertEquals("Denmark", testInventory.getShippingLocation());
    }

    @Test
    void testChangeDate() {
        testInventory.setDateOfDelivery("2020-12-03");
        assertEquals(LocalDate.of(2020, 12, 3), testInventory.getDateOfDelivery());
        testInventory.setDateOfDelivery("2021-01-01");
        assertEquals(LocalDate.of(2021, 1, 1), testInventory.getDateOfDelivery());
    }

    @Test
    void testChangeDesign() {
        testDesign.frontAndBackCustomizeSheet("SINGLE");
        testDesign.frontAndBackCustomizeMaterial("MEDIUM");
        testDesign.frontAndBackCustomizeAreaDensity(250);

        testDesign.leftAndRightCustomizeSheet("SINGLE");
        testDesign.leftAndRightCustomizeMaterial("CRAFT");
        testDesign.leftAndRightCustomizeAreaDensity(100);

        testDesign.topAndBottomCustomizeSheet("SINGLE");
        testDesign.topAndBottomCustomizeMaterial("CRAFT");
        testDesign.topAndBottomCustomizeAreaDensity(100);

        testInventory.setDesignOfBox(testDesign);
        assertEquals(testDesign, testInventory.getDesignOfBox());

        testDesign.frontAndBackCustomizeSheet("DOUBLE");
        testDesign.frontAndBackCustomizeMaterial("CRAFT");
        testDesign.frontAndBackCustomizeAreaDensity(200);

        testDesign.leftAndRightCustomizeSheet("DOUBLE");
        testDesign.leftAndRightCustomizeMaterial("CRAFT");
        testDesign.leftAndRightCustomizeAreaDensity(200);

        testDesign.topAndBottomCustomizeSheet("SINGLE");
        testDesign.topAndBottomCustomizeMaterial("MEDIUM");
        testDesign.topAndBottomCustomizeAreaDensity(150);

        testInventory.setDesignOfBox(testDesign);
        assertEquals(testDesign, testInventory.getDesignOfBox());
    }
}