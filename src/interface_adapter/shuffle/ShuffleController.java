package interface_adapter.shuffle;

import use_case.deck_actions.ShuffleInputBoundary;

/**
 * Controller for managing the shuffling of Cards.
 * Interacts with the ShuffleInputBoundary to execute the shuffle deck action.
 */
public class ShuffleController {
    final ShuffleInputBoundary shuffleInteractor;

    /**
     * Constructs a new ShuffleController with the shuffle interactor.
     *
     * @param shuffleInteractor responsible for handling the draw card logic
     */
    public ShuffleController(ShuffleInputBoundary shuffleInteractor) {

        this.shuffleInteractor = shuffleInteractor;
    }

    public void execute(){
        shuffleInteractor.shuffle();
    }
}
