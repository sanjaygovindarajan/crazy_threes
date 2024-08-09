package use_case.game_actions.load_game;

public class LoadGameInputData {
    final private String gameName;


    public LoadGameInputData(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Get the name of the game from input data
     * @return The game name
     */
    public String getGameName() {
        return gameName;
    }

}
