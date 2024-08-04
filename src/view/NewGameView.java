package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_rules.ReadRulesController;

import javax.swing.*;
import java.awt.*;

/**
 * The initial view the user sees when they run the application.
 */
public class NewGameView extends JPanel {
    private final ViewManagerModel viewManagerModel;
    private final ReadRulesController readRulesController;

    /**
     * Builds a new view based on the view manager model and a controller for reading the rules.
     * @param viewManagerModel The view manager model
     * @param readRulesController The controller for viewing the rules
     */
    public NewGameView(ViewManagerModel viewManagerModel, ReadRulesController readRulesController) {
        this.viewManagerModel = viewManagerModel;
        this.readRulesController = readRulesController;
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

        newGameButton.addActionListener(_ -> {
            //TODO: update this line after the view of NewGame is implemented
            JOptionPane.showMessageDialog(null, "Not implemented!");
        });

        loadGameButton.addActionListener(_ -> {
            this.viewManagerModel.setActiveView("Load Game");
            this.viewManagerModel.firePropertyChanged();
        });

        accessRulesButton.addActionListener(_ -> {
            this.readRulesController.execute();
        });

        add(newGameButton);
        add(loadGameButton);
        add(accessRulesButton);
    }
}
