package use_case.game_actions.load_game;

import data_access.DataAccessInterface;
import interface_adapter.load_game.ViewGamesOutputBoundary;
import interface_adapter.load_game.ViewGamesOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewGamesInteractor implements ViewGamesInputBoundary{
    private final DataAccessInterface userDataAccessObject;
    private final ViewGamesOutputBoundary presenter;

    public ViewGamesInteractor(DataAccessInterface userDataAccessObject, ViewGamesOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void loadSavedGames() {
        List<String> games = new ArrayList<>();
        try {
            games = userDataAccessObject.loadGames();
        } catch (IOException _){
            presenter.loadFailView();
        }

        List<String> gameNames = new ArrayList<>();
        for (String game : games) {
            String[] parts = game.split("&");
            if (parts.length > 0) {
                gameNames.add(parts[0] + "              " + parts[parts.length - 1]);
            }
        }
        ViewGamesOutputData outputData = new ViewGamesOutputData(gameNames);
        presenter.loadSuccessView(outputData);

    }
}
