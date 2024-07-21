package entity;

import java.util.List;

/**
 * A simple interface that allows for playing cards and getting and setting certain instance variables.
 */
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

    /**
     * Plays a three and changes the suit
     * @param index The index of the three
     * @param suit The new suit
     * @throws InvalidCardException The card at the index is not actually a three
     */
    void playThree(int index, char suit) throws InvalidCardException;

    /**
     * Sets the deck to a new deck.
     * @param deck The new deck
     */
    void setDeck(Deck deck);

    /**
     * Sets the discard to a new pile.
     * @param deckDisposed The new discard pile
     */
    void setDiscard(DeckDisposed deckDisposed);

    /**
     * Returns whether the current player has a playable card.
     * @return Whether the current player has a playable card.
     */
    boolean hasPlayableCard();
}
