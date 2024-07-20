package use_case.player_actions;
/**
 * Represents the data returned from a draw card operation.
 * Encapsulates the result of the operation, including a message and a success flag.
 */
public class DrawCardOutputData {
    private final String message;
    private final boolean success;

    /**
     * Constructs a new DrawCardOutputData with the specified message and success status.
     *
     * @param message a message describing the result of the draw card operation
     * @param success a boolean indicating if the draw card operation was successful
     */

    public DrawCardOutputData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Gets the message describing the result of the draw card operation.
     *
     * @return the result message
     */

    public String getMessage() {
        return message;
    }

    /**
     * Checks if the draw card operation was successful.
     *
     * @return true if the operation was successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }
}
