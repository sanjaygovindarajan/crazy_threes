package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.Game;
import interface_adapter.*;
import use_case.deck_actions.ShuffleInteractor;
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
import view.TemporaryShuffleView;
import view.TemporaryThreeView;
import view.TemporaryTurnView;

public class NewGameInteractor {
    private Game game;
    private final StartGameInputBoundary startGame;
    private final LoadGameInputBoundary loadGame;
    private final SaveGameInputBoundary saveGame;
    private final PlayCardInputBoundary playCard;
    private final DrawCardInputBoundary drawCard;
    private ShuffleInteractor shuffle;

    public NewGameInteractor(DataAccessInterface dataAccess, TemporaryTurnView view, TemporaryShuffleView shuffleView){
        saveGame = new SaveGameInteractor(dataAccess, new SaveGamePresenter());
        playCard = new PlayCardInteractor(new StartGamePresenter(view));
        drawCard = new DrawCardInteractor(new StartGamePresenter(view));
        drawCard.getPresenter().setShuffle(shuffleView);
        startGame = new StartGameInteractor(new StartGamePresenter(view));
        loadGame = new LoadGameInteractor(dataAccess, new LoadGamePresenter(view), new StartGamePresenter(view));
    }

    public void startGame(StartGameInputData inputData){
        startGame.execute(inputData);
        this.game = startGame.getGame();
        saveGame.setGame(this.game);
        playCard.setGame(this.game);
        drawCard.setGame(this.game);
        startGame.present();
    }

    public void loadGame(LoadGameInputData inputData){
        try {
            loadGame.execute(inputData);
        }
        catch(IllegalStateException e){
            System.out.println(e);
        }
        this.game = loadGame.getGame();
        saveGame.setGame(this.game);
        playCard.setGame(this.game);
        // drawCard.setGame(this.game);
        loadGame.present(inputData);
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
    public DrawCardInputBoundary getDrawCard() { return drawCard; }
    public ShuffleInteractor getShuffle() {
        return shuffle;
    }

    public StartGameInputBoundary getStartGame() {
        return this.startGame;
    }
}


