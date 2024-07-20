package view;

import entity.MissingCardException;
import interface_adapter.DrawCardController;
import interface_adapter.SaveGameController;
import use_case.player_actions.PlayCardController;

import java.io.IOException;
import java.util.Scanner;

public class TemporaryTurnView {
    PlayCardController playCard;
    SaveGameController saveGame;
    DrawCardController drawCard;

    public void setControllers(PlayCardController playCard, SaveGameController saveGame, DrawCardController drawCard){
        this.playCard = playCard;
        this.saveGame = saveGame;
        this.drawCard = drawCard;
    }

    public void requestAction(){
        System.out.println("Type 'Play card' to play a card.");
        System.out.println("Type 'Draw card' to draw a card.");
        System.out.println("Type 'Save game' to save the current game");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        switch (action) {
            case "Play card" -> playCard(input);
            case "Draw card" -> drawCard();
            case "Save game" -> saveGame(input);
            case "View rules" -> {
            }
        }
    }

    private void saveGame(Scanner input) {
        System.out.println("Type the game name (alphanumeric characters only!)");
        try {
            saveGame.execute(input.nextLine());
        }
        catch(IOException e){
            System.out.println("Save game failed");
        }
    }

    private void drawCard() {
        try {
            drawCard.drawCard();
        }
        catch(MissingCardException e){
            System.out.println("Draw card failed");
        }
    }

    private void playCard(Scanner input) {
        System.out.println("Which suit would you like to play?");
        String suit = input.nextLine().toLowerCase();
        System.out.println("Which number would you like to play?");
        String num = input.nextLine().toLowerCase();
    }
}
