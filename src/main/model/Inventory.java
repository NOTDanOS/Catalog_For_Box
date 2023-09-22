package model;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inventory {
    private String companyName;
    private String purpose;
    private int quantity;
    private String shippingLocation;
    private LocalDate date;
    private Design designOfBox;

    // EFFECTS: constructs an empty inventory form for company owner to fill
    public Inventory() {
        this.companyName = "";
        this.quantity = 0;
        this.shippingLocation = "";
        this.date = LocalDate.now();
        this.designOfBox = null;
        this.purpose = "";
    }
    // MODIFIES: this
    // EFFECTS: adds a company name

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
        EventLog.getInstance().logEvent(new Event("Personal/company name set by user."));
    }


    // MODIFIES: this
    // EFFECTS: adds/changes quantity of box requested

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        EventLog.getInstance().logEvent(new Event("Quantity of boxes set by user."));
    }


    // MODIFIES: this
    // EFFECTS: adds/changes delivery location of box requested

    public void setShippingLocation(String shippingLocation) {
        this.shippingLocation = shippingLocation;
        EventLog.getInstance().logEvent(new Event("Shipping location set by user."));
    }


    // MODIFIES: this
    // EFFECTS: adds/changes date of delivery for box requested
    public void setDateOfDelivery(String dateOfDelivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(dateOfDelivery, formatter);
        EventLog.getInstance().logEvent(new Event("Date of delivery set by user."));
    }

    // MODIFIES: this
    // EFFECTS: adds/changes DesignUI of box
    public void setDesignOfBox(Design designOfBox) {
        this.designOfBox = designOfBox;
        EventLog.getInstance().logEvent(new Event("Design of box set by user."));
    }

    // MODIFIES: this
    // EFFECTS: adds/changes purpose of box
    public void setPurpose(String purpose) {
        this.purpose = purpose;
        EventLog.getInstance().logEvent(new Event("Purpose of delivery set by user."));
    }

    // EFFECTS: puts the contents of the inventory (except DesignUI) into JSON and returns it
    public JSONObject toJSN() {
        JSONObject jsonInventory = new JSONObject();
        putDesign(jsonInventory);
        putGeneralInformation(jsonInventory);
        return jsonInventory;
    }

    private void putGeneralInformation(JSONObject jsonInventory) {
        jsonInventory.put("companyName", companyName);
        jsonInventory.put("quantity", quantity);
        jsonInventory.put("shipping", shippingLocation);
        jsonInventory.put("date", date);
        jsonInventory.put("purpose", purpose);
    }

    private void putDesign(JSONObject jsonInventory) {
        jsonInventory.put("L&RSheet", designOfBox.getLeftAndRight().getSheet());
        jsonInventory.put("L&RMaterial", designOfBox.getLeftAndRight().getMaterial());
        jsonInventory.put("L&RDensity", designOfBox.getLeftAndRight().getAreaDensity());

        jsonInventory.put("T&BSheet", designOfBox.getTopAndBottom().getSheet());
        jsonInventory.put("T&BMaterial", designOfBox.getTopAndBottom().getMaterial());
        jsonInventory.put("T&BDensity", designOfBox.getTopAndBottom().getAreaDensity());

        jsonInventory.put("F&BSheet", designOfBox.getFrontAndBack().getSheet());
        jsonInventory.put("F&BMaterial", designOfBox.getFrontAndBack().getMaterial());
        jsonInventory.put("F&BDensity", designOfBox.getFrontAndBack().getAreaDensity());
    }

    //getters
    public String getCompanyName() {
        return companyName;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShippingLocation() {
        return shippingLocation;
    }

    public LocalDate getDateOfDelivery() {
        return date;
    }

    public Design getDesignOfBox() {
        return designOfBox;
    }
}
