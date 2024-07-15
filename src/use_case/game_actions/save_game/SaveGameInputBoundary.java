package use_case.game_actions.save_game;

import java.io.IOException;

public interface SaveGameInputBoundary {
    void execute(SaveGameInputData inputData) throws IOException;
}
