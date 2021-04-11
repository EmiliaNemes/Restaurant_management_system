package org.presentationLayer;

import org.main.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorGraphicalUserInterface {
    private JFrame frame;
    private JTable baseProductTable;
    private JTable compositeProductTable;
    private DefaultTableModel dtmBP;
    private DefaultTableModel dtmCP;
    private JTextField baseProductNameTF        = new JTextField(20);
    private JTextField weightTF                 = new JTextField(20);
    private JTextField vegetarianTF             = new JTextField(20);
    private JTextField baseProductPriceTF       = new JTextField(20);
    private JTextField compositeProductNameTF   = new JTextField(20);
    private JTextField componentProductsTF      = new JTextField(60);
    private JButton addBaseProductBtn           = new JButton("Add Menu Item: Base Product");
    private JButton addCompositeProductBtn      = new JButton("Add Menu Item: Composite Product");
    private JButton editMenuItemBtn             = new JButton("Edit Menu Item");
    private JButton deleteMenuItemBtn           = new JButton("Delete Menu Item");

    public AdministratorGraphicalUserInterface() {
        frame = new JFrame("Administrator");
        frame.setSize(800, 500);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new JLabel("Base Product"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(new JLabel("Product Name: "));
        panel2.add(baseProductNameTF);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(new JLabel("Weight: "));
        panel3.add(weightTF);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(new JLabel("Vegetarian (YES/NO): "));
        panel4.add(vegetarianTF);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout());
        panel5.add(new JLabel("Price: "));
        panel5.add(baseProductPriceTF);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout());
        panel6.add(new JLabel("Composite Product"));

        JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout());
        panel7.add(new JLabel("Product Name: "));
        panel7.add(compositeProductNameTF);

        JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout());
        panel8.add(new JLabel("Component Products: "));
        panel8.add(componentProductsTF);

        JPanel panel9 = new JPanel();
        panel9.setLayout(new FlowLayout());
        panel9.add(addBaseProductBtn);
        panel9.add(addCompositeProductBtn);
        panel9.add(editMenuItemBtn);
        panel9.add(deleteMenuItemBtn);

        JPanel panel10 = new JPanel();
        panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));

        JPanel panel11 = new JPanel();
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
        panel11.add(new JScrollPane(baseProductTable));

        JPanel panel12 = new JPanel();
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
        panel12.add(new JScrollPane(compositeProductTable));

        panel10.add(panel11);
        panel10.add(panel12);

        JPanel panel13 = new JPanel();
        panel13.setLayout(new FlowLayout());
        panel13.add(panel1);
        panel13.add(panel2);
        panel13.add(panel3);
        panel13.add(panel4);
        panel13.add(panel5);
        panel13.add(panel6);
        panel13.add(panel7);
        panel13.add(panel8);
        panel13.add(panel9);
        panel13.setLayout(new BoxLayout(panel13, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.add(panel13);
        panel.add(panel10);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getBaseProductName(){
        return baseProductNameTF.getText();
    }

    public String getBaseProductWeight(){
        return weightTF.getText();
    }

    public String getBaseProductVegetarian(){
        return vegetarianTF.getText();
    }

    public String getBaseProductPrice(){
        return baseProductPriceTF.getText();
    }

    public String getCompositeProductName(){
        return compositeProductNameTF.getText();
    }

    public String getComponentProducts(){
        return componentProductsTF.getText();
    }

    public void setBaseProductName(String string){
        baseProductNameTF.setText(string);
    }

    public void setBaseProductWeight(String string){
        weightTF.setText(string);
    }

    public void setBaseProductVegetarian(String string){
        vegetarianTF.setText(string);
    }

    public void setBaseProductPrice(String string){
        baseProductPriceTF.setText(string);
    }

    public void setCompositeProductName(String string){
        compositeProductNameTF.setText(string);
    }

    public void setComponentProducts(String string){
        componentProductsTF.setText(string);
    }

    public JTable getBaseProductTable() {
        return baseProductTable;
    }

    public JTable getCompositeProductTable() {
        return compositeProductTable;
    }

    public DefaultTableModel getDtmBP() {
        return dtmBP;
    }

    public DefaultTableModel getDtmCP() {
        return dtmCP;
    }

    public void addBaseProductListener(ActionListener actionListener) {
        addBaseProductBtn.addActionListener(actionListener);
    }

    public void addCompositeProductListener(ActionListener actionListener) {
        addCompositeProductBtn.addActionListener(actionListener);
    }

    public void editMenuItemListener(ActionListener actionListener) {
        editMenuItemBtn.addActionListener(actionListener);
    }

    public void deleteMenuItemListener(ActionListener actionListener) {
        deleteMenuItemBtn.addActionListener(actionListener);
    }
}
