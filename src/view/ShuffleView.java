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
    private final ShuffleController shuffleController;

    /**
     * The view when a player has to shuffle the deck
     * @param shuffleController controller for shuffling the deck
     */
    public ShuffleView(ShuffleController shuffleController) {
        this.shuffleController = shuffleController;

        shuffleButton = new JButton("Shuffle Deck");

        JPanel shufflePanel = new JPanel();
        shufflePanel.add(shuffleButton);
        shufflePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(shufflePanel);
        shuffleButton.addActionListener(this);

    }

    /**
     * Handles buttons being clicked
     * @param e the event to be processed
     */
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
