package use_case.game_actions.load_game;

public interface LoadGameOutputBoundary {

    void prepareSuccessView(LoadGameOutputData loadGameOutputData);

    void prepareFailView(String error) throws Exception;
}
