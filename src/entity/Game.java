package entity;

import java.util.*;

public class Game implements GameInterface{
    private final Deck deck;
    private final List<Player> players;
    private int turn;
    private final DeckDisposed discard;
    private boolean isGameOver;

    public Game(List<String> playerNames) {
        this.deck = createNewDeck();
        this.discard = new DeckDisposed();
        this.players =  new ArrayList<>();
        for (String name: playerNames) {
            this.players.add(new Player(name));
        }
        turn = 0;
        this.isGameOver = false;
        startGame();
    }

    public Game(Deck deck, List<Player> playerList, int turn, DeckDisposed discard) {
        this.deck = deck;
        this.players = playerList;
        this.turn = turn;
        this.discard = discard;
        this.isGameOver = false;
    }

    /**
     * Creates a full deck of cards.
     * @return A full Deck with 52 cards
     */
    private Deck createNewDeck(){
        Deck deck = new Deck();
        for(char c : new char[]{'S', 'C', 'H', 'D'}){
            for(int i : new int[]{2,4,5,6,7,8,9,10,11,12,13,14}){
                deck.addCard(new Card(i, c));
            }
            deck.addCard(new Three(c));
        }
        return deck;
    }

    /**
     * Starts the game and deals 9 cards to each player.
     */
    private void startGame() {
        deck.shuffle();
        dealCards(9);
        discard.addCard(deck.dealCard());
    }

    /**
     * Deals a set number of cards to every Player in the Game
     * @param numCards The number of cards to be dealt to each Player
     */
    private void dealCards(int numCards) {
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
                player.drawCard(deck);
            }
        }
    }

    /**
     * Gets the player whose turn it is
     * @return The player whose turn it is
     */

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    /**
     * Gets the discard pile
     * @return The discard pile
     */
    public DeckDisposed getDiscard() {
        return this.discard;

    }

    /**
     * Plays a card, if possible. Otherwise, throws an exception.
     * @param player The player who is playing the card
     * @param cardIndex The index of the card they are playing
     * @throws InvalidCardException The card is not allowed to be played since it is not the right suit or number.
     */

    public void playCard(Player player, int cardIndex) throws InvalidCardException {
        Card card = player.viewHand().getCardList().get(cardIndex);

        if (isValidPlay(card)) {
            player.playCard(this, cardIndex);
            discard.addCard(card);
            if (player.hasWin()) {
                isGameOver = true;
            } else {
                advanceTurn();
            }
        }
        else {
            throw new InvalidCardException();
        }
    }


    /**
     * Sets the new suit, if possible
     * @param c The new suit
     */
    public void setCurrentSuit(char c){
        discard.getCard().setNewSuit(c);
    }


    /**
     * Checks whether a Card object can be played.
     * @param card The Card object to check whether it can be played
     * @return Whether the card can be played
     */

    public boolean isValidPlay(Card card) {
        if (discard.getCardList().isEmpty()) {
            return true;
        } else {
            Card topCard = discard.getCard();
            return card.getCardNum() == topCard.getCardNum() || card.getCurrentSuit() == topCard.getCurrentSuit() || card.getCardNum() == 3;
        }
    }

    /**
     * Moves the turn to the next player
     */
    private void advanceTurn() {
        this.turn = (this.turn + 1) % players.size();

    }

    /**
     * Getter method for isGameOver instance variable.
     * @return Whether the Game is over or not
     */


    public boolean isGameOver() {
        return this.isGameOver;
    }

    /**
     * Getter method for the list of players.
     * @return The list of players in the game, expressed as a List
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Getter method for the deck in the game
     * @return The deck for the game
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Getter method for the current turn in the game.
     * @return The current turn number, expressed as an integer
     */
    public int getTurn(){
        return this.turn;
    }

    /**
     * Converts a Game object to a String .
     * and gives the game a name.
     * @return The game in string format, with the name
     */
    public String toString(){
        List<String> playerList = new ArrayList<>(players.size());
        for(Player player : players){
            playerList.add(player.toString());
        }
        String playerListStr = String.join(",",playerList);
        return String.join(":",deck.toString(),discard.toString(),playerListStr,Integer.toString(turn));
    }
}
