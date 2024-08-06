package view;

import interface_adapter.PlayThreeViewModel;
import interface_adapter.ViewManagerModel;
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
    private final JLabel changeSuit;

    public PlayThreeView(PlayThreeViewModel playThreeViewModel, ViewManagerModel viewManagerModel, PlayCardController playCardController) {
        this.viewManagerModel = viewManagerModel;
        setLayout(new GridLayout(1, 4));

        changeSuit = new JLabel("Please select the suit you would like to change the game to!", SwingConstants.CENTER);
        changeSuit.setFont(new Font("Arial", Font.BOLD, 14));
        add(changeSuit, BorderLayout.CENTER);


        PlayThreeViewModel viewModel = new PlayThreeViewModel();
        this.playThreeViewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeSuit) {
            viewManagerModel.setActiveView("Three View");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("current suit")){
            changeSuit.setText("Suit changed to" + playThreeViewModel.getSuit());
        }
    }
}
