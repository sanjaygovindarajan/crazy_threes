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
    private JList<String> gameList;
    private final LoadGameViewModel loadGameViewModel;
    private final JPanel listPanel;

    /**
     * Creates a new view for loading a pre-existing game.
     * @param controller: the controller of load game.
     */
    public LoadGameView(LoadGameController controller, LoadGameViewModel loadGameViewModel){
        this.loadGameController = controller;
        this.loadGameViewModel = loadGameViewModel;

        loadGameViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select a game to load");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add JList to a JScrollPane and add it to the panel
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        JLabel listTitleLabel = new JLabel("Games in Database");
        listTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        listPanel.add(listTitleLabel, BorderLayout.NORTH);


        add(titleLabel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() == loadGameViewModel){
            setGames();
        }
    }


    public LoadGameController getController() {
        return this.loadGameController;
    }

    private void setGames(){
        gameList = new JList<>(loadGameViewModel.getGames().toArray(new String[0]));
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set the font size of the game list
        gameList.setFont(new Font(gameList.getFont().getName(), gameList.getFont().getStyle(), 18));
        listPanel.add(new JScrollPane(gameList), BorderLayout.CENTER);

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
}
