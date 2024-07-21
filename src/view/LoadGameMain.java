package view;

import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * Not used in Phase 1
 */
public class LoadGameMain {
    public static void main(String[] args) {

        JFrame application = new JFrame("Load Game");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        application.add(mainPanel);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(mainPanel, cardLayout, viewManagerModel);

        LoadGameViewModel loadGameViewModel = new LoadGameViewModel();

        LoadGameView loadGameView = LoadGameUseCaseFactory.create(viewManagerModel, loadGameViewModel);

        // Add LoadGameView to the mainPanel
        mainPanel.add(loadGameView, loadGameView.viewName);

        // Set active view and fire property change to update UI
        viewManagerModel.setActiveView(loadGameView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}


