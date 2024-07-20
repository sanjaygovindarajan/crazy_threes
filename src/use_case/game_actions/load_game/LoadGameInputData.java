package use_case.game_actions.load_game;

public class LoadGameInputData {
    final private String gameName;

    public LoadGameInputData(String gameName) {
        this.gameName = gameName;
    }
    public String getGameName() {
        return gameName;
    }

}
