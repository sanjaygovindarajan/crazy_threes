package interface_adapter;

import use_case.game_actions.load_game.LoadGameOutputBoundary;
import use_case.game_actions.load_game.LoadGameOutputData;
import use_case.player_actions.DrawCardOutputData;
import view.TemporaryTurnView;


/**
 * Formats and presents the result of shuffling a deck operation.
 * Implements the ShuffleOutputBoundary interface to handle the shuffling
 */
public class ShufflePresenter implements ShuffleOutputBoundary {
    private final TemporaryTurnView view;
    public ShufflePresenter(TemporaryTurnView view) {
        this.view = view;
    }
    /**
     * Presents the result of the shuffle operation.
     * Formats the output data and prints it to the console.
     * Constructor for phase 1.
     */
    public void loadSuccessful(){
        System.out.println("Shuffle done successfully");
        view.requestAction();
    }
}
