package ui;

import model.Design;
import model.Inventory;
import model.InventoryList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class Database {
    private static final String JSON_STORE = "./data/inventory-list.json";
    private Inventory inventory;
    private InventoryList inventoryList;
    private Design design;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Database() throws FileNotFoundException {
        initialize();
    }

    // EFFECTS: creates an empty inventory and DesignUI for said inventory and initializes the program
    private void initialize() {
        input = new Scanner(System.in);
        inventory = new Inventory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadSaveFile();
    }

    private void loadSaveFile() {
        String answer;

        System.out.println("Would you like to load your previous saved inventory list (if you have one)?");
        answer = input.nextLine().toUpperCase();
        if (answer.equals("YES")) {
            loadFile();
            runDatabase();
        } else if (answer.equals("NO")) {
            inventoryList = new InventoryList();
            runDatabase();
        }
    }

    // MODIFIES: this, inventory
    // EFFECTS: prompts the user to input their name
    private void runDatabase() {
        String name;

        System.out.println("\nEnter your personal/company name: ");
        name = input.nextLine();
        System.out.println("\nYou entered: " + name + ".");
        System.out.println();

        inventory.setCompanyName(name);
        quantity();
    }

    // MODIFIES: this, inventory
    // EFFECTS: prompts the user to input the amount of boxes they want to order
    private void quantity() {
        String quantity;

        System.out.println("How many products would you like to order?: ");
        quantity = input.nextLine();
        System.out.println("\nYou entered: " + quantity + " boxes.");
        System.out.println();

        inventory.setQuantity(Integer.parseInt(quantity));
        shipping();
    }

    // MODIFIES: this, inventory
    // EFFECTS: prompts the user to input the location they would like to ship their product to
    private void shipping() {
        String location;

        System.out.println("Where would you like to ship your product?: ");
        location = input.nextLine();
        System.out.println("\nYou entered: " + location + ".");
        System.out.println();

        inventory.setShippingLocation(location);
        dateOfShipping();
    }

    // MODIFIES: this, inventory
    // EFFECTS: prompts the user to input the date when they would like to receive their boxes
    private void dateOfShipping() {
        String date;

        do {
            System.out.println("When would you like it shipped? [yyyy-MM-dd]");
            date = input.nextLine();
            if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                System.out.println("You chose " + date + ".");
                System.out.println();
            } else {
                System.out.println("You didn't use the correct format. Try again.");
                System.out.println();
            }
        } while (!date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"));
        inventory.setDateOfDelivery(date);
        confirmIdentification();
    }

    // EFFECTS: confirms with the user if their name, quantity, location, and date of delivery is according to
    // what he or the company wants. If not, he/the company is redirected to the beginning, when they have to input
    // their name.
    private void confirmIdentification() {
        String confirmation;
        String purpose;

        System.out.println("\nSo far, you've inputted: ");
        System.out.println("NAME: " + inventory.getCompanyName());
        System.out.println("QUANTITY: " + inventory.getQuantity() + " boxes");
        System.out.println("LOCATION: " + inventory.getShippingLocation());
        System.out.println("DATE OF DELIVERY: " + inventory.getDateOfDelivery());

        do {
            System.out.println("\nDo you confirm with this information? [YES or NO]");
            confirmation = input.nextLine().toUpperCase();
            if (confirmation.equals("NO")) {
                System.out.println();
                System.out.println("Redirecting you to NAME again...");
                runDatabase();
            } else if (!confirmation.equals("YES")) {
                System.out.println("\nThat wasn't a YES or NO answer. Try again.");
            }
        } while (!confirmation.equals("YES") && !confirmation.equals("NO"));
        System.out.println("\nType in a title/purpose for the box. This will help you locate your box in a list.");
        purpose = input.nextLine();
        inventory.setPurpose(purpose);
        customizationFAndBSheet();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the SHEET for the FRONT and BACK side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationFAndBSheet() {
        String sheet;

        design = new Design();
        System.out.println("\nYou are currently customizing the TOP & BOTTOM of your product.");
        do {
            System.out.println("\nPick a SHEET [SINGLE, DOUBLE]: ");
            sheet = input.nextLine().toUpperCase();
            if (!sheet.equals("SINGLE") && !sheet.equals("DOUBLE")) {
                System.out.println("\nThat wasn't SINGLE or DOUBLE. Try again.");
            }
        } while (!sheet.equals("SINGLE") && !sheet.equals("DOUBLE"));
        design.frontAndBackCustomizeSheet(sheet);
        customizationFAndBMaterial();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the MATERIAL for the FRONT and BACK side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationFAndBMaterial() {
        String material;

        do {
            System.out.println("\nPick a MATERIAL [CRAFT, MEDIUM]: ");
            material = input.nextLine().toUpperCase();
            if (!material.equals("CRAFT") && !material.equals("MEDIUM")) {
                System.out.println("\nThat wasn't CRAFT or MEDIUM. Try again.");
            }
        } while (!material.equals("CRAFT") && !material.equals("MEDIUM"));
        design.frontAndBackCustomizeMaterial(material);
        customizationFAndBAreaDensity();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the AREA DENSITY for the FRONT and BACK side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationFAndBAreaDensity() {
        String areaDensity;

        do {
            System.out.println("\nPick an AREA DENSITY (g/m^2) [250, 200, 150, 100]: ");
            areaDensity = input.nextLine();
            if (!areaDensity.equals("250") && !areaDensity.equals("200") && !areaDensity.equals("150")
                    && !areaDensity.equals("100")) {
                System.out.println("\nThat wasn't any of the choices we provided. Try again.");
            }
        } while (!areaDensity.equals("250") && !areaDensity.equals("200") && !areaDensity.equals("150")
                && !areaDensity.equals("100"));
        design.frontAndBackCustomizeAreaDensity(Integer.parseInt(areaDensity));
        confirmCustomizationFAndB();
    }

    // EFFECTS: confirms the customization made for FRONT and BACK side of product. If confirmed, DesignUI will be
    // updated with choices made. If not, customization for FRONT and BACK side will be restarted.
    private void confirmCustomizationFAndB() {
        String finalChoice;

        System.out.println("\nThis is your DesignUI of your product's FRONT and BACK sides are so far: ");
        System.out.println();
        System.out.println("Sheet: " + design.getFrontAndBack().getSheet());
        System.out.println("Material: " + design.getFrontAndBack().getMaterial());
        System.out.println("Area Density: " + design.getFrontAndBack().getAreaDensity() + " g/m^2");
        System.out.println();
        System.out.println("Are you satisfied with this?");
        finalChoice = input.nextLine().toUpperCase();
        if (finalChoice.equals("YES")) {
            customizationTAndBSheet();
        } else if (finalChoice.equals("NO")) {
            customizationFAndBSheet();
        }
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the SHEET for the TOP and BOTTOM side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationTAndBSheet() {
        String sheet;

        System.out.println("\nYou are currently customizing the TOP & BOTTOM of your product.");
        do {
            System.out.println("\nPick a SHEET [SINGLE, DOUBLE]: ");
            sheet = input.nextLine().toUpperCase();
            if (!sheet.equals("SINGLE") && !sheet.equals("DOUBLE")) {
                System.out.println("\nThat wasn't SINGLE or DOUBLE. Try again.");
            }
        } while (!sheet.equals("SINGLE") && !sheet.equals("DOUBLE"));
        design.topAndBottomCustomizeSheet(sheet);
        customizationTAndBMaterial();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the MATERIAL for the TOP and BOTTOM side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationTAndBMaterial() {
        String material;

        do {
            System.out.println("\nPick a MATERIAL [CRAFT, MEDIUM]: ");
            material = input.nextLine().toUpperCase();
            if (!material.equals("CRAFT") && !material.equals("MEDIUM")) {
                System.out.println("\nThat wasn't CRAFT or MEDIUM. Try again.");
            }
        } while (!material.equals("CRAFT") && !material.equals("MEDIUM"));
        design.topAndBottomCustomizeMaterial(material);
        customizationTAndBAreaDensity();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the AREA DENSITY for the TOP and BOTTOM side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationTAndBAreaDensity() {
        String areaDensity;

        do {
            System.out.println("\nPick an AREA DENSITY (g/m^2) [250, 200, 150, 100]: ");
            areaDensity = input.nextLine();
            if (!areaDensity.equals("250") && !areaDensity.equals("200") && !areaDensity.equals("150")
                    && !areaDensity.equals("100")) {
                System.out.println("\nThat wasn't any of the choices we provided. Try again.");
            }
        } while (!areaDensity.equals("250") && !areaDensity.equals("200") && !areaDensity.equals("150")
                && !areaDensity.equals("100"));
        design.topAndBottomCustomizeAreaDensity(Integer.parseInt(areaDensity));
        confirmCustomizationTAndB();
    }

    // EFFECTS: confirms the customization made for TOP and BOTTOM side of product. If confirmed, DesignUI will be
    // updated with choices made. If not, customization for TOP and BOTTOM side will be restarted.
    private void confirmCustomizationTAndB() {
        String finalChoice;
        String sheet = design.getTopAndBottom().getSheet();
        String material = design.getTopAndBottom().getMaterial();
        int areaDensity = design.getTopAndBottom().getAreaDensity();

        System.out.println("\nThis is your DesignUI of your product's TOP and BOTTOM sides are so far: ");
        System.out.println();
        System.out.println("Sheet: " + sheet);
        System.out.println("Material: " + material);
        System.out.println("Area Density: " + areaDensity + " g/m^2");
        System.out.println();
        System.out.println("Are you satisfied with this?");
        finalChoice = input.nextLine().toUpperCase();
        if (finalChoice.equals("YES")) {
            System.out.println();
            customizationLAndRSheet();
        } else if (finalChoice.equals("NO")) {
            customizationTAndBSheet();
        }
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the SHEET for the LEFT and RIGHT side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationLAndRSheet() {
        String sheet;

        System.out.println("\nYou are currently customizing the LEFT & RIGHT of your product.");
        do {
            System.out.println("\nPick a SHEET [SINGLE, DOUBLE]: ");
            sheet = input.nextLine().toUpperCase();
            if (!sheet.equals("SINGLE") && !sheet.equals("DOUBLE")) {
                System.out.println("\nThat wasn't SINGLE or DOUBLE. Try again.");
            }
        } while (!sheet.equals("SINGLE") && !sheet.equals("DOUBLE"));
        design.leftAndRightCustomizeSheet(sheet);
        customizationLAndRMaterial();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the MATERIAL for the LEFT and RIGHT side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationLAndRMaterial() {
        String material;

        do {
            System.out.println("\nPick a MATERIAL [CRAFT, MEDIUM]: ");
            material = input.nextLine().toUpperCase();
            if (!material.equals("CRAFT") && !material.equals("MEDIUM")) {
                System.out.println("\nThat wasn't CRAFT or MEDIUM. Try again.");
            }
        } while (!material.equals("CRAFT") && !material.equals("MEDIUM"));
        design.leftAndRightCustomizeMaterial(material);
        customizationLAndRAreaDensity();
    }

    // MODIFIES: this, DesignUI
    // EFFECTS: customizes the AREA DENSITY for the LEFT and RIGHT side of product.
    // If an invalid answer is given, the UI will prompt the user to try again
    private void customizationLAndRAreaDensity() {
        String areaDensity;

        areaDensity = getAreaDensity();
        design.leftAndRightCustomizeAreaDensity(Integer.parseInt(areaDensity));
        confirmCustomizationLAndR();
    }

    private String getAreaDensity() {
        String areaDensity;
        do {
            System.out.println("\nPick an AREA DENSITY (g/m^2) [250, 200, 150, 100]: ");
            areaDensity = input.nextLine();
            if (!areaDensity.equals("250") && !areaDensity.equals("200") && !areaDensity.equals("150")
                    && !areaDensity.equals("100")) {
                System.out.println("\nThat wasn't any of the choices we provided. Try again.");
            }
        } while (!areaDensity.equals("250") && !areaDensity.equals("200") && !areaDensity.equals("150")
                && !areaDensity.equals("100"));
        return areaDensity;
    }


    // EFFECTS: confirms the customization made for LEFT and RIGHT side of product. If confirmed, DesignUI will be
    // updated with choices made. If not, customization for LEFT and RIGHT side will be restarted.
    private void confirmCustomizationLAndR() {
        String finalChoice;
        do {
            System.out.println("\nThis is your DesignUI of your product's LEFT and RIGHT sides are so far: ");
            System.out.println();
            System.out.println("Sheet: " + design.getLeftAndRight().getSheet());
            System.out.println("Material: " + design.getLeftAndRight().getMaterial());
            System.out.println("Area Density: " + design.getLeftAndRight().getAreaDensity() + " g/m^2");
            System.out.println();
            System.out.println("Are you satisfied with this?");
            finalChoice = input.nextLine().toUpperCase();
            if (finalChoice.equals("YES")) {
                inventory.setDesignOfBox(design);
                finish();
            } else if (finalChoice.equals("NO")) {
                customizationTAndBSheet();
            } else {
                System.out.println("\nThat wasn't a YES or NO answer. Try again.");
            }
        } while (!finalChoice.equals("NO") && !finalChoice.equals("YES"));
    }

    // REQUIRES: answer will be limited to either "YES" or "NO" and if user inputs NO, it assumes user has a save state
    // already made
    // MODIFIES: this, inventoryList
    // EFFECTS: ends the inventory creation process and adds the product to his inventory list. If customer is new, a
    // new inventory list will be made. If not, existing inventory list will be made.
    private void finish() {
        System.out.println("\nYou've finished uploading your product.");
        inventoryList.addToInventoryList(inventory);
        for (Inventory i : inventoryList.getInventoryList()) {
            System.out.println(i.getPurpose());
        }
        addProductQuestion();
    }

    // EFFECTS: confirms product has been added to customer's inventory list and asks if he wants to add another
    // product. If he types "YES," then process starts all over from the beginning again.
    private void addProductQuestion() {
        String choice;
        String result;

        System.out.println("\nWould you like to add another product?");
        choice = input.nextLine().toUpperCase();
        if (choice.equals("YES")) {
            inventory = new Inventory();
            runDatabase();
        } else if (choice.equals("NO")) {
            System.out.println("\nWould you like to save your current list?"
                    + " \nIf you type YES, your previous save file will be overwritten."
                    + " \nIf you type NO, your current inventory list will not be saved.");
            result = input.nextLine().toUpperCase();
            if (result.equals("YES")) {
                saveFile();
                System.out.println("\nThank you for using our service.");
            } else if (result.equals("NO")) {
                System.out.println("\nThank you for using our service.");
            }
        }
    }

    // EFFECTS: saves the inventory
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventoryList);
            jsonWriter.close();
            System.out.println("Your list has been saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFile() {
        try {
            inventoryList = jsonReader.read();
            System.out.println("Loaded your list from our database.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
