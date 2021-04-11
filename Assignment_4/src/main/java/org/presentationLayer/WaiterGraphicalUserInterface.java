package org.presentationLayer;

import org.main.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaiterGraphicalUserInterface {
    JFrame frame;
    private JTable orderTable;
    private JTable baseProductTable;
    private JTable compositeProductTable;
    private DefaultTableModel dtmOrder;
    private DefaultTableModel dtmBP;
    private DefaultTableModel dtmCP;
    private JTextField tableTF       = new JTextField(20);
    private JTextField productsTF    = new JTextField(20);
    private JButton addNewOrderBtn   = new JButton("Add Order");
    private JButton computeBillBtn   = new JButton("Compute Bill");

    public WaiterGraphicalUserInterface() {
        frame = new JFrame("Waiter");
        frame.setSize(800, 500);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new JLabel("Order"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(new JLabel("Products: "));
        panel2.add(productsTF);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(new JLabel("Table: "));
        panel3.add(tableTF);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(addNewOrderBtn);
        panel4.add(computeBillBtn);

        JPanel panel5 = new JPanel();
        String[] columns = {"Table", "Data", "Ordered Products"};
        String[][] data = Main.getRestaurant().getOrders();
        dtmOrder = new DefaultTableModel(0,0);
        dtmOrder.setColumnIdentifiers(columns);
        orderTable = new JTable();
        orderTable.setCellSelectionEnabled(false);
        orderTable.setRowSelectionAllowed(true);
        orderTable.setColumnSelectionAllowed(false);
        orderTable.setModel(dtmOrder);
        for(String[] strings: data){
            dtmOrder.addRow(strings);
        }
        panel5.add(new JScrollPane(orderTable));

        JPanel panel6 = new JPanel();
        String[] columns1 = {"Name", "Weight", "Vegetarian", "Price"};
        String[][] data1 = Main.getRestaurant().getBaseProducts();
        dtmBP = new DefaultTableModel(0,0);
        dtmBP.setColumnIdentifiers(columns1);
        baseProductTable = new JTable();
        baseProductTable.setCellSelectionEnabled(false);
        baseProductTable.setRowSelectionAllowed(true);
        baseProductTable.setColumnSelectionAllowed(false);
        baseProductTable.setModel(dtmBP);
        for(String[] strings: data1){
            dtmBP.addRow(strings);
        }
        panel6.add(new JScrollPane(baseProductTable));

        JPanel panel7 = new JPanel();
        String[] columns2 = {"Name", "Composite Products", "Price"};
        String[][] data2 = Main.getRestaurant().getCompositeProducts();
        dtmCP = new DefaultTableModel(0,0);
        dtmCP.setColumnIdentifiers(columns2);
        compositeProductTable = new JTable();
        compositeProductTable.setCellSelectionEnabled(false);
        compositeProductTable.setRowSelectionAllowed(true);
        compositeProductTable.setColumnSelectionAllowed(false);
        compositeProductTable.setModel(dtmCP);
        for(String[] strings: data2){
            dtmCP.addRow(strings);
        }
        panel7.add(new JScrollPane(compositeProductTable));

        JPanel panel8 = new JPanel();
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.Y_AXIS));
        panel8.add(panel5);
        panel8.add(panel6);
        panel8.add(panel7);

        JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout());
        panel9.add(panel1);
        panel9.add(panel2);
        panel9.add(panel3);
        panel9.add(panel4);
        panel9.setLayout(new BoxLayout(panel9, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.add(panel9);
        panel.add(panel8);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public String getTableNumber(){
        return tableTF.getText();
    }

    public String getOrderedProducts(){
        return productsTF.getText();
    }

    public DefaultTableModel getDtmBP() {
        return dtmBP;
    }

    public DefaultTableModel getDtmCP() {
        return dtmCP;
    }

    public DefaultTableModel getDtmOrder() {
        return dtmOrder;
    }

    public void addNewOrderListener(ActionListener actionListener) {
        addNewOrderBtn.addActionListener(actionListener);
    }

    public void computeBillListener(ActionListener actionListener) {
        computeBillBtn.addActionListener(actionListener);
    }
}
