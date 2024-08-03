package interface_adapter.shuffle;

import interface_adapter.ViewManagerModel;

import javax.swing.*;

/**
 * Formats and presents the result of shuffling a deck operation.
 * Implements the ShuffleOutputBoundary interface to handle the shuffling
 */
public class ShufflePresenter implements ShuffleOutputBoundary {
    private ViewManagerModel viewManagerModel;

    public ShufflePresenter(ViewManagerModel viewManagerModel) {
    }

    /**
     * Presents the result of the shuffle operation.
     *  Constructor for phase 1: Formats the output data and prints it to the console.
     *
     * Now modified for phase 2: JOptionPane that outputs a message.
     *
     */
    public void loadSuccessful(){
        JOptionPane.showMessageDialog(null, "Shuffled successfully");
        this.viewManagerModel.setActiveView("Turn view");
    }
}
