package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.Game;
import interface_adapter.LoadGamePresenter;
import interface_adapter.LoadGameViewModel;
import interface_adapter.SaveGamePresenter;
import interface_adapter.ViewManagerModel;
import use_case.deck_actions.Shuffle;
import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;
import use_case.game_actions.load_game.LoadGameInteractor;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.save_game.SaveGameInteractor;
import use_case.game_actions.start_game.StartGameInputBoundary;
import use_case.game_actions.start_game.StartGameInputData;
import use_case.game_actions.start_game.StartGameInteractor;
import use_case.player_actions.PlayCardInputBoundary;
import use_case.player_actions.PlayCardInteractor;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.draw_card.DrawCardInteractor;

public class NewGameInteractor {
    private Game game;
    private StartGameInputBoundary startGame;
    private LoadGameInputBoundary loadGame;
    private SaveGameInputBoundary saveGame;
    private PlayCardInputBoundary playCard;
    private DrawCardInputBoundary drawCard;
    private Shuffle shuffle;

    public NewGameInteractor(DataAccessInterface dataAccess){
        saveGame = new SaveGameInteractor(dataAccess, new SaveGamePresenter());
        playCard = new PlayCardInteractor();
        drawCard = new DrawCardInteractor();
        startGame = new StartGameInteractor();
        loadGame = new LoadGameInteractor(dataAccess, new LoadGamePresenter(new ViewManagerModel(), new LoadGameViewModel()));
    }

    public void startGame(StartGameInputData inputData){
        startGame.execute(inputData);
        this.game = startGame.getGame();
        saveGame.setGame(this.game);
        playCard.setGame(this.game);
        drawCard.setGame(this.game);
        shuffle.setGame(this.game);
    }

    public void loadGame(LoadGameInputData inputData) throws Exception {
        loadGame.execute(inputData);
        this.game = loadGame.getGame();
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

    public StartGameInputBoundary getStartGame() {
        return this.startGame;
    }
}


