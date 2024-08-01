package interface_adapter;
import entity.Game;

public class LoadGameOutputData {

    private final Game game;

    private final boolean useCaseFailed;
    private final String gameName;


    public LoadGameOutputData(Game game, String gameName, boolean useCaseFailed) {
        this.game = game;
        this.gameName = gameName;
        this.useCaseFailed = useCaseFailed;
    }
    public String getGameName() {return gameName;}

    public Game getGame() {
        return game;
    }

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}


