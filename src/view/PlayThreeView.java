package view;

import interface_adapter.PlayThreeViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_game.ViewGamesController;
import interface_adapter.play_card.PlayCardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for when a player needs to change the suit.
 */
public class PlayThreeView extends JPanel implements ActionListener{
    private final PlayThreeViewModel playThreeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JLabel changeSuitLabel;
    private final PlayCardController playCardController;

    /**
     * Creates this view and initializes the controllers and UI components.
     * @param playThreeViewModel The view model for this view
     * @param viewManagerModel The model managing different views
     * @param playCardController Controller for playing a card
     */
    public PlayThreeView(PlayThreeViewModel playThreeViewModel, ViewManagerModel viewManagerModel, PlayCardController playCardController) {
        this.playThreeViewModel = playThreeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playCardController = playCardController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        changeSuitLabel = new JLabel("Please select the suit you would like to change the game to!", SwingConstants.CENTER);
        changeSuitLabel.setFont(new Font("Arial", Font.BOLD, 14));
        changeSuitLabel.setAlignmentX(CENTER_ALIGNMENT);

        JPanel suitPanel = new JPanel();
        suitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton[] suitButtons = new JButton[4];
        String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};

        for (int i = 0; i < suits.length; i++) {
            suitButtons[i] = new JButton(suits[i]);
            suitButtons[i].setActionCommand(suits[i]);
            suitButtons[i].addActionListener(this);
            suitPanel.add(suitButtons[i]);
        }

        add(changeSuitLabel);
        add(suitPanel);
    }

    /**
     * Handles suit change button being clicked
     * @param e The click event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String suit = e.getActionCommand();
        playCardController.playThree(playThreeViewModel.getThreeSuit(), suit);
    }

    /**
     * Getter method for the controller
     * @return The controller for playing a three
     */
    public PlayCardController getController() {
        return this.playCardController;
    }
}
