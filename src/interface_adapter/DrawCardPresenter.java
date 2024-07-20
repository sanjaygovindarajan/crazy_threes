package interface_adapter;

import use_case.player_actions.DrawCardOutputBoundary;
import use_case.player_actions.DrawCardResponseModel;

public class DrawCardPresenter implements DrawCardOutputBoundary {
    @Override
    public void presentDrawCard(DrawCardResponseModel responseModel) {
        // Format the response model for the view
        System.out.println("Message: " + responseModel.getMessage());
        System.out.println("Success: " + responseModel.isSuccess());
    }
}