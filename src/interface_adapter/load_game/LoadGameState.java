package interface_adapter.load_game;

/**
 * Not used in Phase 1
 */
public class LoadGameState {
    private String gameName = "";

    public LoadGameState(LoadGameState copy) {
        gameName = copy.gameName;
    }

    public LoadGameState() {}

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

}
