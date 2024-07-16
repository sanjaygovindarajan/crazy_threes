package entity;

import java.util.*;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private static int turn;
    private DeckDisposed discard;
    private boolean isGameOver;

    public Game(List<String> playerNames) {
        deck = new Deck();
        discard = new DeckDisposed();
        players =  new ArrayList<>();
        for (String name: playerNames) {
            players.add(new Player(name));
        }
        turn = 1;
        isGameOver = false;
        this.discard = new DeckDisposed();
    }

    public void startGame() {
        deck.shuffle();
        dealCards(9);
        discard.addCard(deck.dealCard());
    }

    public void dealCards(int numCards) {
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
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

    public void playCard(Player player, int cardIndex) {
        Card card = player.getHand().viewCards().get(cardIndex);
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
            // what to do if invalid play
        }
    }

    private boolean isValidPlay(Card card) {
        Card topCard = discard.getCard();
        return card.getCardNum() == topCard.getCardNum() || card.getCurrentSuit() == topCard.getCurrentSuit();
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
