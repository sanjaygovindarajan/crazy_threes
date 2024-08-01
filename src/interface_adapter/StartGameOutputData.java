package interface_adapter;

public class StartGameOutputData {
    private final String playerCards;
    private final String playerName;
    private final String card;
    private final char currentSuit;


    public StartGameOutputData(String playerCards, String playerName, String card, char currentSuit){
        this.playerCards = playerCards;
        this.playerName = playerName;
        this.card = card;
        this.currentSuit = currentSuit;
    }

    public String getPlayerCards(){
        return playerCards;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getCard(){ return card; }
    public char getCurrentSuit(){ return currentSuit; }


}
