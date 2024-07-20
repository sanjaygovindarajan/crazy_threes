package interface_adapter;
import use_case.player_actions.DrawCardInputBoundary;

public class DrawCardController {
    final DrawCardInputBoundary drawCardInteractor;

    public DrawCardController(DrawCardInputBoundary drawCardInteractor) {
        this.drawCardInteractor = drawCardInteractor;
    }

    public void drawCard() throws MissingCardException {
        drawCardInteractor.handleDrawCard();
    }
}
