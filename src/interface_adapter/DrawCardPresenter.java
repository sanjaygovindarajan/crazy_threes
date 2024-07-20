package interface_adapter;

import use_case.player_actions.DrawCardOutputBoundary;
import use_case.player_actions.DrawCardOutputData;

public class DrawCardPresenter implements DrawCardOutputBoundary {
    @Override
    public void presentDrawCard(DrawCardOutputData outputData) {
        // Format the response model for the view
        System.out.println("Message: " + outputData.getMessage());
        System.out.println("Success: " + outputData.isSuccess());
    }
}