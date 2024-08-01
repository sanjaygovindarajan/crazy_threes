package interface_adapter;

public interface SaveGameOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailureView(String error);
}
