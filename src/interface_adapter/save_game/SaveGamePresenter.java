package interface_adapter.save_game;
import javax.swing.JOptionPane;
import java.util.Objects;

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
        if (Objects.equals(error, "Game already exists")) {
            JOptionPane.showMessageDialog(null, "Game already exists! Please choose another name.");

        }
        else { System.out.println("Oh no! " + error);}


    }
}
