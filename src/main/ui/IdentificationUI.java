package ui;

import model.Inventory;
import model.InventoryList;

import javax.swing.*;
import java.awt.*;

public class IdentificationUI {
    private JFrame frame;
    private JPanel panel;
    private InventoryList inventoryList;
    private Inventory inventory;

    // EFFECTS: creates an identification form that requires the user to fill out their name, quantity,
    // shipping location, purpose, and date
    public IdentificationUI(InventoryList il) {
        inventoryList = il;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);
        setUpStart();
        buttons(panel);
        setUp();
        panel.repaint();
    }

    // EFFECTS: sets up the frame with a header title, along with the name, quantity, shipping location, purpose,
    // and date, each with their own respective labels, text fields, and buttons for saving.
    public void setUp() {
        inventory = new Inventory();
        setUpStart();
        setUpName();
        setUpQuantity();
        setUpShipping();
        setUpPurpose();
        setUpDate();
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: sets up the header title
    private void setUpStart() {
        JLabel label = new JLabel("IDENTIFICATION FORM");
        label.setFont(new Font("Proxima Nova", Font.PLAIN, 30));
        label.setBounds(175, -150, 400, 400);
        panel.add(label);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: sets up the name label, text field, and save button. If they press the button, their name will be saved
    private void setUpName() {
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        name.setBounds(72, 50, 100, 100);
        panel.add(name);

        JTextField nameTF = new JTextField(20);
        nameTF.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        nameTF.setBounds(250, 90, 200, 25);
        panel.add(nameTF);

        JButton button = new JButton("Save");
        button.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        button.setBounds(500, 90, 80, 20);
        button.addActionListener(e -> inventory.setCompanyName(nameTF.getText()));
        panel.add(button);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: sets up the quantity label, text field, and save button. If they press the button, the quantity
    // requested will be saved
    private void setUpQuantity() {
        JLabel quantity = new JLabel("Quantity:");
        quantity.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        quantity.setBounds(72, 100, 100, 100);
        panel.add(quantity);

        JTextField quantityTF = new JTextField(20);
        quantityTF.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        quantityTF.setBounds(250, 140, 200, 25);
        panel.add(quantityTF);

        JButton button = new JButton("Save");
        button.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        button.setBounds(500, 140, 80, 20);
        button.addActionListener(e -> {
            String quantityTFText = quantityTF.getText();
            int quantity1 = Integer.parseInt(quantityTFText);
            inventory.setQuantity(quantity1);
        });
        panel.add(button);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: sets up the shipping label, text field, and save button. If they press the button, the shipping location
    // requested will be saved
    private void setUpShipping() {
        JLabel shippingLocation = new JLabel("Location:");
        shippingLocation.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        shippingLocation.setBounds(72, 150, 100, 100);
        panel.add(shippingLocation);

        JTextField shippingTF = new JTextField(20);
        shippingTF.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        shippingTF.setBounds(250, 190, 200, 25);
        panel.add(shippingTF);

        JButton button = new JButton("Save");
        button.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        button.setBounds(500, 190, 80, 20);
        button.addActionListener(e -> inventory.setShippingLocation(shippingTF.getText()));
        panel.add(button);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: sets up the purpose label, text field, and save button. If they press the button, their purpose for
    // order requested will be saved
    private void setUpPurpose() {
        JLabel purpose = new JLabel("Purpose:");
        purpose.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        purpose.setBounds(72, 200, 100, 100);
        panel.add(purpose);

        JTextField purposeTF = new JTextField(20);
        purposeTF.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        purposeTF.setBounds(250, 240, 200, 25);
        panel.add(purposeTF);

        JButton button = new JButton("Save");
        button.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        button.setBounds(500, 240, 80, 20);
        button.addActionListener(e -> inventory.setPurpose(purposeTF.getText()));
        panel.add(button);
    }

    // MODIFIES: this, inventoryList
    // EFFECTS: sets up the date label, text field, and save button. If they press the button, their date for shipment
    // will be saved
    private void setUpDate() {
        JLabel date = new JLabel("Date [yyyy-mm-dd]:");
        date.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        date.setBounds(72, 250, 200, 100);
        panel.add(date);

        JTextField dateTF = new JTextField(20);
        dateTF.setFont(new Font("Proxima Nova", Font.PLAIN, 15));
        dateTF.setBounds(250, 290, 200, 25);
        panel.add(dateTF);

        JButton button = new JButton("Save");
        button.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        button.setBounds(500, 290, 80, 20);
        button.addActionListener(e -> inventory.setDateOfDelivery(dateTF.getText()));
        panel.add(button);
    }


    // EFFECTS: helper method that creates a button that moves to the DesignUI UI class
    private void buttons(JPanel panel) {
        ImageIcon next = new ImageIcon("src/main/model/arrow.jpg");
        JButton button = new JButton(next);
        button.setFont(new Font("Proxima Nova", Font.PLAIN, 14));
        button.setBounds(72, 360, 20, 20);
        button.addActionListener(e -> {
            frame.dispose();
            new DesignUI(inventoryList, inventory);
        });
        panel.add(button);
    }
}
