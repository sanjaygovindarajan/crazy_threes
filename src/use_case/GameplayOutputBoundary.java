package use_case;

import use_case.player_actions.GameplayOutputData;

public interface GameplayOutputBoundary {

    void prepareSuccessView(GameplayOutputData GameplayOutputData);

    void prepareFailView(String error) throws Exception;
}
