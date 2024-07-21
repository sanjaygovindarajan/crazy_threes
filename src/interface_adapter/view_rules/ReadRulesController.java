package interface_adapter.view_rules;

import use_case.game_actions.read_rules.ReadRulesInputBoundary;

public class ReadRulesController {
    final ReadRulesInputBoundary readRulesInputBoundary;
    public ReadRulesController(ReadRulesInputBoundary readRulesInputBoundary) {
        this.readRulesInputBoundary = readRulesInputBoundary;
    }
    public void execute(boolean gameStarted) {
        readRulesInputBoundary.execute(gameStarted);
    }
}
