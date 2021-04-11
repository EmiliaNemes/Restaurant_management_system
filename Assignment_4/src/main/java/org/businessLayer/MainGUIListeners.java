package org.businessLayer;

import org.main.Main;
import org.presentationLayer.AdministratorGraphicalUserInterface;
import org.presentationLayer.ChefGraphicalUserInterface;
import org.presentationLayer.MainGraphicalUserInterface;
import org.presentationLayer.WaiterGraphicalUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUIListeners {
    private MainGraphicalUserInterface mainInterface;

    public MainGUIListeners(MainGraphicalUserInterface mainInterface) {
        this.mainInterface = mainInterface;
        mainInterface.addAdministratorListener(new AdministratorListener());
        mainInterface.addWaiterListener(new WaiterListener());
        mainInterface.addChefListener(new ChefListener());
    }

    public static class AdministratorListener implements ActionListener {
        private static AdministratorGraphicalUserInterface administratorGUI;
        @Override
        public void actionPerformed(ActionEvent e) {
            administratorGUI = Main.getAdminGUI();
            administratorGUI.getFrame().setVisible(true);
            new AdministratorsListeners(administratorGUI);
        }

        public static AdministratorGraphicalUserInterface getAdministratorGUI() {
            return administratorGUI;
        }
    }

    public static class WaiterListener implements ActionListener {
        private static WaiterGraphicalUserInterface waiterGUI;
        @Override
        public void actionPerformed(ActionEvent e) {
            waiterGUI = Main.getWaiterGUI();
            waiterGUI.getFrame().setVisible(true);
           new WaitersListeners(waiterGUI);
        }

        public static WaiterGraphicalUserInterface getWaiterGUI() {
            return waiterGUI;
        }
    }

    public static class ChefListener implements ActionListener {
        private ChefGraphicalUserInterface chefGUI;
        @Override
        public void actionPerformed(ActionEvent e) {
            chefGUI = Main.getChefGUI();
            chefGUI.getFrame().setVisible(true);
        }

    }
}
