package use_case.player_actions;

import entity.*;
import use_case.deck_actions.Shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NewGame {
    int numberOfPlayers;
    static List<String> playersName;
    static List<Hand> dealt;



    /**
     * Constructor class
     */
    public NewGame() {
    }

    /**
     * Insert number of Players
     * @param amount number of players
     */
    public void playerAmount(int amount) {
        amount = this.numberOfPlayers;
    }


    /**
     * Ask user to set Names of Players
     */
    public static void setNamesList() {
        //getting player names from user
        Scanner scanner = new Scanner(System.in);
        String nameInput;

        System.out.println("Enter the player's name (type 'done' to finish): ");
        int i = 1;
        boolean yes = true;
        while (yes || i>=6){
            nameInput = scanner.nextLine();
            playersName.add(scanner.nextLine());
            i ++;
            if (nameInput.equalsIgnoreCase("done")){
                yes = false;
            }
            System.out.println("You've entered: " + playersName);
        }
        scanner.close();
    }


    public static void setPlayers(){
        int i = 0;
        for (String name : playersName){
            Player aPlayer = new Player(name, dealt.get(i));
            Game.setPlayers(aPlayer);
            i += 1 ;
        }
    }

}