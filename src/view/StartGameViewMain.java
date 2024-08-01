package view;

import data_access.DataAccess;
import data_access.DataAccessInterface;
import interface_adapter.StartGameController;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.start_game.LoadSuccessViewModel;
import use_case.game_actions.NewGameInteractor;

import javax.swing.*;
import java.awt.*;

public class StartGameViewMain {
    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        DataAccessInterface dataAccess = new DataAccess();
//
//        TemporaryTurnView view = new TemporaryTurnView();
//        TemporaryShuffleView shuffleView = new TemporaryShuffleView();
//        ViewManagerModel viewManagerModel1 = new ViewManagerModel();
//        NewGameInteractor newGame = new NewGameInteractor(dataAccess, view, shuffleView, viewManagerModel1);
//        StartGameController controller = new StartGameController(newGame);

        JFrame application = new JFrame("Load Game");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        application.add(mainPanel);


        LoadSuccessViewModel loadSuccessViewModel = new LoadSuccessViewModel();
        StartGameView startGameView = StartGameUseCaseFactory.create(viewManagerModel, loadSuccessViewModel);
        mainPanel.add(startGameView, startGameView.viewName);
        viewManagerModel.setActiveView(startGameView.viewName);
        viewManagerModel.firePropertyChanged();




        // Add LoadGameView to the mainPanel


        application.pack();
        application.setVisible(true);

    }
}
