package interface_adapter.view_rules;

import javax.swing.*;

public class ReadRulesPresenter implements ReadRulesOutputBoundary {
    /**
     * Prints a success message
     * @param message The success message to be printed
     */
    @Override
    public void prepareSuccessView(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void prepareFailView() {
        JOptionPane.showMessageDialog(null, "ERROR: Rules not found");
    }
}
