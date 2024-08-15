package interface_adapter.view_rules;

import use_case.game_actions.read_rules.ReadRulesInputBoundary;

public class ReadRulesController {
    final ReadRulesInputBoundary readRulesInputBoundary;
    public ReadRulesController(ReadRulesInputBoundary readRulesInputBoundary) {
        this.readRulesInputBoundary = readRulesInputBoundary;
    }
    public void execute() {
        readRulesInputBoundary.execute();
    }

    /**
     * Getter method for the use case interactor
     * @return THe use case interactor
     */
    public ReadRulesInputBoundary getInteractor() {
        return this.readRulesInputBoundary;
    }
}
