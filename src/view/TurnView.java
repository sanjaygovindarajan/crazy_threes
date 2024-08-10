package view;

import interface_adapter.TurnViewModel;
import interface_adapter.draw_card.DrawCardController;
import interface_adapter.load_game.LoadGameController;
import interface_adapter.play_card.PlayCardController;
import interface_adapter.save_game.SaveGameController;
import interface_adapter.view_rules.ReadRulesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The view when a player has to play their turn
 */
public class TurnView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JButton saveButton;
    private final JButton drawButton;
    private final JButton rulesButton;
    private final JTextField gameName;
    private final JPanel cardsPanel;
    private List<JButton> buttonList;
    private final JLabel discard;
    private final JLabel playerName;
    private final TurnViewModel viewModel;

    private final SaveGameController saveGameController;
    private final PlayCardController playCardController;
    private final DrawCardController drawCardController;
    private final ReadRulesController readRulesController;

    /**
     * Creates this view and initializes the controllers.
     * @param saveController Controller for saving the game
     * @param playController Controller for playing a card
     * @param drawController Controller for drawing a card
     * @param rulesController Controller for viewing the rules
     * @param viewModel The view model for this view
     */
    public TurnView(SaveGameController saveController,
                    PlayCardController playController,
                    DrawCardController drawController,
                    ReadRulesController rulesController,
                    TurnViewModel viewModel){
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        buttonList = new ArrayList<>();
        discard = new JLabel();
        playerName = new JLabel();
        playerName.setFont(new Font("Arial", Font.BOLD, 24));
        saveButton = new JButton("Save game");
        drawButton = new JButton("Draw card");
        rulesButton = new JButton("View Rules");
        gameName = new JTextField(25);
        JLabel instructions = new JLabel("Give the game a name before saving it.");

        saveGameController = saveController;
        playCardController = playController;
        drawCardController = drawController;
        readRulesController = rulesController;


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(drawButton);
        buttonsPanel.add(rulesButton);
        buttonsPanel.add(instructions);
        buttonsPanel.add(gameName);
        buttonsPanel.add(saveButton);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel discardPanel = new JPanel();
        discardPanel.add(playerName);
        discardPanel.add(discard);
        discardPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.cardsPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(cardsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cardsPanel.setPreferredSize(new Dimension(100, 600));
        scroll.setPreferredSize(new Dimension(100, 200));

        saveButton.addActionListener(this);
        drawButton.addActionListener(this);
        rulesButton.addActionListener(this);

        add(discardPanel);
        add(scroll);
        add(buttonsPanel);

    }

    /**
     * Handles buttons being clicked
     * @param e The click event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == saveButton){
            if(!isValidName(gameName.getText())){
                JOptionPane.showMessageDialog(this, "Please enter a valid game name! \n" +
                        "Game name must be nonempty and must not contain the characters ,;/&");
                return;
            }
            saveGameController.execute(gameName.getText());
        } else if(source == drawButton){
            drawCardController.drawCard();
        }  else if(source == rulesButton){
            readRulesController.execute();
        } else if(source instanceof JButton){
            if(buttonList.contains((JButton) source)){
                int index = buttonList.indexOf((JButton) source);
                playCardController.playCard(index);
            }
        }
    }

    /**
     * Monitors the view model for any incoming changes from the presenter.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() == viewModel){
            resetCards();
        }

    }

    /**
     * Reloads the player's cards as well as the discard and the player name based on the view model data.
     */
    public void resetCards(){
        clearCards();
        cardsPanel.setPreferredSize(new Dimension(100, 0));

        for(int i = 0;i < viewModel.getCardSuits().size();i++){
            if(i % 5 == 0){
                cardsPanel.setPreferredSize(new Dimension(100, cardsPanel.getPreferredSize().height + 200));
            }
            JButton button = new JButton(getIcon(viewModel.getCardSuits().get(i), viewModel.getCardNums().get(i)));
            button.addActionListener(this);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            buttonList.add(button);
            cardsPanel.add(button);
        }

        discard.setIcon(getIcon(viewModel.getDiscardSuit(), viewModel.getDiscardNum()));
        playerName.setText("It's " + viewModel.getPlayerName() + "'s turn!     ");
        revalidate();
        repaint();
    }

    /**
     * Gets the image of the card that will be displayed.
     * @param suit The suit of the card
     * @param num The number of the card, as used by the API
     * @return An ImageIcon of the image
     */
    private ImageIcon getIcon(char suit, char num){
        BufferedImage image = resizeImage(APIAccess.getCard(suit, num), 0.5F);
        return new ImageIcon(image);
    }

    /**
     * Removes all the cards currently displayed in the player's hand on the view.
     */
    private void clearCards(){
        for(JButton oldButton : buttonList){
            cardsPanel.remove(oldButton);
        }
        this.buttonList = new LinkedList<>();
    }

    /**
     * Resizes an image based on a scale.
     * Inspired by https://www.baeldung.com/java-resize-image
     * @param originalImage The original image
     * @param scale The factor to scale the image by
     * @return The new BufferedImage
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, float scale){
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Checks if a player name is valid.
     * @param name The player name
     * @return Whether the player name is valid
     */
    private boolean isValidName(String name){
        return !name.isEmpty()
                && !name.contains(",")
                && !name.contains(";")
                && !name.contains("&")
                && !name.contains("/");
    }

    /**
     * Getter method for the save game controller
     * @return The controller for saving the game
     */
    public SaveGameController getSaveGameController() {
        return this.saveGameController;
    }

    /**
     * Getter method for the read rules controller
     * @return The controller for reading the rules
     */
    public ReadRulesController getReadRulesController() {
        return this.readRulesController;
    }

    /**
     * Getter method for the play card controller
     * @return The controller for playing a card
     */
    public PlayCardController getPlayCardController() {
        return this.playCardController;
    }

    /**
     * Getter method for the draw card controller
     * @return The controller for drawing a card
     */
    public DrawCardController getDrawCardController() {
        return this.drawCardController;
    }
}
