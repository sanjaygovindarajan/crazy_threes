package use_case.game_actions.read_rules;

import data_access.RulesDataAccess;
import interface_adapter.view_rules.ReadRulesOutputBoundary;

import java.io.IOException;

public class ReadRulesInteractor implements ReadRulesInputBoundary {
    final ReadRulesOutputBoundary presenter;

    public ReadRulesInteractor(ReadRulesOutputBoundary presenter) {
        this.presenter = presenter;

    }

    /**
     * Displays rules based on a fixed string.
     */
    @Override
    public void execute() {
        String rules;
        RulesDataAccess rulesDataAccess = new RulesDataAccess();
        try {
            rules = rulesDataAccess.scanRules();
            presenter.prepareSuccessView(String.valueOf(rules));
        } catch(IOException e){
            presenter.prepareFailView();
        }
    }

}
