package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.GameInterface;
import interface_adapter.*;
import interface_adapter.play_card.PlayCardOutputBoundary;
import interface_adapter.save_game.SaveGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputBoundary;
import use_case.deck_actions.*; //Shuffle user story
import use_case.game_actions.load_game.*; //Load game user story
import use_case.game_actions.save_game.*; //Save game user story
import use_case.game_actions.start_game.*; //Start game user story
import use_case.player_actions.draw_card.*; //Draw card user story
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;

public class NewGameInteractor {

    private GameInterface game;
    private StartGameInputBoundary startGame; //Start game user story
    private LoadGameInputBoundary loadGame; //Load game user story
    private SaveGameInputBoundary saveGame; ///Save game user story
    private PlayCardInputBoundary playCard; //Play card user story
    private DrawCardInputBoundary drawCard; //Draw card user story
    private ShuffleInputBoundary shuffle; //Shuffle user story

    /**
     * Initializes all use case interactors.
     * @param dataAccess The data access object
     * @param loadGamePresenter The load game presenter
     * @param saveGamePresenter The save game presenter
     * @param playCardPresenter The play card presenter
     * @param startGamePresenter The start game presenter
     * @param drawCardPresenter The draw card presenter
     */

    public NewGameInteractor(
            DataAccessInterface dataAccess,
            LoadGameOutputBoundary loadGamePresenter,
            SaveGameOutputBoundary saveGamePresenter,
            PlayCardOutputBoundary playCardPresenter,
            StartGameOutputBoundary startGamePresenter,
            DrawCardOutputBoundary drawCardPresenter){
        loadGame = new LoadGameInteractor(dataAccess, loadGamePresenter);
        saveGame = new SaveGameInteractor(dataAccess, saveGamePresenter);
        playCard = new PlayCardInteractor(playCardPresenter);
        startGame = new StartGameInteractor(startGamePresenter);
        drawCard = new DrawCardInteractor(drawCardPresenter);
    }

    /**
     * Starts a new game.
     * @param inputData The input data for starting a game. Includes the player names.
     */
    public void startGame(StartGameInputData inputData){
        //Create a new game
        startGame.execute(inputData);
        //Gets the game object created
        this.game = startGame.getGame();
        //Makes sure all use case interactors act on the same game
        saveGame.setGame(this.game);
        playCard.setGame(this.game);
        drawCard.setGame(this.game);
        shuffle.setGame(this.game);
        //Prepares to call presenter
        startGame.present();
    }

    /**
     * Loads a new game.
     * @param inputData The input data. Includes the game name.
     */
    public void loadGame(LoadGameInputData inputData){
        //Attempts to load the game
        try {
            loadGame.execute(inputData);
        }
        catch(IllegalStateException e){
            //Faulty data access object
            System.out.println(e);
        }
        //Gets the game object loaded
        this.game = loadGame.getGame();
        //Makes sure all use case interactors act on the same game
        saveGame.setGame(this.game);
        playCard.setGame(this.game);
        drawCard.setGame(this.game);
        //shuffle.setGame(this.game);
        //Prepares to call presenter
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
}


