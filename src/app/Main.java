package app;

import interface_adapter.PlayCardViewModel;
import interface_adapter.TurnViewModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * This is the Main file for Phase 2.
 * See the Main file for Phase 1 in the app package.
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


        //Create view manager
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(mainPanel, cardLayout, viewManagerModel);


        //Create view models
        LoadGameViewModel loadGameViewModel = new LoadGameViewModel();
        TurnViewModel turnViewModel = new TurnViewModel();
        PlayCardViewModel playCardViewModel = new PlayCardViewModel();

        //Create views
        LoadGameView loadGameView = NewGameUseCaseFactory.create(viewManagerModel, loadGameViewModel);
        TurnView turnView = NewGameUseCaseFactory.create(viewManagerModel, turnViewModel, loadGameView);
        PlayCardView playCardView = new PlayCardView();

        // Add views to the main panel
        mainPanel.add(loadGameView, "Load Game");
        mainPanel.add(turnView, "Turn View");
        mainPanel.add(playCardView, "Play Card");

        // Sets the initial view
        viewManagerModel.setActiveView("Load Game");

        //Loads the application
        application.pack();
        application.setVisible(true);
    }
}


