package org.dataLayer;

import org.businessLayer.MenuItem;
import org.businessLayer.Order;
import org.main.Main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
    private static int billContor;

    public void writeInFile(Order order) throws IOException {
        String fileName = "Bill" + billContor + ".txt";
        billContor++;
        java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        file.println("Order: " + order.getOrderID());
        file.println("Date: " + order.getDate());
        file.println("Table: " + order.getTable());
        file.println("");
        file.println("Commanded products:");

        List<MenuItem> menuItemList = Main.getRestaurant().getOrdersHashMap().get(order);
        for (MenuItem item : menuItemList) {
            file.println(item.getProductName() + "      " + item.computePrice());
        }
        file.println("Total price: " + Main.getRestaurant().computePriceForOrder(order));
        file.close();
    }
}
