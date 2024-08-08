package data_access;



import java.io.IOException;
import java.time.LocalDateTime;

import java.util.List;

public interface DataAccessInterface {
    List<String> loadGames() throws IOException;
    void saveGame(String game, LocalDateTime date) throws IOException;
}
