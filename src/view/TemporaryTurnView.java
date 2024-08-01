package view;

import interface_adapter.draw_card.DrawCardController;
import interface_adapter.play_card.PlayCardController;
import interface_adapter.view_rules.ReadRulesController;
import interface_adapter.save_game.SaveGameController;

import java.util.Scanner;

/**
 * The view when a user is prompted to make a move.
 * Temporary view that will be replaced in Phase 2.
 */
public class TemporaryTurnView {
    PlayCardController playCard;
    SaveGameController saveGame;
    DrawCardController drawCard;
    ReadRulesController viewRules;

    /**
     * Sets the controllers for playing a card, saving a game, and drawing a card.
     * @param playCard The controller for playing a card
     * @param saveGame The controller for saving the game
     * @param drawCard The controller for drawing a card
     */
    public void setControllers(PlayCardController playCard, SaveGameController saveGame, DrawCardController drawCard){
        this.playCard = playCard;
        this.saveGame = saveGame;
        this.drawCard = drawCard;
    }

    /**
     * Sets the controller for viewing the rules.
     * @param viewRules The controller for viewing the rules
     */
    public void setViewRules(ReadRulesController viewRules){
        this.viewRules = viewRules;
    }

    /**
     * Requests that the user either plays a card, draws a card, saves the game, or views the rules.
     * After playing a card, they will be prompted for the suit and number of the card.
     * After saving the game, they will be prompted to name the game, and the program will terminate
     * after it is saved.
     */
    public void requestAction(){
        System.out.println("Type '1' to play a card.");
        System.out.println("Type '2' to draw a card.");
        System.out.println("Type '3' to save the current game.");
        System.out.println("Type '4' to review the rules.");
        Scanner input = new Scanner(System.in);
        char action = input.nextLine().toLowerCase().charAt(0);
        switch (action) {
            case '1' -> playCard(input);
            case '2' -> drawCard();
            case '3' -> saveGame(input);
            case '4' -> viewRules();
        }
    }

    public void requestSaveGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("Type a new name to save the current game.");
        saveGame(input);
    }

    /**
     * Implements the use case involving viewing the rules.
     * As of Phase 1 it currently prints out the rules, rather than open a new window with the rules
     * as it will in Phase 2, which will provide the user story with the additional use case of
     * closing the rules.
     */
    private void viewRules() {
        viewRules.execute(true);
    }

    /**
     * Saves the game. Prompts the user to choose a name for the game.
     * @param input The scanner that prompts the user to name the game.
     */
    private void saveGame(Scanner input) {
        System.out.println("Type the game name (alphanumeric characters only!)");
        saveGame.execute(input.nextLine());
    }

    /**
     * Draws a card and gives the card to the player whose turn it is.
     * Implements the drawing a card use case.
     */
    private void drawCard() {
        drawCard.drawCard();
    }

    /**
     * Plays a card after the player chooses which suit and which number they want to play, if possible.
     * @param input The scanner that gets which suit and number the user wants to play.
     */
    private void playCard(Scanner input) {
        System.out.println("Which suit would you like to play?");
        String suit = input.nextLine().toLowerCase();
        System.out.println("Which number would you like to play?");
        String num = input.nextLine().toLowerCase();
        playCard.playCard(num, suit);
    }

    /**
     * Sets the controllers for playing a card and saving the game.
     * @param pc The controller for playing a card
     * @param sg The controller for saving the game
     */
    public void setControllers(PlayCardController pc, SaveGameController sg) {
        this.playCard = pc;
        this.saveGame = sg;
    }

    public void drequestSaveGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type a new name to save the current game.");
        saveGame(input);
    }
}
