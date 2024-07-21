package use_case.game_actions.save_game;

import data_access.*;
import entity.*;
import interface_adapter.save_game.SaveGameOutputBoundary;

import java.io.IOException;

public class SaveGameInteractor implements SaveGameInputBoundary {
    final DataAccessInterface dataAccessObject;
    final SaveGameOutputBoundary presenter;
    GameInterface game;

    public SaveGameInteractor(DataAccessInterface dataAccessObject, SaveGameOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

    /**
     * Saves a game based on input data.
     * @param inputData The game name
     */
    @Override
    public void execute(SaveGameInputData inputData){
        try {
            dataAccessObject.saveGame(inputData.getGameName() + ":" + game.toString());
            presenter.prepareSuccessView("Game " + inputData.getGameName() + " saved.");
        } catch (IOException e) {
            presenter.prepareFailureView("Database file not found. Your game was unable to be saved.");
        }
    }

    /**
     * Sets the current game being played.
     * @param game The game currently being played.
     */
    public void setGame(GameInterface game){
        this.game = game;
    }
}
