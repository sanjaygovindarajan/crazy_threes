package interface_adapter;

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
