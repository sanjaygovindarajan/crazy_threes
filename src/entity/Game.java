package entity;

import java.util.*;

public class Game {
    private Deck deck;
    private final List<Player> players;
    private int turn;
    private DeckDisposed discard;
    private boolean isGameOver;

    public Game(List<String> playerNames) {
        deck = createNewDeck();
        discard = new DeckDisposed();
        players =  new ArrayList<>();
        for (String name: playerNames) {
            players.add(new Player(name));
        }
        turn = 1;
        this.isGameOver = false;
        this.discard = new DeckDisposed();
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

    public void startGame() {
        deck.shuffle();
        dealCards(9);
        discard.addCard(deck.dealCard());
    }

    /**
     * Deals a set number of cards to every Player in the Game
     * @param numCards The number of cards to be dealt to each Player
     */
    public void dealCards(int numCards) {
        Card card = null;
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
                player.drawCard(deck);
            }
        }
    }
    public Player getCurrentPlayer() {;
        return players.get(turn);
    }
                Hand.cardList.add(deck.dealCard()); // idk
            }
        }
    } //FIX ME!!!!!!!!!

    public class Counter {
        public static void advanceTurn() {
            turn +=1;
        }

        public static int getTurnNum() {
            return turn;
        }
    } //checked

    public Player getCurrentPlayer() {
        int turnNum = Counter.getTurnNum();
        while (turnNum - players.size() > 0){
            turnNum -= players.size();
        }
        return players.get(turnNum - 1);
    } //checked



    public DeckDisposed getDiscard() {
        return this.discard;

    }

    public void playCard(Player player, int cardIndex) throws MissingCardException {
        Card card = player.viewHand().viewCards().get(cardIndex);
        if (isValidPlay(card)) {
            player.playCard(this, cardIndex); //unsure about cardIndex here
            discard.addCard(card);
            if (player.hasWin()) {
                isGameOver = true;
            } else {
                Counter.advanceTurn();
            }
        }
        else {
            throw new MissingCardException();
        }
    }


    /**
     * Checks whether a Card object can be played.
     * @param card The Card object to check whether it can be played
     * @return Whether the card can be played
     */

    private boolean isValidPlay(Card card) {
        Card topCard = discard.getCard();
        return card.getCardNum() == topCard.getCardNum() || card.getCurrentSuit() == topCard.getCurrentSuit() || card.getCardNum() == 3;
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
}
