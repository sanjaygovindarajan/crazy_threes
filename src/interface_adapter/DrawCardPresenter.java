package interface_adapter;

import javax.swing.*;

public class DrawCardPresenter extends StartGamePresenter implements DrawCardOutputBoundary{
    /**
     * Requests that the user shuffle the deck.
     */
    //TODO: Make this actually load the shuffle view
    @Override
    public void loadShuffleView(){}

    /**
     * Lets the user know they are not allowed to draw a card.
     */
    @Override
    public void loadUnableToDrawCard() {
        JOptionPane.showMessageDialog(null, "You are not allowed to do that!");
    }
}
