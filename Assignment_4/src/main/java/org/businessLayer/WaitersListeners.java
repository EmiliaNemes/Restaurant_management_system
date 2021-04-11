package org.businessLayer;

import org.main.Main;
import org.presentationLayer.WaiterGraphicalUserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaitersListeners {
    private WaiterGraphicalUserInterface waiterGUI;
    private JTable orderTable;

    public WaitersListeners(WaiterGraphicalUserInterface waiterGUI) {
        this.waiterGUI = waiterGUI;
        orderTable = waiterGUI.getOrderTable();

        waiterGUI.addNewOrderListener(new OrderAdderListener(waiterGUI));
        mouseClickListener();
    }

    private void mouseClickListener() {
        orderTable.setFocusable(false);
        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    int row = orderTable.getSelectedRow();
                    int tableNumber = Integer.parseInt(orderTable.getValueAt(row, 0).toString());
                    waiterGUI.computeBillListener(new BillListener(tableNumber));
                }
            }
        });

    }

    static class OrderAdderListener implements ActionListener {
        private WaiterGraphicalUserInterface waiterGUI;

        public OrderAdderListener(WaiterGraphicalUserInterface waiterGUI) {
            this.waiterGUI = waiterGUI;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int tableNumber = Integer.parseInt(waiterGUI.getTableNumber());
            String products = waiterGUI.getOrderedProducts();
            List<String> productList = new ArrayList<>();
            String[] strings = products.split(", |,");
            for(String s: strings){
                productList.add(s);
            }
            Main.getRestaurant().createNewOrder(tableNumber, productList);
        }
    }


    static class BillListener implements ActionListener {
        private int tableNumber;

        public BillListener(int tableNumber) {
            this.tableNumber = tableNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Order order = Main.getRestaurant().findOrderByTableNumber(tableNumber);
            try {
                Main.getRestaurant().generateBill(order);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
