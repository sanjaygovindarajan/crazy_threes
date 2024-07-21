package use_case.game_actions.load_game;
import entity.GameInterface;

public class LoadGameOutputData {

    private final GameInterface game;

    private final boolean useCaseFailed;
    private final String gameName;


    public LoadGameOutputData(GameInterface game, String gameName, boolean useCaseFailed) {
        this.game = game;
        this.gameName = gameName;
        this.useCaseFailed = useCaseFailed;
    }
    public String getGameName() {return gameName;}

    public GameInterface getGame() {
        return game;
    }

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}


