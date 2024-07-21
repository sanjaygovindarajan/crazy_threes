package interface_adapter;

public interface LoadGameOutputBoundary {

    void prepareSuccessView(LoadGameOutputData loadGameOutputData);

    void prepareFailView(String error);
}
