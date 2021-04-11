package org.presentationLayer;

import org.dataLayer.RestaurantSerializator;
import org.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainGraphicalUserInterface extends JFrame {
    JFrame frame;
    private JButton administratorButton     = new JButton("Administrator");
    private JButton waiterButton            = new JButton("Waiter");
    private JButton chefButton              = new JButton("Chef");

    public MainGraphicalUserInterface() {
        frame = new JFrame("System Users");
        frame.setSize(700, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        administratorButton.setPreferredSize(new Dimension(150,50));
        panel.add(administratorButton, c);

        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 1;
        waiterButton.setPreferredSize(new Dimension(150,50));
        panel.add(waiterButton, c);

        c.weightx = 1.0;
        c.gridx = 2;
        c.gridy = 1;
        chefButton.setPreferredSize(new Dimension(150,50));
        panel.add(chefButton, c);

        frame.setContentPane(panel);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {
                new RestaurantSerializator().serialize(Main.getRestaurant());
            }

            @Override
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addAdministratorListener(ActionListener actionListener) {
        administratorButton.addActionListener(actionListener);
    }

    public void addWaiterListener(ActionListener actionListener) {
        waiterButton.addActionListener(actionListener);
    }

    public void addChefListener(ActionListener actionListener) {
        chefButton.addActionListener(actionListener);
    }
}
