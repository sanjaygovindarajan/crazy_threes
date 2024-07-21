package view;

import interface_adapter.PlayCardController;
import java.util.Scanner;

public class TemporaryThreeView {
    private final PlayCardController pc;
    public TemporaryThreeView(PlayCardController controller){
        this.pc = controller;
    }
    public void requestAction(char suit){
        System.out.println("Looks like you played a three! Choose a new suit.");
        Scanner input = new Scanner(System.in);
        String newSuit = input.nextLine();
        pc.playThree(suit, newSuit);

    }
}
