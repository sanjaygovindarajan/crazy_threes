package use_case;

import entity.Game;

public interface DatabaseInputBoundary {
    void loadGameByName(String name);
    void saveGame(Game game);
}
