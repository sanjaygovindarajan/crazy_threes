package use_case.player_actions;

import data_access.DataAccessInterface;
import entity.Card;
import entity.Game;
import use_case.GameplayInputBoundary;
import use_case.game_actions.LoadGameOutputBoundary;

import java.util.List;

public class GameplayInteractor implements GameplayInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final LoadGameOutputBoundary userPresenter;

    public GameplayInteractor(DataAccessInterface userDataAccessObject,
                              LoadGameOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void playCard(Game game, Card card) {

    }

    @Override
    public void drawCard(Game game) {

    }

    @Override
    public void createGame(List<String> playerNameList) {

    }
}
