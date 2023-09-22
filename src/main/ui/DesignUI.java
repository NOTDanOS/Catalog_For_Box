package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DesignUI {
    private InventoryList inventoryList;
    private Inventory inventory;
    private Design design;
    private JPanel panelFront;
    private JPanel panelTop;
    private JPanel panelLeft;

    // MODIFIES: this, inventory, inventoryList
    // EFFECTS: creates a frame that allows the user to customize their box. It starts with the front and back of the
    // box
    public DesignUI(InventoryList il, Inventory i) {
        inventoryList = il;
        inventory = i;
        design = new Design();
        setFront();
    }

    private static void outputEventLog(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDate() + ": " + event.getDescription());
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a frame that has 3 drop down menus, each with their own respective choices (left most being
    // sheets, middle being material, and right being area density. The frame also has a label that indicates what
    // part of the box they're customizing, and a save button that moves the user to the next part of the box. This
    // time, it's the front and back of the box
    public void setFront() {
        JFrame frameFront = new JFrame();
        JLabel label = new JLabel("Setting Front & Back of Box");
        frameFront.setSize(400,300);
        JButton saveButton = new JButton("Save");

        panelFront = new JPanel();
        sheetFront();
        materialFront();
        densityFront();
        panelFront.add(label);
        panelFront.add(saveButton);

        saveButton.addActionListener(e -> {
            frameFront.dispose();
            setTop();
        });
        frameFront.add(panelFront);
        frameFront.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a frame that has 3 drop down menus, each with their own respective choices (left most being
    // sheets, middle being material, and right being area density. The frame also has a label that indicates what
    // part of the box they're customizing, and a save button that moves the user to the next part of the box. This
    // time, it's the top and bottom of the box
    public void setTop() {
        JFrame frameTop = new JFrame();
        JLabel label = new JLabel("Setting Top & Bottom of Box");
        frameTop.setSize(400,300);
        JButton saveButton = new JButton("Save");

        panelTop = new JPanel();
        sheetTop();
        materialTop();
        densityTop();
        panelTop.add(label);
        panelTop.add(saveButton);

        saveButton.addActionListener(e -> {
            frameTop.dispose();
            setLeft();
        });
        frameTop.add(panelTop);
        frameTop.setVisible(true);
    }

    // MODIFIES: this, inventory, inventoryList
    // EFFECTS: creates a frame that has 3 drop down menus, each with their own respective choices (left most being
    // sheets, middle being material, and right being area density. The frame also has a label that indicates what
    // part of the box they're customizing, and a save button that moves the user to the next part of the box. This
    // time, it's the left and right of the box
    public void setLeft() {
        JFrame frameLeft = new JFrame();
        JLabel label = new JLabel("Setting Left & Right of Box");
        frameLeft.setSize(400,300);
        JButton saveButton = new JButton("Save");

        panelLeft = new JPanel();
        sheetLeft();
        materialLeft();
        densityLeft();
        panelLeft.add(label);
        panelLeft.add(saveButton);

        saveButton.addActionListener(e -> {
            inventory.setDesignOfBox(design);
            inventoryList.addToInventoryList(inventory);
            frameLeft.dispose();
            new SaveOrAddUI(inventoryList);
        });
        frameLeft.add(panelLeft);
        frameLeft.setVisible(true);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the front and back sheet of the box
    public void sheetFront() {
        String[] singleDouble = {"Single", "Double"};
        JComboBox<String> singleDoubleCombo = new JComboBox<>(singleDouble);
        singleDoubleCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                design.frontAndBackCustomizeSheet(e.getItem().toString());
            }
        });
        panelFront.add(singleDoubleCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the front and back material of the box
    public void materialFront() {
        String[] craftMedium = {"Craft", "Medium"};
        JComboBox<String> materialCombo = new JComboBox<>(craftMedium);
        materialCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                design.frontAndBackCustomizeMaterial(e.getItem().toString());
            }
        });
        panelFront.add(materialCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the front and back density of the box
    public void densityFront() {
        Integer[] areaDensity = {250, 200, 150, 100};
        JComboBox<Integer> areaDensityCombo = new JComboBox<>(areaDensity);
        areaDensityCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String stringValue = e.getItem().toString();
                int chosen = Integer.parseInt(stringValue);
                design.frontAndBackCustomizeAreaDensity(chosen);
            }
        });
        panelFront.add(areaDensityCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the top and bottom sheet of the box
    public void sheetTop() {
        String[] singleDouble = {"Single", "Double"};
        JComboBox<String> singleDoubleCombo = new JComboBox<>(singleDouble);
        singleDoubleCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                design.topAndBottomCustomizeSheet(e.getItem().toString());
            }
        });
        panelTop.add(singleDoubleCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the top and bottom material of the box
    public void materialTop() {
        String[] craftMedium = {"Craft", "Medium"};
        JComboBox<String> materialCombo = new JComboBox<>(craftMedium);
        materialCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                design.topAndBottomCustomizeMaterial(e.getItem().toString());
            }
        });
        panelTop.add(materialCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the top and bottom density of the box
    public void densityTop() {
        Integer[] areaDensity = {250, 200, 150, 100};
        JComboBox<Integer> areaDensityCombo = new JComboBox<>(areaDensity);
        areaDensityCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String stringValue = e.getItem().toString();
                int chosen = Integer.parseInt(stringValue);
                design.topAndBottomCustomizeAreaDensity(chosen);
            }
        });
        panelTop.add(areaDensityCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the left and right sheet of the box
    public void sheetLeft() {
        String[] singleDouble = {"Single", "Double"};
        JComboBox<String> singleDoubleCombo = new JComboBox<>(singleDouble);
        singleDoubleCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                design.leftAndRightCustomizeSheet(e.getItem().toString());
            }
        });
        panelLeft.add(singleDoubleCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the left and right material of the box
    public void materialLeft() {
        String[] craftMedium = {"Craft", "Medium"};
        JComboBox<String> materialCombo = new JComboBox<>(craftMedium);
        materialCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                design.leftAndRightCustomizeSheet(e.getItem().toString());
            }
        });
        panelLeft.add(materialCombo);
    }

    // MODIFIES: this, design
    // EFFECTS: helper method that creates the drop-down menu for the left and right density of the box
    public void densityLeft() {
        Integer[] areaDensity = {250, 200, 150, 100};
        JComboBox<Integer> areaDensityCombo = new JComboBox<>(areaDensity);
        areaDensityCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String stringValue = e.getItem().toString();
                int chosen = Integer.parseInt(stringValue);
                design.leftAndRightCustomizeAreaDensity(chosen);
            }
        });
        panelLeft.add(areaDensityCombo);
    }
}
