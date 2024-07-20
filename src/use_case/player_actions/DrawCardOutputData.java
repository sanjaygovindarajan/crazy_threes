package use_case.player_actions;

public class DrawCardOutputData {
    private final String message;
    private final boolean success;

    public DrawCardOutputData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
