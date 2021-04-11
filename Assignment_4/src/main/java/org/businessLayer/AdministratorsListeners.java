package org.businessLayer;

import org.main.Main;
import org.presentationLayer.AdministratorGraphicalUserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AdministratorsListeners {
    private AdministratorGraphicalUserInterface adminGUI;
    private JTable baseProductTable;
    private JTable compositeProductTable;

    public AdministratorsListeners(AdministratorGraphicalUserInterface adminGUI) {
        this.adminGUI = adminGUI;
        baseProductTable = adminGUI.getBaseProductTable();
        compositeProductTable = adminGUI.getCompositeProductTable();

        adminGUI.addBaseProductListener(new BaseProductListener(adminGUI));
        adminGUI.addCompositeProductListener(new CompositeProductListener(adminGUI));
        mouseClickListener();
    }

    private void mouseClickListener() {
        baseProductTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    int row = baseProductTable.getSelectedRow();
                    System.out.println(row);
                    String productName = baseProductTable.getValueAt(row, 0).toString();
                    int weight = Integer.parseInt(baseProductTable.getValueAt(row, 1).toString());
                    boolean vegetarian = Boolean.parseBoolean(baseProductTable.getValueAt(row, 2).toString());
                    float price = Float.parseFloat(baseProductTable.getValueAt(row, 3).toString());
                    MenuItem baseProduct = new BaseProduct(productName, weight, vegetarian, price);
                    adminGUI.editMenuItemListener(new MenuItemEditorListener(adminGUI, baseProduct, row));
                    adminGUI.deleteMenuItemListener(new MenuItemDeleteListener(adminGUI, productName, row));
                }
            }
        });

        compositeProductTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    int row = compositeProductTable.getSelectedRow();
                    String productName = compositeProductTable.getValueAt(row, 0).toString();
                    String compositeProducts = compositeProductTable.getValueAt(row, 1).toString();
                    List<MenuItem> productList = new ArrayList<>();
                    String[] strings = compositeProducts.split(", |,");
                    for(String s: strings){
                        productList.add(Main.getRestaurant().findItemByName(s));
                    }
                    MenuItem compositeProduct = new CompositeProduct(productName, productList);
                    adminGUI.editMenuItemListener(new MenuItemEditorListener(adminGUI, compositeProduct, row));
                    adminGUI.deleteMenuItemListener(new MenuItemDeleteListener(adminGUI, productName, row));
                }
            }
        });
    }


        static class BaseProductListener implements ActionListener {
        private AdministratorGraphicalUserInterface adminGUI;

        public BaseProductListener(AdministratorGraphicalUserInterface adminGUI){
            this.adminGUI = adminGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getBaseProductName();
            int weight = Integer.parseInt(adminGUI.getBaseProductWeight());
            boolean vegetarian;
            if(adminGUI.getBaseProductVegetarian().equals("YES")){
                vegetarian = true;
            } else {
                vegetarian = false;
            }
            float price = Float.parseFloat(adminGUI.getBaseProductPrice());
            Main.getRestaurant().createNewMenuItem(name, weight, vegetarian, price);
        }
    }

    static class CompositeProductListener implements ActionListener {
        private AdministratorGraphicalUserInterface adminGUI;

        public CompositeProductListener(AdministratorGraphicalUserInterface adminGUI){
            this.adminGUI = adminGUI;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getCompositeProductName();
            String products = adminGUI.getComponentProducts();
            List<MenuItem> productList = new ArrayList<>();
            String[] strings = products.split(", |,");
            for(String s: strings){
                productList.add(Main.getRestaurant().findItemByName(s));
            }

            Main.getRestaurant().createCompositeNewMenuItem(name, productList);
        }
    }

    static class MenuItemEditorListener implements ActionListener {
        private AdministratorGraphicalUserInterface adminGUI;
        private MenuItem menuItem;
        private int row;

        public MenuItemEditorListener(AdministratorGraphicalUserInterface adminGUI, MenuItem menuItem, int row){
            this.adminGUI = adminGUI;
            this.menuItem = menuItem;
            this.row = row;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.getRestaurant().editMenuItem(menuItem, row);
        }
    }

    static class MenuItemDeleteListener implements ActionListener {
        private AdministratorGraphicalUserInterface adminGUI;
        private String productName;
        private int row;

        public MenuItemDeleteListener(AdministratorGraphicalUserInterface adminGUI, String productName, int row){
            this.adminGUI = adminGUI;
            this.productName = productName;
            this.row = row;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem item = Main.getRestaurant().findItemByName(productName);
            Main.getRestaurant().deleteMenuItem(item, row);
        }
    }
}
