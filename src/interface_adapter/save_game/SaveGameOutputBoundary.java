package interface_adapter.save_game;

public interface SaveGameOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailureView(String error);
}
