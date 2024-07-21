package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.*;
import use_case.deck_actions.ShuffleInteractor;
import use_case.game_actions.load_game.*;
import use_case.game_actions.save_game.*;
import use_case.game_actions.start_game.*;
import use_case.player_actions.*;
import use_case.player_actions.draw_card.*;
import view.*;

public class NewGameInteractor {
    private GameInterface game;
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
        drawCard.setGame(this.game);
        loadGame.present();
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


