package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.*;

/**
 * Represents the view shown when the player wins the game.
 * This panel displays a congratulatory message and a button to start a new game.
 */
public class WinView extends JPanel implements ActionListener, PropertyChangeListener {
    private final WinViewModel winViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton newGameButton;
    private final JLabel winMessage;

    /**
     * Constructs a WinView screen with a congratulatory message and a new game button.
     * @param winViewModel The view model for this view
     * @param viewManagerModel the model that manages the game views and actions
     */
    public WinView(WinViewModel winViewModel, ViewManagerModel viewManagerModel) {
        this.winViewModel = winViewModel;
        this.viewManagerModel = viewManagerModel;
        setLayout(new GridLayout(4, 1));

        winMessage = new JLabel("Congratulations! You won!", SwingConstants.CENTER);
        winMessage.setFont(new Font("Arial", Font.BOLD, 30));
        add(winMessage, BorderLayout.CENTER);

        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(newGameButton, BorderLayout.SOUTH);
        newGameButton.addActionListener(this);

        winViewModel.addPropertyChangeListener(this);

    }

    /**
     * Handles "new game" button being clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            viewManagerModel.setActiveView("New Game");
        }
    }

    /**
     * Updates the congratulatory message when the winner changes.
     * @param evt the property change event containing the new winner's name
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("winning player")){
            winMessage.setText("Congratulations, " + winViewModel.getWinner() + "! You win!");
        }
    }
}
