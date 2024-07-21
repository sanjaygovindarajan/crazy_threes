package use_case.game_actions.read_rules;

import interface_adapter.ReadRulesOutputBoundary;

public class ReadRulesInteractor implements ReadRulesInputBoundary {
    final ReadRulesOutputBoundary presenter;

    public ReadRulesInteractor(ReadRulesOutputBoundary presenter) {
        this.presenter = presenter;

    }

    /**
     * Displays rules based on a fixed string.
     */
    @Override
    public void execute(boolean gameStarted){
        presenter.prepareSuccessView(
    "The rules are as follows: \n " +
            "Each player is dealt 9 cards, the balance of the cards becomes the deck. The first card is placed \n" +
            "face up in the center, becoming the top card of the disposed deck. If a three is turned, it is  \n" +
            "buried in the middle of the deck. \n" +
            "\n" +
            "Each player must place one card face up on the disposed deck on their turn. Each card played \n" +
            "(other than a three) must match the card showing on the discard pile, either suit or in \n" +
            "denomination. \n" +
            "\n" +
            "Example: if the 4 of Clubs if the top card in the discard pile, any clubs may be played on it or /n" +
            "any 4s. \n" +
            "\n" +
            "If unable to play, cards are drawn from the top of the face down deck until a play is possible, or \n" +
            "until the deck is empty. If unable to play when the deck is empty, the disposed deck is shuffled \n" +
            "and set face down as the deck except for the top card that stays as the top cards from the \n" +
            "disposed deck and then continue to draw cards until they can play. \n" +
            "\n" +
            "Threes are wild! That is, a three may be played at any time in turn, and the player need only \n" +
            "specify a suit for it (but never a number). The next player must play either a card of the specified \n" +
            "suit or a three. \n" +
            "\n" +
            "The player that runs out of cards wins! You can save your wins by saving the game and \n" +
            "accumulate them by loading your game and play again with the same people. \n", gameStarted); //type out rules here (in empty string)
    }

}
