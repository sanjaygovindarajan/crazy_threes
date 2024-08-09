package interface_adapter.load_game;

import interface_adapter.LoadGameViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;

public class ViewGamesPresenter implements ViewGamesOutputBoundary{
    private final LoadGameViewModel loadGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewGamesPresenter(LoadGameViewModel loadGameViewModel, ViewManagerModel viewManagerModel) {
        this.loadGameViewModel = loadGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prints an error message
     */
    @Override
    public void loadFailView() {
        JOptionPane.showMessageDialog(null, "Data access not initialized");
    }

    /**
     * Present all the games in the database to loadGameView.
     * @param outputData The output data.
     */
    @Override
    public void loadSuccessView(ViewGamesOutputData outputData) {
        loadGameViewModel.setGames(outputData.getGames());
        loadGameViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("Load Game");
    }
}
