package use_case.player_actions.draw_card;

import entity.Game;
import entity.MissingCardException;

public interface DrawCardInputBoundary {
    void handleDrawCard() throws MissingCardException;

    void setGame(Game game);
}


