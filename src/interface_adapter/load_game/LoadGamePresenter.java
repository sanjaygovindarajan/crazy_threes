package interface_adapter.load_game;

import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;

public class LoadGamePresenter extends StartGamePresenter implements LoadGameOutputBoundary {

    public LoadGamePresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        super(viewManagerModel, turnViewModel);
    }
    @Override
    public void prepareFailView(String error){
        JOptionPane.showMessageDialog(null, "ERROR: " + error);

    }
}
