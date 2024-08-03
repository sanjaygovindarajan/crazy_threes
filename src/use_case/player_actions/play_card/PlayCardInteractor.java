package use_case.player_actions.play_card;

import entity.*;
import entity.exceptions.InvalidCardException;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;

import javax.swing.*;

public class PlayCardInteractor implements PlayCardInputBoundary {
    GameInterface game;
    PlayCardOutputBoundary presenter;

    public PlayCardInteractor(PlayCardOutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * Allows for a card to be played in the game.
     * @param index The index of the card
     */
    @Override
    public void playCard(int index){
        Player player = game.getCurrentPlayer();
        boolean threeCase = (player.viewHand().getCardList().get(index).getCardNum() == 3);

        try {
            if (!threeCase) {
                game.playCard(player, index);
            } else {
                String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
                //TODO: Refactor this into its own view
                int response = JOptionPane.showOptionDialog(null,
                        "Looks like you played a three! Pick a new suit",
                        "Three played",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        suits,
                        suits[0]);
                Character[] suitChar = {'S','H','D','C'};
                playThree(player.viewHand().getCardList().get(index).getDisplaySuit(), suitChar[response]);
            }
            if (game.isGameOver()) {
                presenter.winMessage(game.getCurrentPlayer().getName());
            } else {
                player = game.getCurrentPlayer();
                StartGameOutputData outputData = new StartGameOutputData(
                        player.viewHand().toString(),
                        player.getName(),
                        game.getDiscard().getCard().toString(),
                        game.getDiscard().getSuit());
                presenter.loadSuccessView(outputData);
            }
        } catch (InvalidCardException e) {
            presenter.loadInvalidCardView();
        }
    }

    /**
     * Allows for a three to be played in the game and for the suit of the disposal deck to be set.
     * @param suit the suit of the card being played.
     * @param newSuit the suit the disposal deck will begin accepting.
     */
    public void playThree(char suit, char newSuit){
        int index = -1;
        int count = 0;
        for(Card card: game.getCurrentPlayer().viewHand().getCardList()){
            if(card.getCardNum() == 3 && card.getDisplaySuit() == suit){
                index = count;
            }
            count++;
        }
        if(index == -1) {
            presenter.loadInvalidCardView();
            return;
        }
        try {
            game.playThree(index, newSuit);
        }
        catch(InvalidCardException e){
            presenter.loadInvalidCardView();
        } finally {
            if (game.isGameOver()) {
                presenter.winMessage(game.getCurrentPlayer().getName());
            }
        }
    }

    /**
     * Getter method for the presenter.
     * @return presenter the presenter.
     */
    @Override
    public StartGameOutputBoundary getPresenter() {
        return presenter;
    }

    /**
     * Sets the current game being played.
     * @param game The game currently being played.
     */
    @Override
    public void setGame(GameInterface game) {
        this.game = game;
    }
}
