package use_case.game_actions;

import use_case.game_actions.load_game.LoadGameInputBoundary;
import use_case.game_actions.load_game.LoadGameInputData;
import use_case.game_actions.save_game.SaveGameInputBoundary;
import use_case.game_actions.start_game.*;
import use_case.player_actions.draw_card.DrawCardInputBoundary;
import use_case.player_actions.play_card.PlayCardInputBoundary;
import use_case.deck_actions.*;

public interface NewGameInterface {
    void startGame(StartGameInputData inputData);
    void loadGame(LoadGameInputData inputData);
    SaveGameInputBoundary getSaveGame();
    PlayCardInputBoundary getPlayCard();
    ShuffleInputBoundary getShuffle();
    LoadGameInputBoundary getLoadGame();
    StartGameInputBoundary getStartGame();
    DrawCardInputBoundary getDrawCard();


}
