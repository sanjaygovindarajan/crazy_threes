package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.start_game.LoadSuccessViewModel;
import interface_adapter.start_game.StartGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class LoadGameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Load Game";
    public final DataAccessInterface dataAccess = new DataAccess("src/data_access/database.txt");
    private final JTextField gameNameInputField = new JTextField(15);
    private final LoadGameController loadGameController;

    private final JList<String> gameList;
    private final DefaultListModel<String> listModel;
    private final JButton loadGameButton = new JButton("Load Game");

    public LoadGameView(LoadGameController controller, LoadGameViewModel loadGameViewModel) throws IOException {
        this.loadGameController = controller;

        loadGameViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Type the name of the game to load");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Game Name:"));
        inputPanel.add(gameNameInputField);
        inputPanel.add(loadGameButton);

        // Initialize the list model and JList
        listModel = new DefaultListModel<>();
        gameList = new JList<>(listModel);

        for (String game : dataAccess.loadGames()) {
            listModel.addElement(game.split(":")[0]);
        }

        JScrollPane listScrollPane = new JScrollPane(gameList);

        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(listScrollPane, BorderLayout.SOUTH);

        loadGameButton.addActionListener(this);

        // Add key listener to the text field
        gameNameInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loadGame();
                }
            }
        });
    }

    private void loadGame() {
        String gameName = gameNameInputField.getText().trim();

        try {
            loadGameController.execute(gameName); // Call controller method to load the game
            showStartGameView();  // Show StartGameView on successful verification
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid game name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showStartGameView() {
        SwingUtilities.invokeLater(() -> {
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            LoadSuccessViewModel loadSuccessViewModel = new LoadSuccessViewModel();

            StartGameView startGameView = StartGameUseCaseFactory.create(viewManagerModel, loadSuccessViewModel);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();  // Close current frame

            JFrame newFrame = new JFrame("Start Game");
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            newFrame.getContentPane().add(startGameView);  // Add JPanel instead of JFrame
            assert startGameView != null;
            viewManagerModel.setActiveView(startGameView.viewName);
            viewManagerModel.firePropertyChanged();
            newFrame.pack();
            newFrame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadGameButton) {
            loadGame();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("gameList".equals(evt.getPropertyName())) {
            listModel.clear();
            String[] games = (String[]) evt.getNewValue();
            for (String game : games) {
                String gameName = game.split(":")[0];
                listModel.addElement(gameName);
            }
        }
    }
}
