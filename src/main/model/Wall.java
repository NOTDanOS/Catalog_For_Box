package model;

public class Wall {
    private String sheet;
    private String material;
    private int areaDensity;

    // EFFECTS: constructs an empty wall with no customization in it
    public Wall() {
        this.sheet = "";
        this.material = "";
        this.areaDensity = 0;
    }

    //getters
    public String getSheet() {
        return this.sheet;
    }

    public String getMaterial() {
        return this.material;
    }

    public int getAreaDensity() {
        return this.areaDensity;
    }

    // REQUIRES: choices are limited to "SINGLE" or "DOUBLE"
    // MODIFIES: this
    // EFFECTS: assigns a sheet wall to the wall
    public void assignSheet(String sheet) {
        if (sheet.equals("SINGLE")  || sheet.equals("DOUBLE")) {
            this.sheet = sheet;
        }
    }

    // REQUIRES: choices are limited to "CRAFT" or "MEDIUM"
    // MODIFIES: this
    // EFFECTS: assigns a material to the wall
    public void assignMaterial(String material) {
        if (material.equals("CRAFT")  || material.equals("MEDIUM")) {
            this.material = material;
        }
    }

    // REQUIRES: choices are limited to 100-250 values
    // MODIFIES: this
    // EFFECTS: assigns a material to the wall
    public void assignAreaDensity(int areaDensity) {
        if (areaDensity >= 100 && areaDensity <= 250) {
            this.areaDensity = areaDensity;
        }
    }
}
