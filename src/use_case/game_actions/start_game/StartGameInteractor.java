package use_case.game_actions.start_game;

import data_access.DataAccessInterface;
import entity.Game;
import interface_adapter.SaveGamePresenter;
import interface_adapter.StartGameOutputBoundary;
import interface_adapter.StartGameOutputData;
import interface_adapter.StartGamePresenter;
import use_case.deck_actions.Shuffle;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInteractor;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.draw_card.DrawCardInteractor;
import use_case.player_actions.PlayCardInputBoundary;
import use_case.player_actions.PlayCardInteractor;

public class StartGameInteractor implements StartGameInputBoundary {
    private Game game;
    private StartGameOutputBoundary presenter;

    public StartGameInteractor(){
        presenter = new StartGamePresenter();
    }
    /**
     * Starts a new game from the beginning.
     *
     * @param inputData The names of the players in the new game
     */
    @Override
    public void execute(StartGameInputData inputData) {
        this.game = new Game(inputData.getPlayers());
        StartGameOutputData data = new StartGameOutputData(game.getCurrentPlayer().getHand().toString(), game.getCurrentPlayer().getName());
        presenter.loadSuccessView(data);

    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
