package interface_adapter.play_card;

import interface_adapter.StartGamePresenter;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;

public class PlayCardPresenter extends StartGamePresenter implements PlayCardOutputBoundary {
    public PlayCardPresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel) {
        super(viewManagerModel, turnViewModel);
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
        System.out.println("Congratulations " + player + " wins!");
    }

}
