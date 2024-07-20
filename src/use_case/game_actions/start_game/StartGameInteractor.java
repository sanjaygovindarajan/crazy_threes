package use_case.game_actions.start_game;

import data_access.DataAccessInterface;
import entity.Game;
import interface_adapter.SaveGamePresenter;
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
    private LoadGameInputBoundary loadGame;
    private SaveGameInputBoundary saveGame;
    private PlayCardInputBoundary playCard;
    private DrawCardInputBoundary drawCard;
    private Shuffle shuffle;

    public StartGameInteractor(DataAccessInterface dataAccess){
        saveGame = new SaveGameInteractor(dataAccess, new SaveGamePresenter());
        playCard = new PlayCardInteractor();
        drawCard = new DrawCardInteractor();
    }
    /**
     * Starts a new game from the beginning.
     *
     * @param inputData The names of the players in the new game
     */
    @Override
    public void execute(StartGameInputData inputData) {
        this.game = new Game(inputData.getPlayers());
        saveGame.setGame(this.game);
        playCard.setGame(this.game);
        drawCard.setGame(this.game);
        shuffle.setGame(this.game);

    }
    public SaveGameInputBoundary getSaveGame() {
        return saveGame;
    }
    public PlayCardInputBoundary getPlayCard() {
        return playCard;
    }
    public LoadGameInputBoundary getLoadGame() {
        return loadGame;
    }
    public DrawCardInputBoundary getDrawCard() {
        return drawCard;
    }
    public Shuffle getShuffle() {
        return shuffle;
    }

    public void setGame(Game game){
        this.game = game;
    }
}
