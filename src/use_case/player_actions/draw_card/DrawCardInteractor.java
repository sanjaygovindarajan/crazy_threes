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

    GameInterface game;
    StartGameOutputBoundary presenter;

    public DrawCardInteractor(StartGameOutputBoundary presenter){
        this.presenter = presenter;
    }

    /**
     * Draws a single card if the player is allowed to do so and if there is a card in the deck.
     * If there is no card in the deck, prompts the player to shuffle the deck.
     * If the player is not allowed to draw a card, prompts the player to choose a different action.
     * If a card is drawn successfully, prompts the player to choose the next action.
     */
    @Override
    public void handleDrawCard(){
        Player player = game.getCurrentPlayer();
        if(!game.hasPlayableCard()) {
            try {
                player.drawCard(game.getDeck());
                StartGameOutputData data = new StartGameOutputData(
                        player.viewHand().toString(),
                        player.getName(),
                        game.getDiscard().getCard().toString(),
                        game.getDiscard().getSuit());
                presenter.loadSuccessView(data);
            } catch (MissingCardException e) {
                presenter.loadShuffleView();
            }

        } else {
            presenter.loadUnableToDrawCard();
        }
    }

    /**
     * Sets the game that the interactor modifies.
     * @param game The game
     */
    @Override
    public void setGame(GameInterface game) {
        this.game = game;
    }

    /**
     * Gets the presenter for the draw card user story.
     * @return The presenter
     */
    @Override
    public StartGameOutputBoundary getPresenter() {
        return this.presenter;
    }
}
