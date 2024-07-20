package use_case.game_actions.start_game;

import entity.Game;
import interface_adapter.StartGameOutputBoundary;
import interface_adapter.StartGameOutputData;
import interface_adapter.StartGamePresenter;

public class StartGameInteractor implements StartGameInputBoundary {
    private Game game;
    private StartGameOutputBoundary presenter;

    public StartGameInteractor(StartGameOutputBoundary presenter){
        this.presenter = presenter;
    }
    /**
     * Starts a new game from the beginning.
     *
     * @param inputData The names of the players in the new game
     */
    @Override
    public void execute(StartGameInputData inputData) {
        this.game = new Game(inputData.getPlayers());
    }

    public void present(){
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
