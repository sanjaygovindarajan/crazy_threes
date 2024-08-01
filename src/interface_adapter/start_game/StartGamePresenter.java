package interface_adapter.start_game;

import interface_adapter.ViewManagerModel;
import view.*;

import java.util.Scanner;

/**
 * The presenter for the start game, play card, draw card, and load game user stories.
 * Not located in start_game package because it is used by many use cases.
 */
public class StartGamePresenter implements StartGameOutputBoundary {
//    private final LoadSuccessViewModel loadSuccessViewModel;
    private TemporaryThreeView threeView;
//    private StartGameViewModel startGameViewModel;
    private ViewManagerModel viewManagerModel;
    private TemporaryTurnView view;
    private TemporaryShuffleView shuffle;
    private LoadSuccessViewModel loadSuccessViewModel = new LoadSuccessViewModel();

    /**
     * Constructor for Phase 2. Not currently used.
     * @param viewManagerModel View manager model
     */
    public StartGamePresenter(TemporaryTurnView view, ViewManagerModel viewManagerModel, LoadSuccessViewModel loadSuccessViewModel){
        this.view = view;
        this.viewManagerModel = viewManagerModel;
        this.loadSuccessViewModel = loadSuccessViewModel;

    }

    /**
     * Empty constructor used for testing purposes.
     */
    public StartGamePresenter(){
    }

    /**
     * Constructor used in the case where the Three View is not needed, or will be added later.
     * @param view The view
     */
    public StartGamePresenter(TemporaryTurnView view){
        this.view = view;
    }

    /**
     * General constructor
     * @param view The main view
     * @param threeView The view in the case a player plays a three
     */
    public StartGamePresenter(TemporaryTurnView view, TemporaryThreeView threeView){
        this.view = view;
        this.threeView = threeView;
    }

    /**
     * Prints the player whose turn it is, their cards, the face-up card, and the current suit.
     * Switches the view to the next player's turn.
     * If the method is called as a test and there is no view, prints out "Test completed."
     * @param data Includes the player's cards and the face-up card.
     */
    @Override
    public void loadSuccessView(StartGameOutputData data) {
        System.out.println("ViewManagerModel initialized: " + viewManagerModel);
        System.out.println("LoadSuccessViewModel initialized: " + loadSuccessViewModel);
        System.out.println("StartGameData: " + data);

        ViewManagerModel viewManagerModel = new ViewManagerModel();


        loadSuccessViewModel.setStateFromStartGameData(data);
        LoadSuccessState state = loadSuccessViewModel.getState();

        this.loadSuccessViewModel.setState(state);
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setActiveView(loadSuccessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();



        System.out.println("It's " + state.getPlayerName() + "'s turn!");
        System.out.println("Their cards:");
        for (String card : state.getPlayerCards()) {
            System.out.println(printCard(card));
        }
        System.out.println("The previous card was the " + state.getPreviousCard());
        if (state.getCurrentSuit() != null) {
            System.out.println("However, the suit was changed to " + state.getCurrentSuit());
        }
        try {
            int i=0;
        } catch (NullPointerException e) {
            // Test mode, there is no view
            System.out.println("Test completed");
        }
}

    /**
     * Prints a message declaring the card invalid and requests the player choose a different action.
     * If the method is called as a test and there is no view, prints out "Test completed."
     */
    @Override
    public void loadInvalidCardView() {
        System.out.println("You are not allowed to play that card!");
        try {
            this.view.requestAction();
        } catch(NullPointerException e){
            //Test mode, there is no view
            System.out.println("Test completed");
        }
    }

    /**
     * Prints a message declaring that the player does not have the card
     * that they want to play and requests the player choose a different action.
     * If the method is called as a test and there is no view, prints out "Test completed."
     */
    public void loadMissingCardView(){
        System.out.println("You don't have this card!");
        try {
            this.view.requestAction();
        } catch(NullPointerException e){
            //Test mode, there is no view
            System.out.println("Test completed");
        }
    }

    /**
     * Requests that the player choose a different suit.
     * @param suit The current suit
     */
    @Override
    public void loadThreeView(char suit) {
        threeView.requestAction(suit);

    }

    /**
     * Displays a win message declaring that the player has won.
     * @param player The name of the player that won
     */
    @Override
    public void winMessage(String player){
        System.out.println("Congratulations " + player + " wins!");
    }

    /**
     * Sets the view in the case that the player played a three.
     * @param view The three view
     */
    @Override
    public void setThreeView(TemporaryThreeView view){
        this.threeView = view;
    }

    @Override
    public void loadUnableToDrawCard() {
        System.out.println("You are not allowed to draw a card if you have a playable card.");
        try {
            this.view.requestAction();
        } catch(NullPointerException e){
            //Test mode, there is no view
            System.out.println("Test completed");
        }
    }

    /**
     * Requests that the user shuffle the deck.
     */
    @Override
    public void loadShuffleView() {
        this.shuffle.shuffle(new Scanner(System.in));
    }

    /**
     * Sets the shuffle view.
     * @param shuffleView The shuffle view
     */
    @Override
    public void setShuffle(TemporaryShuffleView shuffleView) {
        this.shuffle = shuffleView;
    }

    /**
     * Returns the card in a human-friendly string format.
     * @param card The card as a string, but in the database format
     *             (the char of the suit, and then the number)
     * @return The card as a string, but in a human-friendly format.
     */
    private String printCard(String card){
        String num = card.substring(1);
        String suit = card.substring(0, 1);
        num = num.replace("11","Jack");
        num = num.replace("12","Queen");
        num = num.replace("13","King");
        num = num.replace("14","Ace");
        suit = suit.replace("S", "spades");
        suit = suit.replace("C", "clubs");
        suit = suit.replace("H", "hearts");
        suit = suit.replace("D", "diamonds");
        return (num + " of " + suit);
    }

}
