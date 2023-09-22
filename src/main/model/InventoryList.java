package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InventoryList {
    private List<Inventory> inventoryList;

    // EFFECTS: creates an empty list of inventory
    public InventoryList() {
        this.inventoryList = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("New inventory list made by user."));
    }

    // MODIFIES: this
    // EFFECTS: adds an inventory to a list of inventory
    public void addToInventoryList(Inventory i) {
        inventoryList.add(i);
        EventLog.getInstance().logEvent(new Event("Inventory added to list with choices set by user."));
    }

    // getters
    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    // EFFECTS: turns inventory list to a JSON array
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventoryList", inventoryListToJson());
        return json;
    }

    // EFFECTS: returns inventory in the inventory list as a JSON array
    private JSONArray inventoryListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Inventory i : inventoryList) {
            jsonArray.put(i.toJSN());
        }

        return jsonArray;
    }
}
