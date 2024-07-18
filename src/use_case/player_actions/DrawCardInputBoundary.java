package use_case.player_actions;

import entity.Game;
import entity.Player;
import entity.MissingCardException;

public interface DrawCardInputBoundary {
    void handleDrawCard(Game game, Player player) throws MissingCardException;
}


