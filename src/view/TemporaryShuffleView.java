package view;

import interface_adapter.shuffle.ShuffleController;

import java.util.Scanner;

/**
 * The view the user sees when they are prompted to shuffle the deck.
 */
public class TemporaryShuffleView {
    ShuffleController sh;

    /**
     * Use when the controller is already available.
     * @param controller The controller for shuffling the deck
     */
    public TemporaryShuffleView(ShuffleController controller){
        this.sh = controller;
    }

    /**
     * Use when the controller will be added later.
     */
    public TemporaryShuffleView() {

    }

    /**
     * Prompts the user to shuffle the deck.
     * @param input The Scanner used to prompt the user.
     */
    public void shuffle(Scanner input){
        System.out.println("Type anything to shuffle.");
        input.nextLine();
        sh.execute();
    }

    /**
     * Sets the controller for shuffling the deck.
     * @param controller The controller
     */
    public void setController(ShuffleController controller){
        this.sh = controller;
    }
}
