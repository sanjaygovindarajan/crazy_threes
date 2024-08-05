package interface_adapter.play_card;

import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.WinViewModel;

import javax.swing.*;

public class PlayCardPresenter extends StartGamePresenter implements PlayCardOutputBoundary {
    WinViewModel winViewModel;
    public PlayCardPresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, WinViewModel winViewModel) {
        super(viewManagerModel, turnViewModel);
        this.winViewModel = winViewModel;
    }


    @Override
    public void loadInvalidCardView() {
        JOptionPane.showMessageDialog(null, "You are not allowed to play that card!");
    }

    @Override
    public void loadMissingCardView() {

    }

    /**
     * Displays a win message declaring that the player has won.
     * @param player The name of the player that won
     */
    @Override
    public void winMessage(String player){
        winViewModel.setWinner(player);
        viewManagerModel.setActiveView("Win View");
    }

}
