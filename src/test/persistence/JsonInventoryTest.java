package persistence;

import model.Inventory;
import model.Design;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonInventoryTest {

    protected void checkNameAndPurpose(String companyName, String purpose, Inventory i) {
        assertEquals(companyName, i.getCompanyName());
        assertEquals(purpose, i.getPurpose());
    }

    protected void checkQuantityAndShippingLocation (int quantity, String shippingLocation, Inventory i) {
        assertEquals(quantity, i.getQuantity());
        assertEquals(shippingLocation, i.getShippingLocation());
    }

    protected void checkDesign (Design design, Inventory i) {
        i.setDesignOfBox(design);
        assertEquals(design, i.getDesignOfBox());
    }

    protected void checkDate (String date, Inventory i) {
        i.setDateOfDelivery(date);
        assertEquals(LocalDate.parse(date), i.getDateOfDelivery());
    }
}
