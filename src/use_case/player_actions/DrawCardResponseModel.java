package use_case.player_actions;

public class DrawCardResponseModel {
    private final String message;
    private final boolean success;

    public DrawCardResponseModel(String message, boolean success) {
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
