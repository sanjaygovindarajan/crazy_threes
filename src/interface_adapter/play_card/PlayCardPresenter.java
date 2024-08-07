package interface_adapter.play_card;

import interface_adapter.PlayThreeViewModel;
import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.WinViewModel;

import javax.swing.*;

public class PlayCardPresenter extends StartGamePresenter implements PlayCardOutputBoundary {
    WinViewModel winViewModel;
    PlayThreeViewModel playThreeViewModel;
    public PlayCardPresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel, WinViewModel winViewModel, PlayThreeViewModel playThreeViewModel) {
        super(viewManagerModel, turnViewModel);
        this.winViewModel = winViewModel;
        this.playThreeViewModel = playThreeViewModel;
    }


    @Override
    public void loadInvalidCardView() {
        JOptionPane.showMessageDialog(null, "You are not allowed to play that card!");
    }

    @Override
    public void loadMissingCardView() {

    }

    @Override
    public void loadThreeView(char suit) {
        playThreeViewModel.setThreeSuit(suit);
        viewManagerModel.setActiveView("Three View");
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
