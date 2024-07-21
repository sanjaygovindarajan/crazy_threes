package view;

import data_access.DataAccess;
import interface_adapter.LoadGameController;
import interface_adapter.LoadGameViewModel;
import data_access.DataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class LoadGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Load Game";

    private final JTextField gameNameInputField = new JTextField(15);
    private final LoadGameController loadGameController;
    private final DataAccessInterface dataAccess = new DataAccess();
    private final JButton loadGameButton = new JButton("Load Game");
    private final DefaultListModel<String> gameListModel = new DefaultListModel<>();
    private final JList<String> gameList = new JList<>(gameListModel);

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

        // Add game list below the input panel
        JScrollPane listScrollPane = new JScrollPane(gameList);
        add(listScrollPane, BorderLayout.SOUTH);

        updateGameList(dataAccess.loadGames());
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
        // Handle property change event if needed
        if ("gameList".equals(evt.getPropertyName())) {
            // Update the game list
            updateGameList((List<String>) evt.getNewValue());
        }
    }

    private void updateGameList(List<String> games) {
        gameListModel.clear();
        JLabel saveFilesLabel = new JLabel("Files:");
        saveFilesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameListModel.addElement(saveFilesLabel.getText());
        if (games != null) {
            for (String game : games) {
                String[] parts = game.split(":");
                if (parts.length > 0) {
                    gameListModel.addElement(parts[0].trim());
                }
            }
        }
    }
}

