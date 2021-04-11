package org.main;

import org.businessLayer.MainGUIListeners;
import org.businessLayer.Restaurant;
import org.dataLayer.RestaurantSerializator;
import org.presentationLayer.*;

import java.io.*;

public class Main
{
    private static Restaurant restaurant;
    private static ChefGraphicalUserInterface chefGUI;
    private static WaiterGraphicalUserInterface waiterGUI;
    private static AdministratorGraphicalUserInterface adminGUI;

    public static void main( String[] args ) {
        restaurant = new Restaurant();
        restaurant = new RestaurantSerializator().deserialize(args[0]);
        MainGraphicalUserInterface userInterface = new MainGraphicalUserInterface();
        MainGUIListeners mainGUIListeners = new MainGUIListeners(userInterface);
        chefGUI = new ChefGraphicalUserInterface();
        waiterGUI = new WaiterGraphicalUserInterface();
        adminGUI = new AdministratorGraphicalUserInterface();
        restaurant.addPropertyChangeListener(chefGUI);
    }

    public static Restaurant getRestaurant() {
        return restaurant;
    }
    public static ChefGraphicalUserInterface getChefGUI() {
        return chefGUI;
    }
    public static WaiterGraphicalUserInterface getWaiterGUI() {
        return waiterGUI;
    }
    public static AdministratorGraphicalUserInterface getAdminGUI() {
        return adminGUI;
    }
}