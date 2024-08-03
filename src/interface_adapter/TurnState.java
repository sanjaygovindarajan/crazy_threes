package interface_adapter;

public class TurnState {
    private String gameName = "";

    public TurnState(TurnState copy) {
        gameName = copy.gameName;
    }

    public TurnState() {}

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

}
