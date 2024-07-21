package view;

import interface_adapter.load_game.LoadGameController;
import interface_adapter.view_rules.ReadRulesController;
import interface_adapter.start_game.StartGameController;

import java.util.Scanner;

/**
 * The initial view that the user sees when they start the game.
 */
public class TemporaryDefaultView {
    private final StartGameController ng;
    private final LoadGameController lg;
    private ReadRulesController vr;

    public TemporaryDefaultView(StartGameController ng, LoadGameController lg){
        this.ng = ng;
        this.lg = lg;
    }

    /**
     * Requests that the user either starts a new game, loads a game, or views the rules.
     */
    public void requestAction() {
        System.out.println("Type the name of a saved game to load a game");
        System.out.println("Or, type 'Start game' to start a new game");
        System.out.println("Or, type 'View rules' to view the rules");
        Scanner input = new Scanner(System.in);
        String action = input.nextLine();
        if (action.equals("Start game")) {
            System.out.println("Input player names separated by a space");
            String playerNames = input.nextLine();
            ng.execute(playerNames);
        } else if(action.equals("View rules")){
            vr.execute(false);
        } else {
            lg.execute(action);
        }
    }

    public void setViewRules(ReadRulesController vr) {
        this.vr = vr;
    }
}
