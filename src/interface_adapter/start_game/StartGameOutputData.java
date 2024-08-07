package interface_adapter.start_game;

/**
 * Output data for the start game presenter and the classes that inherit from it.
 */
public class StartGameOutputData {
    private final String playerCards;
    private final String playerName;
    private final String card;
    private final char currentSuit;

    /**
     * Creates a new output data object
     * @param playerCards The player's cards
     * @param playerName The player name
     * @param card The card on top of the discard pile
     * @param currentSuit The suit that the next player must follow
     */
    public StartGameOutputData(String playerCards, String playerName, String card, char currentSuit){
        this.playerCards = playerCards;
        this.playerName = playerName;
        this.card = card;
        this.currentSuit = currentSuit;
    }

    /**
     * Getter method for the player's cards
     * @return The player's cards, as a string
     */
    public String getPlayerCards(){
        return playerCards;
    }

    /**
     * Getter method for the player name
     * @return The player name
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Getter method for the top card of the discard pile
     * @return The top card, as a string
     */
    public String getCard(){ return card; }

    /**
     * Getter method for the current suit
     * @return The suit that the player must follow
     */
    public char getCurrentSuit(){ return currentSuit; }

}
