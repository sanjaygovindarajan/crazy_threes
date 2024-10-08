package interface_adapter.draw_card;

import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;

public class DrawCardPresenter extends StartGamePresenter implements DrawCardOutputBoundary {


    public DrawCardPresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel){
        super(viewManagerModel, turnViewModel);

    }

    /**
     * Lets the user know they are not allowed to draw a card.
     */
    @Override
    public void loadUnableToDrawCard() {
        JOptionPane.showMessageDialog(null, "You are not allowed to do that!");

    }


    /**
     * Requests that the user shuffle the deck.
     */
    @Override
    public void loadShuffleView() {
        this.viewManagerModel.setActiveView("Shuffle View");
    }
}

