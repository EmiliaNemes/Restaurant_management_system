package org.presentationLayer;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChefGraphicalUserInterface implements PropertyChangeListener {
    JFrame frame;
    JTextArea textArea;

    public ChefGraphicalUserInterface() {
        frame= new JFrame("Chef");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        frame.add(textArea);
        frame.setVisible(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        textArea.append((String)evt.getNewValue());
    }

    public JFrame getFrame() {
        return frame;
    }
}
