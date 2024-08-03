package app;

import interface_adapter.TurnViewModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * This is the Main file for Phase 2.
 * It creates the views and the controllers.
 */
public class Main {
    public static void main(String[] args) {
        //Create frame
        JFrame application = new JFrame("Crazy Threes");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create panel
        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        application.add(mainPanel);


        //Create view manager model
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        //Create view models
        LoadGameViewModel loadGameViewModel = new LoadGameViewModel();
        TurnViewModel turnViewModel = new TurnViewModel();

        //Create views
        LoadGameView loadGameView = NewGameUseCaseFactory.create(viewManagerModel, turnViewModel, loadGameViewModel);
        TurnView turnView = NewGameUseCaseFactory.create(viewManagerModel, turnViewModel, loadGameView);
        GamePanel gameView = NewGameUseCaseFactory.createNewGame(viewManagerModel);

        // Add views to the main panel
        mainPanel.add(loadGameView, "Load Game");
        mainPanel.add(turnView, "Turn View");
        mainPanel.add(gameView, "New Game");

        // Sets the initial view
        viewManagerModel.setActiveView("New Game");

        //Loads the application
        application.setSize(800, 800);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}


