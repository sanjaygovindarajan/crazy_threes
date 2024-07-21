package view;

import interface_adapter.play_card.PlayCardController;
import java.util.Scanner;

/**
 * The view the user sees when prompted to change the suit because they played a Three
 */
public class TemporaryThreeView {
    private final PlayCardController pc;

    /**
     * Creates a new TemporaryThreeView object
     * @param controller The controller for playing a card.
     */
    public TemporaryThreeView(PlayCardController controller){
        this.pc = controller;
    }

    /**
     * Requests that the user changes the suit
     * @param suit The old suit of the three
     */
    public void requestAction(char suit){
        System.out.println("Looks like you played a three! Choose a new suit.");
        Scanner input = new Scanner(System.in);
        String newSuit = input.nextLine();
        pc.playThree(suit, newSuit);

    }
}
