package data_access;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataAccess implements DataAccessInterface {
    private final File databaseFile;
    public DataAccess() {
        this.databaseFile = new File("src/data_access/database.txt");
    }
    public DataAccess(File databaseFile) {
        this.databaseFile = databaseFile;
    }
    public DataAccess(String databaseFile) {
        this.databaseFile = new File(databaseFile);
    }

    /**
     * Loads a list of all the games.
     * @return A List containing a string encoding each game
     */
    public List<String> loadGames() throws IOException {
        String db = Files.readString(Path.of(this.databaseFile.getPath()));
        String[] gamesArray = db.split(",,,");
        return Arrays.asList(gamesArray);
    }

    /**
     * Saves a string representing a game in the database
     * @param game The string of the game to be saved in the database
     */
    public void saveGame(String game, LocalDateTime date) throws IOException{
        String existing = Files.readString(Path.of(this.databaseFile.getPath()));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(dateFormatter);
        String gameEntry = game + "&" + formattedDate;
        String db;
        if(existing.isEmpty()) {
            db = gameEntry;
        } else {
            db = gameEntry + ",,," + existing;
        }
        Files.writeString(Path.of(this.databaseFile.getPath()), db);
    }
}
