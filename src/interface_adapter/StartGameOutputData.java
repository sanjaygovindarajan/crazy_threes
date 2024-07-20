package interface_adapter;

public class StartGameOutputData {
    String playerCards;
    String playerName;

    public StartGameOutputData(String playerCards, String playerName){
        this.playerCards = playerCards;
        this.playerName = playerName;
    }

    public String getPlayerCards(){
        return playerCards;
    }

    public String getPlayerName(){
        return playerName;
    }


}
