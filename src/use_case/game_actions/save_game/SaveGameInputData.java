package use_case.game_actions.save_game;

public class SaveGameInputData
{
    private final String gameName;
    public SaveGameInputData(String gameName){
        this.gameName = gameName;
    }

    /**
     * Gets the game name part of the input data
     * @return The game name
     */
    public String getGameName(){
        return this.gameName;
    }
}
