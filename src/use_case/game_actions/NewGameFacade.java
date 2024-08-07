package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.GameInterface;
import interface_adapter.draw_card.DrawCardOutputBoundary;
import interface_adapter.load_game.LoadGameOutputBoundary;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.save_game.SaveGameOutputBoundary;
import interface_adapter.shuffle.ShuffleOutputBoundary;
import interface_adapter.start_game.StartGameOutputBoundary;
import use_case.deck_actions.*; //Shuffle user story
import use_case.game_actions.load_game.*; //Load game user story
import use_case.game_actions.save_game.*; //Save game user story
import use_case.game_actions.start_game.*; //Start game user story
import use_case.player_actions.draw_card.*; //Draw card user story
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;

/**
 * A façade for creating a new game.
 * The façade is responsible for updating other interactors with the new game.
 * However, the creation of the game occurs instead in the StartGameInteractor
 * and LoadGameInteractor classes, whose methods are not called outside of this
 * class.
 */
public class NewGameFacade {

    private final StartGameInputBoundary startGame;
    private final LoadGameInputBoundary loadGame;
    private final SaveGameInputBoundary saveGame;
    private final PlayCardInputBoundary playCard;
    private final DrawCardInputBoundary drawCard;
    private final ShuffleInputBoundary shuffle;

    /**
     * Initializes all use case interactors.
     * @param dataAccess The data access object
     * @param loadGamePresenter The load game presenter
     * @param saveGamePresenter The save game presenter
     * @param playCardPresenter The play card presenter
     * @param startGamePresenter The start game presenter
     * @param drawCardPresenter The draw card presenter
     */

    public NewGameFacade(
            DataAccessInterface dataAccess,
            LoadGameOutputBoundary loadGamePresenter,
            SaveGameOutputBoundary saveGamePresenter,
            PlayCardOutputBoundary playCardPresenter,
            StartGameOutputBoundary startGamePresenter,
            DrawCardOutputBoundary drawCardPresenter,
            ShuffleOutputBoundary shufflePresenter){
        loadGame = new LoadGameInteractor(dataAccess, loadGamePresenter);
        saveGame = new SaveGameInteractor(dataAccess, saveGamePresenter);
        playCard = new PlayCardInteractor(playCardPresenter);
        startGame = new StartGameInteractor(startGamePresenter);
        drawCard = new DrawCardInteractor(drawCardPresenter);
        shuffle = new ShuffleInteractor(shufflePresenter);
    }

    /**
     * Starts a new game.
     * @param inputData The input data for starting a game. Includes the player names.
     */
    public void startGame(StartGameInputData inputData){
        startGame.execute(inputData);
        setGames(startGame.getGame());
        startGame.present();
    }

    /**
     * Loads a new game.
     * @param inputData The input data. Includes the game name.
     */
    public void loadGame(LoadGameInputData inputData){
        try {
            loadGame.execute(inputData);
        }
        catch(IllegalStateException _){
        }
        setGames(loadGame.getGame());
        loadGame.present(inputData);
    }

    /**
     * Gets the save game use case interactor
     * @return The save game use case interactor
     */
    public SaveGameInputBoundary getSaveGame() {
        return saveGame;
    }

    /**
     * Gets the play card use case interactor
     * @return The play card use case interactor
     */
    public PlayCardInputBoundary getPlayCard() {
        return playCard;
    }

    /**
     * Gets the load game use case interactor
     * @return The load game use case interactor
     */
    public LoadGameInputBoundary getLoadGame() {
        return loadGame;
    }

    /**
     * Gets the draw card use case interactor
     * @return The draw card use case interactor
     */
    public DrawCardInputBoundary getDrawCard() { return drawCard; }

    /**
     * Gets the shuffle use case interactor
     * @return The shuffle use case interactor
     */
    public ShuffleInputBoundary getShuffle() {
        return shuffle;
    }

    /**
     * Gets the start game use case interactor
     * @return The start game use case interactor
     */
    public StartGameInputBoundary getStartGame() {
        return this.startGame;
    }

    private void setGames(GameInterface game){
        saveGame.setGame(game);
        playCard.setGame(game);
        drawCard.setGame(game);
        shuffle.setGame(game);
    }
}
