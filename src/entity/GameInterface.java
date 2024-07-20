package entity;

import java.util.List;



public interface GameInterface {
    /**
     * Gets the player whose turn it is
     * @return The player whose turn it is
     */
    Player getCurrentPlayer();
    /**
     * Getter method for isGameOver instance variable.
     * @return Whether the Game is over or not
     */
    boolean isGameOver();
    /**
     * Plays a card, if possible. Otherwise throws an exception.
     * @param player The player who is playing the card
     * @param cardIndex The index of the card they are playing
     * @throws MissingCardException The card is not allowed to be played since it is not the right suit or number.
     */
    void playCard(Player player, int cardIndex) throws InvalidCardException;

    /**
     * Getter method for the current turn in the game.
     * @return The current turn number, expressed as an integer
     */
    int getTurn();
    /**
     * Getter method for the deck in the game
     * @return The deck for the game
     */
    Deck getDeck();
    /**
     * Getter method for the list of players.
     * @return The list of players in the game, expressed as a List
     */
    List<Player> getPlayers();
    /**
     * Gets the discard pile
     * @return The discard pile
     */
    DeckDisposed getDiscard();

    public default void playThree(char oldSuit, char suit) {

    }

    void setDeck(Deck deck);

    void setDiscard(DeckDisposed deckDisposed);
}
