package use_case.player_actions.draw_card;

import entity.*;

import interface_adapter.SaveGameOutputBoundary;
import interface_adapter.StartGameOutputBoundary;
import interface_adapter.StartGameOutputData;


/**
 * Handles the logic for drawing a card in the game.
 * This interactor checks if the current player has a playable card and, if not, draws a card
 * from the deck until a playable card is found.
 * If the drawn card is playable, it will be played. The interactor then communicates the results
 * to the output boundary.
 */

public class DrawCardInteractor implements DrawCardInputBoundary {

    Game game;
    StartGameOutputBoundary presenter;

    public DrawCardInteractor(StartGameOutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * Handles the process of drawing a card for the current player. If the player does not
     * have a playable card, the method draws cards from the deck until a playable card is found.
     * It then prepares a response model and passes it to the output boundary for presentation.
     */
    @Override
    public void handleDrawCard(){
        Player player = game.getCurrentPlayer();
        if(!game.hasPlayableCard()) {
            try {
                player.drawCard(game.getDeck());
            } catch (MissingCardException e) {
                presenter.loadShuffleView();
            }
            StartGameOutputData data = new StartGameOutputData(
                    player.viewHand().getCardList().toString(),
                    player.getName(),
                    game.getDiscard().getCard().toString(),
                    game.getDiscard().getSuit());
            presenter.loadSuccessView(data);
        } else {
            presenter.loadUnableToDrawCard();
        }
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public StartGameOutputBoundary getPresenter() {
        return this.presenter;
    }
}
