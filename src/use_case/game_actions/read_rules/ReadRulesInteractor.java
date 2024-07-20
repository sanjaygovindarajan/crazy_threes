package use_case.game_actions.read_rules;

import interface_adapter.ReadRulesOutputBoundary;

public class ReadRulesInteractor implements ReadRulesInputBoundary {
    final ReadRulesOutputBoundary presenter;

    public ReadRulesInteractor(ReadRulesOutputBoundary presenter) {
        this.presenter = presenter;

    }

    /**
     * Displays rules based on a fixed string.
     */
    @Override
    public void execute(boolean gameStarted){
        presenter.prepareSuccessView("The rules are as follows:" + "", gameStarted); //type out rules here (in empty string)
    }

}
