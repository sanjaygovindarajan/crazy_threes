package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the view shown when the player wins the game.
 * This panel displays a congratulatory message and a button to start a new game.
 */
public class WinView extends JPanel implements ActionListener{
    private final WinViewModel winViewModel;
    private JButton newGameButton;

    /**
     * Constructs a WinView screen with a congratulatory message and a new game button.
     * @param viewManagerModel the model that manages the game views and actions
     */
    public WinView(ViewManagerModel viewManagerModel) {
        this.winViewModel = winViewModel;
        setLayout(new GridLayout(4, 1));

        JLabel winMessage = new JLabel("Congratulations! You won!", SwingConstants.CENTER);
        winMessage.setFont(new Font("Arial", Font.BOLD, 30));
        add(winMessage, BorderLayout.CENTER);

        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(newGameButton, BorderLayout.SOUTH);
        newGameButton.addActionListener(this);

    }

    /**
     * Handles "new game" button being clicked
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            winViewModel.requestNewGame();
        }
    }

}
