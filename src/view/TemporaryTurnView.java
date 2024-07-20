package view;

import interface_adapter.SaveGameController;
import use_case.player_actions.PlayCardController;

import java.util.Scanner;

public class TemporaryTurnView {
    PlayCardController playCard;
    SaveGameController saveCard;
    public void requestAction(){
        System.out.println("Type 'Play card' to play a card.");
        System.out.println("Type 'Draw card' to draw a card.");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        if(action.equals("Play card")){
            playCard(input);
        } else if(action.equals("Draw card")) {
            drawCard();
        } else if(action.equals("Save game")){
            saveGame();
        }
    }

    private static void saveGame() {
    }

    private static void drawCard() {
    }

    private static void playCard(Scanner input) {
        System.out.println("Which suit would you like to play?");
        String suit = input.nextLine().toLowerCase();
        System.out.println("Which number would you like to play?");
        String num = input.nextLine().toLowerCase();
    }
}
