package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * View for loading a previous game.
 */
public class LoadGameView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JTextField gameNameInputField = new JTextField(15);
    private final LoadGameController loadGameController;

    private final JButton loadGameButton = new JButton("Load Game");
    DataAccessInterface dataAccess = new DataAccess("src/data_access/database.txt");
    private final JList<String> gameList;

    public LoadGameView(LoadGameController controller, LoadGameViewModel loadGameViewModel) throws IOException {
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

        List<String> games = dataAccess.loadGames();
        List<String> gameNames = new ArrayList<>();
        for (String game : games) {
            String[] parts = game.split(":");
            if (parts.length > 0) {
                gameNames.add(parts[0]);
            }
        }
        gameList = new JList<>(gameNames.toArray(new String[0]));
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add JList to a JScrollPane and add it to the panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        JLabel listTitleLabel = new JLabel("Games in Database");
        listTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        listPanel.add(listTitleLabel, BorderLayout.NORTH);
        listPanel.add(new JScrollPane(gameList), BorderLayout.CENTER);

        add(listPanel, BorderLayout.SOUTH);

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

    public LoadGameController getController() {
        return this.loadGameController;
    }
}
