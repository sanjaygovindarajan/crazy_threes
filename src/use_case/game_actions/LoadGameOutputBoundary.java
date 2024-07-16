package use_case.game_actions;

public interface LoadGameOutputBoundary {

    void prepareSuccessView(LoadGameOutputData loadGameOutputData);

    void prepareFailView(String error) throws Exception;
}
