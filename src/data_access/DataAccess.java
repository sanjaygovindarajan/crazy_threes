package data_access;
import entity.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DataAccess implements DataAccessInterface {
    private final File databaseFile;
    public DataAccess() {
        this.databaseFile = new File("database.txt");
    }
    public DataAccess(File databaseFile) {
        this.databaseFile = databaseFile;
    }
    public DataAccess(String databaseFile) {
        this.databaseFile = new File(databaseFile);
    }

    /**
     * Loads a Game object from the database
     * @param name The identifier by which the game is stored in the database
     * @return A Game object corresponding to the Game stored in the database
     */
    public Game loadGameByName(String name) {
        String db = Files.readString(Path.of(this.databaseFile.getPath()));
        String[] gamesArray = db.split(",,,");
        for(String game: gamesArray){
            if(game.split(":")[0].equals(name)){
                return readGame(game);
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Saves a Game object in the database
     * @param game The game to be saved in the database
     * @param name The identifier of the game by which it can be accessed later from the database
     */
    public void saveGame(Game game, String name){
        String gameStr = convertGame(game, name);
        String db = gameStr + ",,," + Files.readString(Path.of(this.databaseFile.getPath()));
        Files.writeString(Path.of(this.databaseFile.getPath()), db);
    }

    /**
     * Converts a Card object to a String
     * @param card The Card object corresponding to the Card to be saved
     * @return A String with the first character corresponding to the suit of the card and the subsequent characters
     * corresponding to the number of the card
     */

    private String convertCard(Card card){
        return Character.toString(card.getCurrentSuit()) + Integer.toString(card.getCardNum());
    }

    /**
     * Converts a CardCollection object to a String
     * @param cardList A CardCollection object corresponding to the CardCollection to be saved
     * @return The CardCollection in string format (that is, the Card objects in string format separated by a comma)
     */
    private String convertCardCollection(CardCollection cardList){
        List<String> cardListStr = new ArrayList<>(cardList.getCardList().size());
        for(Card card : cardList.getCardList()){
            cardListStr.add(convertCard(card));
        }
        return String.join(",",cardListStr);
    }

    /**
     * Converts a Player object to a String
     * @param player A Player object corresponding to the player to be saved
     * @return The player in string format
     */
    private String convertPlayer(Player player){
        return String.join(";",player.getName(),convertCardCollection(player.viewHand()));
    }

    /**
     * Converts a Game object to a String and gives the game a name.
     * @param game A Game object corresponding to the game to be saved
     * @param name What the game should be named in the database
     * @return The game in string format, with the name
     */
    private String convertGame(Game game, String name){
        List<String> playerList = new ArrayList<>(game.getPlayers().size());
        for(Player player : game.getPlayers()){
            playerList.add(convertPlayer(player));
        }
        String playerListStr = String.join(",",playerList);
        return String.join(":",name,convertCardCollection(game.getDeck()),convertCardCollection(game.getDiscard()),playerListStr,Integer.toString(game.getTurn()));
    }

    /**
     * Creates a new Card based on a String.
     * @param cardString A two-character string where the first character refers to the suit and the subsequent
     *                   characters refer to the number of the card
     * @return A Card object with suit and number based on the input.
     */
    private Card readCard(String cardString){

        return new Card(Integer.parseInt(cardString.substring(1)), cardString.charAt(0));
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
     * Creates a new player based on a String.
     * @param playerString A String in the proper format
     * @return A Player object with attributes based on the input.
     */
    private Player readPlayer(String playerString){
        String[] playerArray = playerString.split(";");
        String name = playerArray[0];
        Hand hand = new Hand(readCardCollection(playerArray[1]));
        return new Player(name, hand); //Add once constructor is complete
    }

    /**
     * Creates a new game based on a String.
     * @param gameString A String in the proper format
     * @return A Game object with attributes based on the input
     */
    private Game readGame(String gameString){
        String[] gameArray = gameString.split(":");
        Deck deck = new Deck(readCardCollection(gameArray[1]));
        DeckDisposed discard = new DeckDisposed(readCardCollection(gameArray[2]));
        String[] playerArray = gameArray[3].split(",");
        List<Player> playerList = new ArrayList<>();
        for(String player: playerArray) {
            playerList.add(readPlayer(player));
        }
        int turn = Integer.parseInt(gameArray[4]);
        return new Game(deck, playerList, turn, discard); //Add once constructor is complete
    }


}
