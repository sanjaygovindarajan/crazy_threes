package use_case.game_actions.start_game;
import java.util.*;

public class StartGameInputData {
    List<String> playerNames;
    public StartGameInputData(String[] playerNames){
        this.playerNames = new ArrayList<>(Arrays.asList(playerNames));
    }

    /**
     * A getter method for the list of players
     * @return The list of players
     */
    public List<String> getPlayers(){
        return playerNames;
    }
}
