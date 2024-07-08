package data_access;

import entity.Game;

public interface DataAccessInterface {
    Game loadGameByName(String name);
    void saveGame(Game game, String name);
}
