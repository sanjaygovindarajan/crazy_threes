package use_case;

import java.util.List;

import entity.Card;
import entity.Game;

public interface GameplayInputBoundary {

    void playCard(Game game, Card card);
    void drawCard(Game game);
    void createGame(List<String> playerNameList);

}
