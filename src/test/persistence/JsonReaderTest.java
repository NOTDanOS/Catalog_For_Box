package persistence;

import model.InventoryList;
import model.Inventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonInventoryTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            InventoryList il = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventoryList.json");
        try {
            InventoryList il = reader.read();
            assertEquals(0, il.getInventoryList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderStandardInventoryList() {
        JsonReader reader = new JsonReader("./data/testReaderStandardInventoryList.json");
        try {
            InventoryList inventoryList = reader.read();
            List<Inventory> testIl = inventoryList.getInventoryList();
            assertEquals(1, testIl.size());

            checkNameAndPurpose("Tester-1", "Tester-1 boxes to Rio de Janeiro", testIl.get(0));
            checkQuantityAndShippingLocation(200, "Rio de Janeiro", testIl.get(0));
            checkDesign(null, testIl.get(0));
            checkDate("2021-12-09", testIl.get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
