package view;

import interface_adapter.PlayThreeViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.WinViewModel;
import interface_adapter.play_card.PlayCardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayThreeView extends JPanel implements ActionListener, PropertyChangeListener {
    private PlayThreeViewModel playThreeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton confirmButton;
    private final JButton cancelButton;
    private final JLabel changeSuit;

    public PlayThreeView(PlayThreeViewModel playThreeViewModel, ViewManagerModel viewManagerModel, JButton cancelButton) {
        this.cancelButton = cancelButton;
        this.viewManagerModel = viewManagerModel;
        setLayout(new GridLayout(1, 4));

        changeSuit = new JLabel("Please select the suit you would like to change the game to!", SwingConstants.CENTER);
        changeSuit.setFont(new Font("Arial", Font.BOLD, 14));
        add(changeSuit, BorderLayout.CENTER);

        confirmButton = new JButton("Confirm New Suit");
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(confirmButton, BorderLayout.CENTER);
        confirmButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(cancelButton, BorderLayout.SOUTH);
        cancelButton.addActionListener(this);

        playThreeViewModel.addPropertyChangeListener(this);

    }

    public PlayThreeView(PlayThreeViewModel playThreeViewModel, ViewManagerModel viewManagerModel, JButton newGameButton, JLabel winMessage, JButton confirmButton, JButton cancelButton, JLabel changeSuit) {
        this.playThreeViewModel = playThreeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.confirmButton = confirmButton;
        this.cancelButton = cancelButton;
        this.changeSuit = changeSuit;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeSuit) {
            viewManagerModel.setActiveView("Turn View");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("winning player")){
            changeSuit.setText("Suit changed to" + playThreeViewModel.getSuit());
        }
    }
}
