package interface_adapter;

import use_case.player_actions.DrawCardOutputData;

/**
 * Formats and presents the result of a draw card operation.
 * Implements the DrawCardOutputBoundary interface to handle the presentation of the
 * draw card output data.
 */
public class DrawCardPresenter implements DrawCardOutputBoundary {
    /**
     * Presents the result of the draw card operation.
     * Formats the output data and prints it to the console.
     *
     * @param outputData the output data containing the result of the draw card operation
     */
    @Override
    public void presentDrawCard(DrawCardOutputData outputData) {
        // Format the output data for the view
        System.out.println("Message: " + outputData.getMessage());
        System.out.println("Success: " + outputData.isSuccess());
    }
}