package use_case.player_actions.play_card;

import entity.*;
import entity.exceptions.InvalidCardException;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;
import entity.Bot;

public class PlayCardInteractor implements PlayCardInputBoundary {
    GameInterface game;
    StartGameOutputBoundary presenter;

    public PlayCardInteractor(StartGameOutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * Allows for a card to be played in the game.
     * @param number the number of the card to be played.
     * @param suit the suit of the card to be played.
     */
    @Override
    public void playCard(int number, char suit){
        Player player = game.getCurrentPlayer();
        boolean threeCase = (number == 3);
        int index = -1;
        int count = 0;
        DeckDisposed discard = game.getDiscard();
        for(Card card: player.viewHand().getCardList()){
            if(card.getCardNum() == number && card.getDisplaySuit() == suit){
                index = count;
            }
            count++;
        }
        if(index == -1){
            presenter.loadMissingCardView();
            return;
        }
        try {
            if (!threeCase) {
                game.playCard(player, index);
            } else {
                presenter.loadThreeView(suit);
            }
            player = game.getCurrentPlayer();
            while(player.isBot()) {
                Bot bot = (Bot) game.getCurrentPlayer();
                bot.chooseCard(discard, game);
                if (game.isGameOver()) {
                    break;
                }
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
