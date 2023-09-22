package ui;

import model.InventoryList;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class IntroUI {
    private static final String JSON_STORE = "./data/inventory-list.json";
    private InventoryList inventoryList;
    private JsonReader jsonReader;
    private JFrame introFrame;
    private JPanel panel;

    // EFFECTS: creates a Yes No frame
    public IntroUI() {
        loadQuestion();
    }

    // EFFECTS: creates a frame that asks the user if he wants to load their previous inventory list, with 2 buttons
    // that respectively say "Yes" or "No." If they press yes, they load it. If no, then they make a new inventory list
    public void loadQuestion() {
        jsonReader = new JsonReader(JSON_STORE);
        introFrame = new JFrame("Load Question");
        panel = new JPanel();
        introFrame.setSize(400,300);
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.add(panel);

        JLabel label = new JLabel("Load your previous inventory list?");
        label.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        label.setBounds(85, -25, 250, 250);
        panel.add(label);

        setLoadButtons();

        introFrame.setVisible(true);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: creates the "Yes No" buttons to load the inventory list. Also, both buttons transfer the user to the
    // Identification GUI
    private void setLoadButtons() {
        JButton yes = new JButton("Yes");
        yes.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        yes.setBounds(100, 150, 80, 20);
        yes.addActionListener(e -> {
            loadFile();
            introFrame.dispose();
            new IdentificationUI(inventoryList);
        });
        panel.add(yes);

        JButton no = new JButton("No");
        no.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        no.setBounds(200, 150, 80, 20);
        no.addActionListener(e -> {
            introFrame.dispose();
            new IdentificationUI(new InventoryList());
        });
        panel.add(no);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: loads inventory list from file
    private void loadFile() {
        try {
            inventoryList = jsonReader.read();
            System.out.println("Loaded your list from our database.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
