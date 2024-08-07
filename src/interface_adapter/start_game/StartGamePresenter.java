package interface_adapter.start_game;
import interface_adapter.TurnViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.util.*;

/**
 * The presenter for the start game user stories.
 * Also, the draw card, load game, and play card presenters inherit from this.
 */
public class StartGamePresenter implements StartGameOutputBoundary {
    protected TurnViewModel turnViewModel;
    protected ViewManagerModel viewManagerModel;

    /**
     * Constructor for Phase 2. Not currently used.
     * @param viewManagerModel View manager model
     * @param startGameViewModel Start Game View Model
     */
    public StartGamePresenter(ViewManagerModel viewManagerModel, TurnViewModel startGameViewModel) {
        this.turnViewModel = startGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Empty constructor used for testing purposes.
     */
    public StartGamePresenter(){
    }

    /**
     * Loads the cards from the game, the player name, and the top card of the discard pile.
     * Switches the view to the next player's turn.
     * @param data The output data
     */
    @Override
    public void loadSuccessView(StartGameOutputData data) {
        turnViewModel.setPlayerName(data.getPlayerName());
        List<Character> cardSuits = new ArrayList<>();
        List<Character> cardNum = new ArrayList<>();
        for (String card : data.getPlayerCards().split(",")) {
            cardSuits.add(card.charAt(0));
            char num = changeCardNumber(card).charAt(1);
            cardNum.add(num);
        }
        turnViewModel.setCardSuits(cardSuits);
        turnViewModel.setCardNums(cardNum);
        turnViewModel.setDiscardSuit(data.getCard().charAt(0));
        char num = changeCardNumber(data.getCard()).charAt(1);
        turnViewModel.setDiscardNum(num);
        if (data.getCard().charAt(1) == '3') {
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
                .replace("13","K")
                .replace("14","A");
    }

}
