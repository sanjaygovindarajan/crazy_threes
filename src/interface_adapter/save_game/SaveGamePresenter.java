package interface_adapter.save_game;

import view.TemporaryTurnView;

public class SaveGamePresenter implements SaveGameOutputBoundary {
    TemporaryTurnView view;
    public SaveGamePresenter(TemporaryTurnView view){this.view = view;}
    /**
     * Prints a success message
     * @param message The success message
     */
    @Override
    public void prepareSuccessView(String message) {
        System.out.println("Success! " + message);
    }

    /**
     * Prints an error message
     * @param error The error message
     */
    @Override
    public void prepareFailureView(String error) {
        System.out.println("Oh no! " + error);
        view.requestSaveGame();

    }
}
