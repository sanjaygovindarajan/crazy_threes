package use_case.game_actions.load_game;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.load_game.LoadGameOutputBoundary;
import interface_adapter.start_game.StartGameOutputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadGameInteractor implements LoadGameInputBoundary {

    private final DataAccessInterface userDataAccessObject;
    private final LoadGameOutputBoundary presenter;
    private GameInterface currentGame;

    public LoadGameInteractor(DataAccessInterface userDataAccessObject,
                              LoadGameOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = userPresenter;
    }

    @Override
    public void execute(LoadGameInputData loadGameInputData) throws IllegalStateException {

        GameInterface game = null;
        String name = loadGameInputData.getGameName();
        try {
            if (userDataAccessObject != null) {
                List<String> gamesList = userDataAccessObject.loadGames();
                for(String gameStr : gamesList) {
                    if(gameStr.split("&")[0].trim().equals(name)){
                        game = readGame(gameStr);
                    }
                }
                // throw new IndexOutOfBoundsException();
                // game = userDataAccessObject.loadGameByName(loadGameInputData.getGameName());
            } else {
                throw new IllegalStateException("Data access object is not initialized.");
            }

            if (game == null) {
                presenter.prepareFailView("This game doesn't exist.");
            } else {

                this.currentGame = game;
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                presenter.prepareFailView("This game doesn't exist.");
            }
            else {

                presenter.prepareFailView(e.getMessage());}

            }

    }

    /**
     * Call start game presenter to load
     * @param loadGameInputData The input data of load game.
     */
    public void present(LoadGameInputData loadGameInputData){
        try {
            StartGameOutputData outputData = new StartGameOutputData(
                    currentGame.getCurrentPlayer().viewHand().toString(),
                    currentGame.getCurrentPlayer().getName(),
                    currentGame.getDiscard().getCard().toString(),
                    currentGame.getDiscard().getSuit()
            );
            presenter.loadSuccessView(outputData);
        } catch(NullPointerException e){
        }
    }

    @Override
    public GameInterface getGame() {
        return currentGame;
    }

    /**
     * Creates a new game based on a String.
     * @param gameString A String in the proper format
     * @return A Game object with attributes based on the input
     */
    private GameInterface readGame(String gameString){
        String[] gameArray = gameString.split("&");
        Deck deck = new Deck(readCardCollection(gameArray[1]));
        DeckDisposed discard = new DeckDisposed(readCardCollection(gameArray[2]));
        String[] playerArray = gameArray[3].split("/");
        List<Player> playerList = new ArrayList<>();
        for(String player: playerArray) {
            playerList.add(readPlayer(player));
        }
        int turn = Integer.parseInt(gameArray[4].trim());
        return new Game(deck, playerList, turn, discard); //Add once constructor is complete
    }

    /**
     * Creates a new player based on a String.
     * @param playerString A String in the proper format
     * @return A Player object with attributes based on the input.
     */
    private Player readPlayer(String playerString){
        String[] playerArray = playerString.split(";");
        String name = playerArray[0];
        Hand hand = new Hand(readCardCollection(playerArray[1]));
        if(name.charAt(0) == '#'){
            return new Bot(name, hand);
        } else {
            return new Player(name, hand);
        }
    }

    /**
     * Creates a list of cards based on a String.
     * @param cardString A string containing a list of string representations of cards separated by a comma
     * @return A list of cards contained in the input string.
     */
    private List<Card> readCardCollection(String cardString){
        List<String> cardListStr = Arrays.asList(cardString.split(","));
        List<Card> cardList = new ArrayList<>(cardListStr.size());
        for(String card : cardListStr){
            cardList.add(readCard(card));
        }
        return cardList;
    }

    /**
     * Creates a new Card based on a String.
     * @param cardString A two-character string where the first character refers to the suit and the subsequent
     *                   characters refer to the number of the card
     * @return A Card object with suit and number based on the input.
     */
    private Card readCard(String cardString){
        int cardNum = Integer.parseInt(cardString.substring(1));
        if(cardNum == 3){
            return new Three(cardString.charAt(0));
        }
        else {
            return new Card(Integer.parseInt(cardString.substring(1)), cardString.charAt(0));
        }
    }


}



