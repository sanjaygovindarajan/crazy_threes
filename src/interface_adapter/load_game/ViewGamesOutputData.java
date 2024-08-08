package interface_adapter.load_game;

import java.util.List;

public class ViewGamesOutputData {
    private final List<String> games;

    public ViewGamesOutputData(List<String> games) {
        this.games = games;
    }

    public List<String> getGames(){
        return this.games;
    }
}
