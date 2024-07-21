package interface_adapter;

import view.*;

import java.util.Scanner;

public class StartGamePresenter implements StartGameOutputBoundary{
    private TemporaryThreeView threeView;
    private StartGameViewModel startGameViewModel;
    private ViewManagerModel viewManagerModel;
    private TemporaryTurnView view;
    private TemporaryShuffleView shuffle;

    public StartGamePresenter(ViewManagerModel viewManagerModel, StartGameViewModel startGameViewModel){
        this.startGameViewModel = startGameViewModel;
        this.viewManagerModel = viewManagerModel;

    }
    public StartGamePresenter(){
    }

    public StartGamePresenter(TemporaryTurnView view){
        this.view = view;
    }
    public StartGamePresenter(TemporaryTurnView view, TemporaryThreeView threeView){
        this.view = view;
        this.threeView = threeView;
    }

    @Override
    public void loadSuccessView(StartGameOutputData data) {
        System.out.println("It's " + data.getPlayerName() + "'s turn!");
        System.out.println("Their cards:");
        for(String card : data.getPlayerCards().split(",")){
            System.out.println(printCard(card));
        }
        System.out.println("The previous card was the " + printCard(data.getCard()));
        if(data.getCard().charAt(1) == '3'){
            String suit = Character.toString(data.getCurrentSuit());
            suit = suit.replace("S", "spades");
            suit = suit.replace("C", "clubs");
            suit = suit.replace("H", "hearts");
            suit = suit.replace("D", "diamonds");
            System.out.println("However, the suit was changed to " + suit);
        }
        view.requestAction();
    }

    @Override
    public void loadInvalidCardView() {
        System.out.println("You are not allowed to play that card!");
        view.requestAction();
    }

    public void loadMissingCardView(){
        System.out.println("You don't have this card!");
        view.requestAction();
    }

    @Override
    public void loadThreeView(char suit) {
        threeView.requestAction(suit);

    }

    public void setThreeView(TemporaryThreeView view){
        this.threeView = view;
    }

    @Override
    public void loadUnableToDrawCard() {
        System.out.println("You are not allowed to draw a card if you have a playable card.");
        this.view.requestAction();
    }

    @Override
    public void loadShuffleView() {
        this.shuffle.shuffle(new Scanner(System.in));
    }

    @Override
    public void setShuffle(TemporaryShuffleView shuffleView) {
        this.shuffle = shuffleView;
    }

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
