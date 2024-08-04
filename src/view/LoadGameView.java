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
public class LoadGameView extends JPanel implements PropertyChangeListener {

    private final LoadGameController loadGameController;
    private final JList<String> gameList;
    DataAccessInterface dataAccess = new DataAccess("src/data_access/database.txt");

    public LoadGameView(LoadGameController controller, LoadGameViewModel loadGameViewModel) throws IOException {
        this.loadGameController = controller;
        loadGameViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select a game to load");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        List<String> games = dataAccess.loadGames();
        List<String> gameNames = new ArrayList<>();
        for (String game : games) {
            String[] parts = game.split("&");
            if (parts.length > 0) {
                gameNames.add(parts[0] + "              " + parts[parts.length - 1]);
            }
        }
        gameList = new JList<>(gameNames.toArray(new String[0]));
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set the font size of the game list
        gameList.setFont(new Font(gameList.getFont().getName(), gameList.getFont().getStyle(), 18));

        // Add JList to a JScrollPane and add it to the panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        JLabel listTitleLabel = new JLabel("Games in Database");
        listTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        listPanel.add(listTitleLabel, BorderLayout.NORTH);
        listPanel.add(new JScrollPane(gameList), BorderLayout.CENTER);

        add(titleLabel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);

        gameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click detected
                    String selectedValue = gameList.getSelectedValue();
                    if (selectedValue != null) {
                        // Split by any amount of whitespace

                        String[] parts = selectedValue.split(" {14}");
                        if (parts.length > 0) {
                            String selectedGame = parts[0];

                            try {
                                loadGameController.execute(selectedGame.trim());
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("not implemented yet");
    }

    public LoadGameController getController() {
        return this.loadGameController;
    }
}
