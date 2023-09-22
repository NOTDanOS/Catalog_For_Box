package model;

public class Design {
    private Wall frontAndBack;
    private Wall leftAndRight;
    private Wall topAndBottom;

    // EFFECTS: creates a box with no customization on the front, back,
    // left & right, and top & bottom
    public Design() {
        this.frontAndBack = new Wall();
        this.leftAndRight = new Wall();
        this.topAndBottom = new Wall();
    }

    // MODIFIES: this
    // EFFECTS: customizes the front and back sheet of the box
    public void frontAndBackCustomizeSheet(String sheet) {
        this.frontAndBack.assignSheet(sheet);
        EventLog.getInstance().logEvent(new Event("Sheet for Front and Back wall set/changed by user."));
    }

    // MODIFIES: this
    // EFFECTS: customizes the front and back material of the box
    public void frontAndBackCustomizeMaterial(String material) {
        this.frontAndBack.assignMaterial(material);
        EventLog.getInstance().logEvent(new Event("Material for Front and Back wall set/changed by user."));
    }

    // MODIFIES: this
    // EFFECTS: customizes the front and back area density of the box
    public void frontAndBackCustomizeAreaDensity(int areaDensity) {
        this.frontAndBack.assignAreaDensity(areaDensity);
        EventLog.getInstance().logEvent(new Event("Area Density for Front and Back wall set/changed by user."
        ));
    }

    // MODIFIES: this
    // EFFECTS: customizes the left and right sheet of the box
    public void leftAndRightCustomizeSheet(String sheet) {
        this.leftAndRight.assignSheet(sheet);
        EventLog.getInstance().logEvent(new Event("Sheet for Left and Right wall set/changed by user."));
    }

    // MODIFIES: this
    // EFFECTS: customizes the left and right material of the box
    public void leftAndRightCustomizeMaterial(String material) {
        this.leftAndRight.assignMaterial(material);
        EventLog.getInstance().logEvent(new Event("Material for Left and Right wall set/changed by user."));
    }

    // MODIFIES: this
    // EFFECTS: customizes the left and right area density of the box
    public void leftAndRightCustomizeAreaDensity(int areaDensity) {
        this.leftAndRight.assignAreaDensity(areaDensity);
        EventLog.getInstance().logEvent(new Event("Area Density for Left and Right wall set/changed by user."
        ));
    }

    // MODIFIES: this
    // EFFECTS: customizes the top and bottom sheet of the box
    public void topAndBottomCustomizeSheet(String sheet) {
        this.topAndBottom.assignSheet(sheet);
        EventLog.getInstance().logEvent(new Event("Sheet for Top and Bottom wall set/changed by user."));
    }

    // MODIFIES: this
    // EFFECTS: customizes the top and bottom material of the box
    public void topAndBottomCustomizeMaterial(String material) {
        this.topAndBottom.assignMaterial(material);
        EventLog.getInstance().logEvent(new Event("Material for Top and Bottom wall set/changed by user."));
    }

    // MODIFIES: this
    // EFFECTS: customizes the top and bottom area density of the box
    public void topAndBottomCustomizeAreaDensity(int areaDensity) {
        this.topAndBottom.assignAreaDensity(areaDensity);
        EventLog.getInstance().logEvent(new Event("Area Density for Top and Bottom wall set/changed by user."
        ));
    }

    //getters
    public Wall getFrontAndBack() {
        return this.frontAndBack;
    }

    public Wall getLeftAndRight() {
        return this.leftAndRight;
    }

    public Wall getTopAndBottom() {
        return this.topAndBottom;
    }
}
