package app;

import interface_adapter.PlayThreeViewModel;
import interface_adapter.TurnViewModel;
import interface_adapter.WinViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.LoadGameViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * This is the Main file for Phase 2.
 * It creates the views.
 */
public class Main {
    /**
     * The main method
     * @param args Not used
     */
    public static void main(String[] args) {
        JFrame application = new JFrame("Crazy Threes");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        application.add(mainPanel);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(mainPanel, cardLayout, viewManagerModel);

        TurnViewModel turnViewModel = new TurnViewModel();
        WinViewModel winViewModel = new WinViewModel();
        PlayThreeViewModel playThreeViewModel = new PlayThreeViewModel();
        LoadGameViewModel loadGameViewModel = new LoadGameViewModel();

        LoadGameView loadGameView = ViewBuilder.createLoadGameView(viewManagerModel, turnViewModel, winViewModel, playThreeViewModel, loadGameViewModel);
        TurnView turnView = ViewBuilder.createTurnView(turnViewModel, loadGameView.getController().getInteractor());
        NewGameView gameView = ViewBuilder.createNewGame(viewManagerModel, loadGameViewModel);
        ShuffleView shuffleView = ViewBuilder.createShuffleView(loadGameView.getController().getInteractor());
        InputPlayersView playersView = ViewBuilder.createInputPlayers(loadGameView.getController().getInteractor());
        WinView winView = ViewBuilder.createWinView(viewManagerModel, winViewModel);
        PlayThreeView playThreeView = ViewBuilder.createThreeView(viewManagerModel, loadGameView.getController().getInteractor(), playThreeViewModel);

        mainPanel.add(gameView, "New Game");
        mainPanel.add(loadGameView, "Load Game");
        mainPanel.add(turnView, "Turn View");
        mainPanel.add(shuffleView, "Shuffle View");
        mainPanel.add(playersView, "Input Players");
        mainPanel.add(playThreeView, "Three View");
        mainPanel.add(winView, "Win View");

        viewManagerModel.setActiveView("New Game");

        application.setSize(800, 800);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
