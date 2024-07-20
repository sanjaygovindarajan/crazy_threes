package data_access;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
        // for(String game: gamesArray){
            // if(game.split(":")[0].equals(name)){
                // return readGame(game);
            // }
        // }
        // throw new IndexOutOfBoundsException();
    }

    /**
     * Saves a string representing a game in the database
     * @param game The string of the game to be saved in the database
     */
    public void saveGame(String game) throws IOException{
        String existing = Files.readString(Path.of(this.databaseFile.getPath()));
        String db;
        if(existing.isEmpty()) {
            db = game;
        } else {
            db = game + ",,," + existing;
        };
        Files.writeString(Path.of(this.databaseFile.getPath()), db);
    }






}
