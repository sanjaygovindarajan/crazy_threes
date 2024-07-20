package interface_adapter;

public class StartGamePresenter implements StartGameOutputBoundary{
    @Override
    public void loadSuccessView(StartGameOutputData data) {
        System.out.println("It's " + data.getPlayerName() + "'s turn!");
        System.out.println("Their cards:");
        for(String card : data.getPlayerCards().split(",")){
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
            System.out.println(num + " of " + suit);
        }
    }
}
