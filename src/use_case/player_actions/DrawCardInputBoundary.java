package use_case.player_actions;

import entity.MissingCardException;

public interface DrawCardInputBoundary {
    void handleDrawCard() throws MissingCardException;
}


