package data_access;

import entity.Game;

import java.io.IOException;

public interface DataAccessInterface {
    Game loadGameByName(String name) throws IOException;
    void saveGame(String game) throws IOException;
}
