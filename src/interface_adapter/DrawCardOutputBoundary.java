package interface_adapter;

import use_case.player_actions.DrawCardOutputData;

/**
 * Output boundary interface for presenting draw card operation results.
 * Defines the method required to present the outcome of the draw card operation to the view.
 */
public interface DrawCardOutputBoundary {
    /**
     * Presents the result of the draw card operation.
     *
     * @param outputData the output data containing the result of the operation
     */
        void presentDrawCard(DrawCardOutputData outputData);
    }



