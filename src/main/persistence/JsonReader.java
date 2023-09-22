package persistence;

import model.Design;
import model.Inventory;
import model.InventoryList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private Inventory inventory;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads InventoryList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public InventoryList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventoryList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory list from JSON object and returns it
    private InventoryList parseInventoryList(JSONObject jsonObject) {
        InventoryList il = new InventoryList();
        addInventoryList(il, jsonObject);
        return il;
    }

    // MODIFIES: InventoryList
    // EFFECTS: parses inventories from JSON inventory and adds them to inventory list
    private void addInventoryList(InventoryList il, JSONObject jsonInventory) {
        JSONArray jsonArray = jsonInventory.getJSONArray("inventoryList");
        for (Object json : jsonArray) {
            JSONObject nextInventory = (JSONObject) json;
            addInventories(il, nextInventory);
        }
    }

    // MODIFIES: Inventory
    // EFFECTS: parses inventory from JSON inventory and adds it to inventory list
    private void addInventories(InventoryList il, JSONObject jsonInventory) {
        String companyName = jsonInventory.getString("companyName");
        int quantity = jsonInventory.getInt("quantity");
        String shipping = jsonInventory.getString("shipping");
        String date = jsonInventory.getString("date");
        String purpose = jsonInventory.getString("purpose");

        Design design = setDesign(jsonInventory);

        inventory = new Inventory();
        inventory.setCompanyName(companyName);
        inventory.setQuantity(quantity);
        inventory.setShippingLocation(shipping);
        inventory.setDateOfDelivery(date);
        inventory.setPurpose(purpose);
        inventory.setDesignOfBox(design);
        il.addToInventoryList(inventory);
    }

    private static Design setDesign(JSONObject jsonInventory) {
        String leftAndRightSheet = jsonInventory.getString("L&RSheet");
        String leftAndRightMaterial = jsonInventory.getString("L&RMaterial");
        int leftAndRightDensity = jsonInventory.getInt("L&RDensity");

        String topAndBottomSheet = jsonInventory.getString("T&BSheet");
        String topAndBottomMaterial = jsonInventory.getString("T&BMaterial");
        int topAndBottomDensity = jsonInventory.getInt("T&BDensity");

        String frontAndBackSheet = jsonInventory.getString("F&BSheet");
        String frontAndBackMaterial = jsonInventory.getString("F&BMaterial");
        int frontAndBackDensity = jsonInventory.getInt("F&BDensity");

        Design design = new Design();

        design.frontAndBackCustomizeSheet(frontAndBackSheet);
        design.frontAndBackCustomizeMaterial(frontAndBackMaterial);
        design.frontAndBackCustomizeAreaDensity(frontAndBackDensity);

        design.leftAndRightCustomizeSheet(leftAndRightSheet);
        design.leftAndRightCustomizeMaterial(leftAndRightMaterial);
        design.leftAndRightCustomizeAreaDensity(leftAndRightDensity);

        design.topAndBottomCustomizeSheet(topAndBottomSheet);
        design.topAndBottomCustomizeMaterial(topAndBottomMaterial);
        design.topAndBottomCustomizeAreaDensity(topAndBottomDensity);
        return design;
    }
}
