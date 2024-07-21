package view;

import interface_adapter.ShuffleController;

import java.util.Scanner;

public class TemporaryShuffleView {
    ShuffleController sh;

    public TemporaryShuffleView(ShuffleController controller){
        this.sh = controller;
    }

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

    public void setController(ShuffleController controller){
        this.sh = controller;
    }
}
