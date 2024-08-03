package interface_adapter;

import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import view.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The presenter for the start game, play card, draw card, and load game user stories.
 * Not located in start_game package because it is used by many use cases.
 */
public class StartGamePresenter implements StartGameOutputBoundary {
    protected TurnViewModel turnViewModel;
    protected ViewManagerModel viewManagerModel;

    /**
     * Constructor for Phase 2. Not currently used.
     * @param viewManagerModel View manager model
     * @param startGameViewModel Start Game View Model
     */
    public StartGamePresenter(ViewManagerModel viewManagerModel, TurnViewModel startGameViewModel){
        this.turnViewModel = startGameViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    /**
     * Empty constructor used for testing purposes.
     */
    public StartGamePresenter(){
    }

    /**
     * Prints the player whose turn it is, their cards, the face-up card, and the current suit.
     * Switches the view to the next player's turn.
     * If the method is called as a test and there is no view, prints out "Test completed."
     * @param data Includes the player's cards and the face-up card.
     */
    @Override
    public void loadSuccessView(StartGameOutputData data) {
        turnViewModel.setPlayerName(data.getPlayerName());
        List<Character> cardSuits = new ArrayList<>();
        List<Character> cardNum = new ArrayList<>();
        for(String card : data.getPlayerCards().split(",")){
            cardSuits.add(card.charAt(0));
            char num = card.charAt(1);
            if(num == 1){
                cardNum.add('0');
            } else {
                cardNum.add(num);
            }
        }
        turnViewModel.setCardSuits(cardSuits);
        turnViewModel.setCardNums(cardNum);
        turnViewModel.setDiscardSuit(data.getCard().charAt(0));
        char num = data.getCard().charAt(1);
        if(num == 1){
            turnViewModel.setDiscardNum('0');
        } else {
            turnViewModel.setDiscardNum(num);
        }
        if(data.getCard().charAt(1) == '3'){
            String suit = Character.toString(data.getCurrentSuit());
            suit = suit.replace("S", "spades");
            suit = suit.replace("C", "clubs");
            suit = suit.replace("H", "hearts");
            suit = suit.replace("D", "diamonds");
            JOptionPane.showMessageDialog(null, "The suit was changed to " + suit);
        }
        this.viewManagerModel.setActiveView("Turn View");
        this.turnViewModel.firePropertyChanged();
        this.viewManagerModel.firePropertyChanged();
    }

}
