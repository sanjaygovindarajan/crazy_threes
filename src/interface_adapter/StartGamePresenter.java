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
    protected WinViewModel winViewModel;

    /**
     * Constructor for Phase 2. Not currently used.
     * @param viewManagerModel View manager model
     * @param startGameViewModel Start Game View Model
     * @param winViewModel Win View Model
     */
    public StartGamePresenter(ViewManagerModel viewManagerModel, TurnViewModel startGameViewModel, WinViewModel winViewModel){
        this.turnViewModel = startGameViewModel;
        this.viewManagerModel = viewManagerModel;
        this.winViewModel = winViewModel;

        this.winViewModel.addPropertyChangeListener(evt -> {
            if ("newGame".equals(evt.getPropertyName()) && Boolean.TRUE.equals(evt.getNewValue())) {
                startNewGame();
            }
        });
    }

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
            char num = changeCardNumber(card).charAt(1);
            cardNum.add(num);
        }
        turnViewModel.setCardSuits(cardSuits);
        turnViewModel.setCardNums(cardNum);
        turnViewModel.setDiscardSuit(data.getCard().charAt(0));
        char num = changeCardNumber(data.getCard()).charAt(1);
        turnViewModel.setDiscardNum(num);
        if(data.getCard().charAt(1) == '3'){
            String suit = Character.toString(data.getCurrentSuit());
            suit = suit.replace("S", "spades");
            suit = suit.replace("C", "clubs");
            suit = suit.replace("H", "hearts");
            suit = suit.replace("D", "diamonds");
            JOptionPane.showMessageDialog(null, "The suit was changed to " + suit);
        }
        updateView();
    }

    /**
     * Helper method to update the view
     */
    protected void updateView(){
        this.viewManagerModel.setActiveView("Turn View");
        this.turnViewModel.firePropertyChanged();
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Changes the card number to match the format of the API
     * @param card The card in string format
     * @return A new card in string format with numbers >10 replaced with a single character
     */
    protected String changeCardNumber(String card){
        return card.replace("10","0")
                .replace("11","J")
                .replace("12","Q")
                .replace("13","Q")
                .replace("14","A");
    }

}
