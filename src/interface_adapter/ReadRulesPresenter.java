package interface_adapter;

public class ReadRulesPresenter implements ReadRulesOutputBoundary{
    /**
     * Prints a success message
     * @param message The success message to be printed
     */
    @Override
    public void prepareSuccessView(String message) {
        System.out.println("Success! " + message);
    }
}
