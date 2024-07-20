package view;

import java.util.Scanner;

public class TemporaryTurnView {
    public static void requestAction(){
        System.out.println("Type 'Play card' to play a card.");
        System.out.println("Type 'Draw card' to draw a card.");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        if(action.equals("Play card")){
            playCard(input);
        } else {
            drawCard();
        }
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
