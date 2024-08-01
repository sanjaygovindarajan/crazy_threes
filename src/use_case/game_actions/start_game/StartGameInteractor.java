package use_case.game_actions.start_game;

import entity.Card;
import entity.Game;
import interface_adapter.start_game.StartGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;

public class StartGameInteractor implements StartGameInputBoundary {
    private Game game;
    private final StartGameOutputBoundary presenter;

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
        Card card = game.getDiscard().getCard();
        StartGameOutputData data = new StartGameOutputData(game.getCurrentPlayer().viewHand().toString(), game.getCurrentPlayer().getName(), card.toString(), card.getCurrentSuit());
        presenter.loadSuccessView(data);

    }

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
