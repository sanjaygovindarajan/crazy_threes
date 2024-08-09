package interface_adapter.load_game;

import java.util.List;

public class ViewGamesOutputData {
    private final List<String> games;

    /**
     * Creates a new output data object.
     * @param games All games in the database.
     */
    public ViewGamesOutputData(List<String> games) {
        this.games = games;
    }

    /**
     * Getter method for all games.
     * @return All games in the database.
     */
    public List<String> getGames(){
        return this.games;
    }
}
