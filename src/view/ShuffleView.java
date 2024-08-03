package view;

import entity.Deck;
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
    private final JPanel cannotShufflePanel;

    private ShuffleController shuffleController;
    private GameInterface gameInterface;

    public ShuffleView(JPanel cannotShufflePanel) {
        shuffleButton = new JButton("Shuffle Deck");
        JLabel instructions = new JLabel("You cannot shuffle the deck until it is empty.");


        JPanel shufflePanel = new JPanel();
        shufflePanel.add(shuffleButton);
        shufflePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        shuffleButton.addActionListener(this);


        this.cannotShufflePanel = new JPanel();
        cannotShufflePanel.add(instructions);
        cannotShufflePanel.setVisible(false);

        setLayout(new BorderLayout());
        add(shufflePanel, BorderLayout.NORTH);
        add(cannotShufflePanel, BorderLayout.SOUTH);

        add(this.cannotShufflePanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == shuffleButton){
            Deck currentDeck = gameInterface.getDeck();
            if (currentDeck.getCardList().isEmpty()){
                shuffleController.execute();
                cannotShufflePanel.setVisible(false);
            } else {
                cannotShufflePanel.setVisible(true);
            }
            revalidate();
            repaint();
            }
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
