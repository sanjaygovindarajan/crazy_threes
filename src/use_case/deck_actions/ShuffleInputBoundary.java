package use_case.deck_actions;

public interface ShuffleInputBoundary {
    /**
     * Replaces the deck with the discard pile and shuffles it.
     */
    void shuffle();
}