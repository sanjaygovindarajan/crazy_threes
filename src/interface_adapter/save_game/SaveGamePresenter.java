package interface_adapter.save_game;
import javax.swing.JOptionPane;

public class SaveGamePresenter implements SaveGameOutputBoundary {

    /**
     * Prints a success message
     * @param message The success message
     */
    @Override
    public void prepareSuccessView(String message) {
        JOptionPane.showMessageDialog(null, "Success! " + message);
    }

    /**
     * Prints an error message
     * @param error The error message
     */
    @Override
    public void prepareFailureView(String error) {
        System.out.println("Oh no! " + error);

    }
}
