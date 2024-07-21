package data_access;

import entity.Game;

import java.io.IOException;
import java.util.List;

public interface DataAccessInterface {
    List<String> loadGames() throws IOException;
    void saveGame(String game) throws IOException;
    List<String> getAllGames() throws IOException;
}
