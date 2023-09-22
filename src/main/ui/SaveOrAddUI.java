package ui;

import model.InventoryList;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SaveOrAddUI {
    private static final String JSON_STORE = "./data/inventory-list.json";
    private InventoryList inventoryList;
    private JFrame frame;
    private JPanel panel;

    // EFFECTS: creates a panel that asks the user if they'd like to add more products or just end and go to the next UI
    public SaveOrAddUI(InventoryList il) {
        inventoryList = il;
        frame = new JFrame();
        frame.setSize(250, 250);
        panel = new JPanel();
        setSaveButton();
        setAddButton();
        setTableNoSaveButton();
        frame.add(panel);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up a button that allows the user to go back to the Identification UI and start over the process,
    // while also keeping their previous inventory
    private void setAddButton() {
        JButton addButton = new JButton("Add Another One");
        addButton.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        addButton.setBounds(100, 900, 80, 20);
        addButton.addActionListener(e -> {
            saveFile();
            frame.dispose();
            new IdentificationUI(inventoryList);
        });
        panel.add(addButton);
    }


    // MODIFIES: this
    // EFFECTS: sets up a button that allows the user to go forward to a table that displays their entire choices
    // made for their inventory
    private void setSaveButton() {
        JButton saveButton = new JButton("Save & Close");
        saveButton.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        saveButton.setBounds(50, 900, 80, 20);
        saveButton.addActionListener(e -> {
            saveFile();
            frame.dispose();
            new FinalUI(inventoryList);
        });
        panel.add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: sets up a button that allows the user to go forward to a table that displays their entire choices
    // made for their inventory NO SAVE
    private void setTableNoSaveButton() {
        JButton saveButton = new JButton("Don't Save & Close");
        saveButton.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        saveButton.setBounds(150, 900, 80, 20);
        saveButton.addActionListener(e -> {
            frame.dispose();
            new FinalUI(inventoryList);
        });
        panel.add(saveButton);
    }


    // MODIFIES: this, inventoryList
    // EFFECTS: saves inventory list to file
    private void saveFile() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(inventoryList);
            jsonWriter.close();
            System.out.println("Your list has been saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }
}
