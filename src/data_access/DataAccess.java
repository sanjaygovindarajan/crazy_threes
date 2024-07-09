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
    public Game loadGameByName(String name) {
        String db = Files.readString(Path.of(this.databaseFile.getPath()));
        String[] gamesArray = db.split(",,,");
        for(String game: gamesArray){
            if(game.split(":")[0].equals(name)){
                return readGame(game);
            }
        }
        return new Game();
    }
    public void saveGame(Game game, String name){
        String gameStr = convertGame(game, name);
        String db = gameStr + ",,," + Files.readString(Path.of(this.databaseFile.getPath()));
        Files.writeString(Path.of(this.databaseFile.getPath()), db);
    }

    private String convertCard(Card card){
        return Character.toString(card.getCurrentSuit()) + Integer.toString(card.getCardNum());
    }
    private String convertCardCollection(CardCollection cardList){
        List<String> cardListStr = new ArrayList<>(cardList.getCardList().size());
        for(Card card : cardList.getCardList()){
            cardListStr.add(convertCard(card));
        }
        return String.join(",",cardListStr);
    }
    private String convertPlayer(Player player){
        return String.join(";",player.getName(),convertCardCollection(player.viewHand()));
    }
    private String convertGame(Game game, String name){
        List<String> playerList = new ArrayList<>(game.getPlayers().size());
        for(Player player : game.getPlayers()){
            playerList.add(convertPlayer(player));
        }
        String playerListStr = String.join(",",playerList);
        return String.join(":",name,convertCardCollection(game.getDeck()),convertCardCollection(game.getDiscard()),playerListStr,Integer.toString(game.getTurn()));
    }

    private Card readCard(String cardString){
        return new Card(cardString.charAt(1), cardString.charAt(0));
    }

    private List<Card> readCardCollection(String cardString){
        List<String> cardListStr = Arrays.asList(cardString.split(","));
        List<Card> cardList = new ArrayList<>(cardListStr.size());
        for(String card : cardListStr){
            cardList.add(readCard(card));
        }
        return cardList;
    }

    private Player readPlayer(String playerString){
        String[] playerArray = playerString.split(";");
        String name = playerArray[0];
        Hand hand = new Hand(readCardCollection(playerArray[1]));
        return new Player(name, hand); //Add once constructor is complete
    }

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
        return new Game(); //Add once constructor is complete
    }


}
