package use_case.game_actions.load_game;
import entity.Game;

public class LoadGameOutputData {

    private final Game game;
    private final boolean useCaseFailed;


    public LoadGameOutputData(Game game, boolean useCaseFailed) {
        this.game = game;
        this.useCaseFailed = useCaseFailed;
    }

    public Game getGame() {
        return game;
    }

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}


