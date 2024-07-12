package use_case.game_actions;

public class LoadGameInputData {
    final private String gameName;

    public LoadGameInputData(String gameName) {
        this.gameName = gameName;
    }
    public String getGameName() {
        return gameName;
    }

}
