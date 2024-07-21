package view;

import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Not used in Phase 1
 */
public class LoadGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Load Game";

    private final JTextField gameNameInputField = new JTextField(15);
    private final LoadGameController loadGameController;

    private final JButton loadGameButton = new JButton("Load Game");

    public LoadGameView(LoadGameController controller, LoadGameViewModel loadGameViewModel) {
        this.loadGameController = controller;
        loadGameViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Type the name of the game");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Game Name:"));
        inputPanel.add(gameNameInputField);
        inputPanel.add(loadGameButton);

        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        loadGameButton.addActionListener(this);

        // Add key listener to the text field
        gameNameInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String gameName = gameNameInputField.getText().trim();
                    try {
                        loadGameController.execute(gameName); // Call controller method to load the game
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click if needed
        if (e.getSource() == loadGameButton) {
            String gameName = gameNameInputField.getText().trim();
            try {
                loadGameController.execute(gameName); // Call controller method to load the game
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("not implemented yet");
    }
}
