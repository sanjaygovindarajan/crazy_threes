package interface_adapter;

public class PlayCardPresenter extends StartGamePresenter implements PlayCardOutputBoundary{
    /**
     * Switches to the play card view
     */
    @Override
    public void switchView() {
        viewManagerModel.setActiveView("Play Card");
    }

    /**
     * Requests that the player choose a different suit.
     * @param suit The current suit
     */
    @Override
    public void loadThreeView(char suit) {
        threeView.requestAction(suit);

    }

    /**
     * Displays a win message declaring that the player has won.
     * @param player The name of the player that won
     */
    @Override
    public void winMessage(String player){
        System.out.println("Congratulations " + player + " wins!");
    }

}
