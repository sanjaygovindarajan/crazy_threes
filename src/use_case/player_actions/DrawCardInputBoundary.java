package use_case.player_actions;

import entity.Game;
import entity.GameInterface;
import entity.Player;
import entity.MissingCardException;

public interface DrawCardInputBoundary {
    void handleDrawCard() throws MissingCardException;
}


