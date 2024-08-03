package view;

import entity.GameInterface;
import interface_adapter.shuffle.ShuffleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShuffleView  extends JPanel implements ActionListener, PropertyChangeListener {
    private final JButton shuffleButton;

    private ShuffleController shuffleController;

    public ShuffleView(ShuffleController shuffleController) {
        shuffleButton = new JButton("Shuffle Deck");
        JLabel instructions = new JLabel("You cannot shuffle the deck until it is empty.");


        JPanel shufflePanel = new JPanel();
        shufflePanel.add(shuffleButton);
        shufflePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        shuffleButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == shuffleButton){
            shuffleController.execute();
            }
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
