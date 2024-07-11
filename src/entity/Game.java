package entity;

import java.util.*;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private Player turn;
    private DeckDisposed discard;
    private boolean isGameOver;

    public Game(List<String> playerNames) {
        deck = new Deck();
        discard = new DeckDisposed();
        players =  new ArrayList<>();
        for (String name: playerNames) {
            players.add(new Player(name));
        }
        this.turn = players.get(0);
        isGameOver = false;
        this.discard = new DeckDisposed();
    }

    public void startGame() {
        deck.shuffle();
        dealCards(5);
        discard.addCard(deck.dealCard());
    }

    public void dealCards(int numCards) {
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
                deck.dealCard(player); // maybe this should make
            }
        }
    }

    public Player getCurrentPlayer() {
        return this.turn;
    }

    public DeckDisposed getDiscard() {
        return this.discard;
    }

    public void playCard(Player player, int cardIndex) {
        Card card = player.getHand().viewCards().get(cardIndex);
        if (isValidPlay(card)) {
            player.playCard(this, cardIndex); //unsure about cardIndex here
            discard.addCard(card);
            if (player.hasWin()) {
                isGameOver = true;
            } else {
                advanceTurn();
            }
        }
        else {
            // what to do if invalid play
        }
    }
    private boolean isValidPlay(Card card) {
        Card topCard = discard.peekTopCard();
        return card.getCardNum() == topCard.getCardNum() || card.getCurrentSuit() == topCard.getCurrentSuit();
    }

    private void advanceTurn() {
        int currentIndex = players.indexOf(turn);
        turn = players.get((currentIndex + 1) % players.size());
    }

    public boolean isGameOver() {
        for(Player player: this.players) {
            if (player.hasWin()) {
                return true;
            }
        }
        return false;
    }

}
