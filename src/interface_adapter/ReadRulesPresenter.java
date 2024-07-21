package interface_adapter;

import view.TemporaryDefaultView;
import view.TemporaryTurnView;

public class ReadRulesPresenter implements ReadRulesOutputBoundary{
    TemporaryTurnView view;
    TemporaryDefaultView defaultView;
    public ReadRulesPresenter(TemporaryTurnView view) {
        this.view = view;
        this.defaultView = defaultView;
    }

    /**
     * Prints a success message
     * @param message The success message to be printed
     */
    @Override
    public void prepareSuccessView(String message, boolean gameStarted) {
        System.out.println("Success! " + message);
        if(gameStarted){
            view.requestAction();
        } else {
            defaultView.requestAction();
        }
    }
}
