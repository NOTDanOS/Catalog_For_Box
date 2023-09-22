package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

public class FinalUI {
    private InventoryList inventoryList;
    private DefaultTableModel tableModel;
    private JTable table;

    // EFFECTS: creates a GUI that displays a table of Name, Quantity, Shipping Location, Date of Arrival, Purpose, and
    // Design code. As a new inventory is added, another row is added to the table. Information on it is unchangeable
    public FinalUI(InventoryList il) {
        JFrame frame = new JFrame();
        frame.setSize(2000, 2000);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        outputEventLog(frame);
        inventoryList = il;
        String[] columnTitles = {"Name", "Quantity", "Shipping Location", "Date of Arrival", "Purpose", "Design"};
        tableModel = new DefaultTableModel(columnTitles, 0);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        setUpTable();
        sortDate();

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
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
    // EFFECTS: sets up the table based on the inventory list given
    private void setUpTable() {

        for (int i = 0; i < inventoryList.getInventoryList().size(); i++) {
            Inventory grabInventory = inventoryList.getInventoryList().get(i);
            String name = grabInventory.getCompanyName();
            int quantity = grabInventory.getQuantity();
            String shippingLocation = grabInventory.getShippingLocation();
            LocalDate date = grabInventory.getDateOfDelivery();
            String purpose = grabInventory.getPurpose();
            Design design = grabInventory.getDesignOfBox();

            Object[] data = {name, quantity, shippingLocation, date, purpose, design};

            tableModel.addRow(data);
        }
    }

    // MODIFIES: this
    // EFFECTS: automatically sorts the table by date in ascending order
    private void sortDate() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndexToSort = 3;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }
}
