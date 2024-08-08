package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_game.ViewGamesController;
import interface_adapter.view_rules.ReadRulesController;

import javax.swing.*;
import java.awt.*;

/**
 * The initial view the user sees when they run the application.
 */
public class NewGameView extends JPanel {
    private final ViewManagerModel viewManagerModel;
    private final ReadRulesController readRulesController;
    private final ViewGamesController viewGamesController;

    /**
     * Builds a new view based on the view manager model and a controller for reading the rules.
     * @param viewManagerModel The view manager model
     * @param readRulesController The controller for viewing the rules
     */
    public NewGameView(ViewManagerModel viewManagerModel, ReadRulesController readRulesController, ViewGamesController viewGamesController) {
        this.viewManagerModel = viewManagerModel;
        this.readRulesController = readRulesController;
        this.viewGamesController = viewGamesController;
        setLayout(new GridLayout(4, 1));


        JLabel titleLabel = new JLabel("Crazy Threes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 24));
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(new Font("Arial", Font.PLAIN, 24));
        JButton accessRulesButton = new JButton("Access Rules");
        accessRulesButton.setFont(new Font("Arial", Font.PLAIN, 24));

        newGameButton.addActionListener(e -> {
            this.viewManagerModel.setActiveView("Input Players");
        });

        loadGameButton.addActionListener(e -> {
            this.viewGamesController.execute();
        });

        accessRulesButton.addActionListener(e -> {
            this.readRulesController.execute();
        });

        add(newGameButton);
        add(loadGameButton);
        add(accessRulesButton);
    }
}
