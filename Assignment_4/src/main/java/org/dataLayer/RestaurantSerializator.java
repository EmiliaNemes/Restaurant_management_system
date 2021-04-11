package org.dataLayer;

import org.businessLayer.Restaurant;
import org.main.Main;

import java.io.*;

public class RestaurantSerializator {
    private Restaurant restaurant;

    public Restaurant deserialize(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.restaurant = Main.getRestaurant();
            this.restaurant = (Restaurant) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Serialized data is red from restaurant.ser");
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return restaurant;
    }

    public void serialize(Restaurant restaurant1){
        try {
            FileOutputStream fileOut = new FileOutputStream("restaurant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(restaurant1);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in restaurant.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
