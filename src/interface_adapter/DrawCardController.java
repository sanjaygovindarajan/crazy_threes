package interface_adapter;

import use_case.player_actions.draw_card.DrawCardInputBoundary;

/**
 * Controller for managing draw card operations.
 * Interacts with the DrawCardInputBoundary to execute the draw card action and handles
 * any exceptions that may occur.
 */
public class DrawCardController {
    final DrawCardInputBoundary drawCardInteractor;

    /**
     * Constructs a new DrawCardController with the draw card interactor.
     *
     * @param drawCardInteractor the interactor responsible for handling the draw card logic
     */
    public DrawCardController(DrawCardInputBoundary drawCardInteractor) {
        this.drawCardInteractor = drawCardInteractor;
    }

    /**
     * Executes the draw card operation using the interactor.
     * Handles if there are no cards left in deck.
     */
    public void drawCard() {
        drawCardInteractor.handleDrawCard();
    }
}
