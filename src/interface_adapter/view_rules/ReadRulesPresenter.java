package interface_adapter.view_rules;

public class ReadRulesPresenter implements ReadRulesOutputBoundary {
    public ReadRulesPresenter(TemporaryTurnView view, TemporaryDefaultView defaultView) {
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
