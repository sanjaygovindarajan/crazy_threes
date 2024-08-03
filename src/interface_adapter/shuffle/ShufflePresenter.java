package interface_adapter.shuffle;


/**
 * Formats and presents the result of shuffling a deck operation.
 * Implements the ShuffleOutputBoundary interface to handle the shuffling
 */
public class ShufflePresenter implements ShuffleOutputBoundary {
    /**
     * Presents the result of the shuffle operation.
     * Formats the output data and prints it to the console.
     * Constructor for phase 1.
     */
    public void loadSuccessful(){
        System.out.println("Shuffle done successfully");
    }
}
