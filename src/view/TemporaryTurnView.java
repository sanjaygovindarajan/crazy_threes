package view;

import entity.MissingCardException;
import interface_adapter.DrawCardController;
import interface_adapter.PlayCardController;
import interface_adapter.SaveGameController;

import java.io.IOException;
import java.util.Scanner;

public class TemporaryTurnView {
    PlayCardController playCard;
    SaveGameController saveGame;
    DrawCardController drawCard;
    ViewRulesController viewRules;

    public void setControllers(PlayCardController playCard, SaveGameController saveGame, DrawCardController drawCard){
        this.playCard = playCard;
        this.saveGame = saveGame;
        this.drawCard = drawCard;
    }

    public void setViewRules(ViewRulesController viewRules){
        this.viewRules = viewRules;
    }

    public void requestAction(){
        System.out.println("Type 'Play card' to play a card.");
        System.out.println("Type 'Draw card' to draw a card.");
        System.out.println("Type 'Save game' to save the current gam.");
        System.out.println("Type 'View rules' to review the rules.");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        switch (action) {
            case "Play card" -> playCard(input);
            case "Draw card" -> drawCard();
            case "Save game" -> saveGame(input);
            case "View rules" -> viewRules();
        }
    }

    private void viewRules() {
        viewRules.execute();
    }

    private void saveGame(Scanner input) {
        System.out.println("Type the game name (alphanumeric characters only!)");
        saveGame.execute(input.nextLine());
    }

    private void drawCard() {
        drawCard.drawCard();
    }

    private void playCard(Scanner input) {
        System.out.println("Which suit would you like to play?");
        String suit = input.nextLine().toLowerCase();
        System.out.println("Which number would you like to play?");
        String num = input.nextLine().toLowerCase();
        playCard.playCard(num, suit);
    }


}
