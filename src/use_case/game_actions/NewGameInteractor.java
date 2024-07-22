package use_case.game_actions;

import data_access.DataAccessInterface;
import entity.GameInterface;
import interface_adapter.*;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.save_game.SaveGamePresenter;
import interface_adapter.shuffle.ShufflePresenter;
import use_case.deck_actions.*; //Shuffle user story
import use_case.game_actions.load_game.*; //Load game user story
import use_case.game_actions.save_game.*; //Save game user story
import use_case.game_actions.start_game.*; //Start game user story
import use_case.player_actions.draw_card.*; //Draw card user story
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInteractor;
import view.*;

public class NewGameInteractor {
    private GameInterface game;
    private final StartGameInputBoundary startGame; //Start game user story
    private final LoadGameInputBoundary loadGame; //Load game user story
    private final SaveGameInputBoundary saveGame; ///Save game user story
    private final PlayCardInputBoundary playCard; //Play card user story
    private final DrawCardInputBoundary drawCard; //Draw card user story
    private final ShuffleInputBoundary shuffle; //Shuffle user story

    /**
     * Initializes all the use case interactors.
     * @param dataAccess The data access object
     * @param view The view signifying that it is a player's turn
     * @param shuffleView The view signifying that the deck needs to be shuffled
     */
    public NewGameInteractor(DataAccessInterface dataAccess, TemporaryTurnView view, TemporaryShuffleView shuffleView){
        saveGame = new SaveGameInteractor(dataAccess, new SaveGamePresenter(view));
        playCard = new PlayCardInteractor(new StartGamePresenter(view));
        drawCard = new DrawCardInteractor(new StartGamePresenter(view));
        drawCard.getPresenter().setShuffle(shuffleView);
        startGame = new StartGameInteractor(new StartGamePresenter(view));
        loadGame = new LoadGameInteractor(dataAccess, new LoadGamePresenter(view), new StartGamePresenter(view));
        shuffle = new ShuffleInteractor(new ShufflePresenter(view));
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
        shuffle.setGame(this.game);
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


