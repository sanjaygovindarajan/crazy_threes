package interface_adapter.start_game;

import java.util.ArrayList;
import java.util.List;

public class LoadSuccessState {
    private String message;
    private long timestamp;
    private String playerName;
    private List<String> playerCards = new ArrayList<>();;
    private String previousCard;
    private String currentSuit;

    public LoadSuccessState() {
        this.message = "Data loaded successfully!";
        this.timestamp = System.currentTimeMillis();
    }

    public LoadSuccessState(String message, long timestamp, String playerName, List<String> playerCards, String previousCard, String currentSuit) {
        this.message = message;
        this.timestamp = timestamp;
        this.playerName = playerName;
        this.playerCards = playerCards;
        this.previousCard = previousCard;
        this.currentSuit = currentSuit;
    }

    // Getters and Setters for all fields
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<String> playerCards) {
        this.playerCards = playerCards;
    }

    public String getPreviousCard() {
        return previousCard;
    }

    public void setPreviousCard(String previousCard) {
        this.previousCard = previousCard;
    }

    public String getCurrentSuit() {
        return currentSuit;
    }

    public void setCurrentSuit(String currentSuit) {
        this.currentSuit = currentSuit;
    }

    public String printCard(String card) {
        String num = card.substring(1);
        String suit = card.substring(0, 1);
        num = num.replace("11","Jack");
        num = num.replace("12","Queen");
        num = num.replace("13","King");
        num = num.replace("14","Ace");
        suit = suit.replace("S", "spades");
        suit = suit.replace("C", "clubs");
        suit = suit.replace("H", "hearts");
        suit = suit.replace("D", "diamonds");
        return (num + " of " + suit);
    }


}

