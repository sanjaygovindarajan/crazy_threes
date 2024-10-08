package use_case.player_actions.play_card;

import entity.*;
import entity.exceptions.InvalidCardException;
import interface_adapter.play_card.PlayCardInputData;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.play_card.PlayThreeInputData;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import entity.Bot;

import javax.swing.*;

public class PlayCardInteractor implements PlayCardInputBoundary {
    GameInterface game;
    PlayCardOutputBoundary presenter;

    public PlayCardInteractor(PlayCardOutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * Allows for a card to be played in the game.
     * @param inputData The index of the card
     */
    @Override
    public void playCard(PlayCardInputData inputData){
        int index = inputData.getIndex();
        Player player = game.getCurrentPlayer();
        boolean threeCase = (player.viewHand().getCardList().get(index).getCardNum() == 3);
        try {
            if (!threeCase) {
                game.playCard(player, index);
                finishPlayGame();
            } else {
                presenter.loadThreeView(player.viewHand().getCardList().get(index).getDisplaySuit());
            }
        } catch (InvalidCardException e) {
            presenter.loadInvalidCardView();
        }

    }

    /**
     * Allows for a three to be played in the game and for the suit of the disposal deck to be set.
     * @param inputData The suit and new suit of the card
     */
    public void playThree(PlayThreeInputData inputData){
        char suit = inputData.getSuit();
        char newSuit = inputData.getNewSuit();
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
            finishPlayGame();
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

    private void finishPlayGame() throws InvalidCardException {
        while(game.getCurrentPlayer().isBot()) {
            Bot bot = (Bot) game.getCurrentPlayer();
            bot.chooseCard(game.getDiscard(), game);
            if (game.isGameOver()) {
                break;
            }
        }
        if (game.isGameOver()) {
            presenter.winMessage(game.getCurrentPlayer().getName());
        } else {
            Player player = game.getCurrentPlayer();
            StartGameOutputData outputData = new StartGameOutputData(
                    player.viewHand().toString(),
                    player.getName(),
                    game.getDiscard().getCard().toString(),
                    game.getDiscard().getSuit());
            presenter.loadSuccessView(outputData);
        }
    }
}
