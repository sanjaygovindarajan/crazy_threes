package interface_adapter;

import entity.GameInterface;
import interface_adapter.shuffle.ShuffleController;
import view.ShuffleView;


import javax.swing.*;

public class DrawCardPresenter extends StartGamePresenter implements DrawCardOutputBoundary{
    private final ShuffleController shuffleController;
    private final GameInterface gameInterface;

    public DrawCardPresenter(ViewManagerModel viewManagerModel, TurnViewModel turnViewModel){
        super(viewManagerModel, turnViewModel);
        this.shuffleController = shuffleController;
        this.gameInterface = gameInterface;
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
        ShuffleView shuffleView = new ShuffleView(shuffleController, gameInterface);
        ViewManagerModel.setActiveView(shuffleView);
    }
}

