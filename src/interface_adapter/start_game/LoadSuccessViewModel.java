package interface_adapter.start_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;

public class LoadSuccessViewModel extends ViewModel {

    public final String SUCCESS_TITLE_LABEL = "Load Successful";
    public final String SUCCESS_MESSAGE_LABEL = "The data has been loaded successfully!";

    private LoadSuccessState state = new LoadSuccessState();

    public LoadSuccessViewModel() {
        super("load success");
    }

    public void setState(LoadSuccessState newState) {
        LoadSuccessState oldState = this.state;
        this.state = newState;
        support.firePropertyChange("state", oldState, newState);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoadSuccessState getState() {
        return state;
    }

    // Additional helper method to set state from StartGameOutputData
    public void setStateFromStartGameData(StartGameOutputData data) {
        String playerName = data.getPlayerName();
        List<String> playerCards = Arrays.asList(data.getPlayerCards().split(","));
        String previousCard = printCard(data.getCard());
        String currentSuit = data.getCard().charAt(1) == '3' ? getSuitName(data.getCurrentSuit()) : null;

        this.state = new LoadSuccessState("Data loaded successfully!", System.currentTimeMillis(), playerName, playerCards, previousCard, currentSuit);
        firePropertyChanged();
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

    private String getSuitName(char suit) {
        switch (suit) {
            case 'S': return "spades";
            case 'C': return "clubs";
            case 'H': return "hearts";
            case 'D': return "diamonds";
            default: return null;
        }
    }
}
