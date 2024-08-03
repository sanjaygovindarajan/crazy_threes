package view;

import interface_adapter.TurnViewModel;
import interface_adapter.draw_card.DrawCardController;
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

public class TurnView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JButton saveButton;
    private final JButton drawButton;
    private final JButton rulesButton;
    private final JTextField gameName;
    private final JPanel cardsPanel;
    private List<JButton> buttonList;
    private JLabel discard;
    private final TurnViewModel viewModel;


    private SaveGameController saveGameController;
    private PlayCardController playCardController;
    private DrawCardController drawCardController;
    private ReadRulesController readRulesController;

    public TurnView(SaveGameController saveController,
                    PlayCardController playController,
                    DrawCardController drawController,
                    TurnViewModel viewModel){
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        buttonList = new ArrayList<>();
        discard = new JLabel();
        saveButton = new JButton("Save game");
        drawButton = new JButton("Draw card");
        rulesButton = new JButton("View Rules");
        gameName = new JTextField(25);
        JLabel instructions = new JLabel("Give the game a name before saving it.");

        saveGameController = saveController;
        playCardController = playController;
        drawCardController = drawController;

        JPanel savePanel = new JPanel();
        savePanel.add(instructions);
        savePanel.add(gameName);
        savePanel.add(saveButton);


        JPanel drawPanel = new JPanel();
        drawPanel.add(drawButton);
        drawPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel discardPanel = new JPanel();
        discardPanel.add(discard);
        discardPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        saveButton.addActionListener(this);
        drawButton.addActionListener(this);

        add(savePanel);
        add(discardPanel);
        add(cardsPanel);
        add(drawPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == saveButton){
            saveGameController.execute(gameName.getText());
        } else if(source == drawButton){
            drawCardController.drawCard();
        } else if(source instanceof JButton){
            if(buttonList.contains((JButton) source)){
                int index = buttonList.indexOf((JButton) source);
                playCardController.playCard(index);
            }
        };
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() == viewModel){
            resetCards();
        }

    }

    public void resetCards(){
        clearCards();

        for(int i = 0;i < viewModel.getCardSuits().size();i++){
            JButton button = new JButton(getIcon(viewModel.getCardSuits().get(i), viewModel.getCardNums().get(i)));
            button.addActionListener(this);
            buttonList.add(button);
            cardsPanel.add(button);
        }

        discard.setIcon(getIcon(viewModel.getDiscardSuit(), viewModel.getDiscardNum()));
        revalidate();
        repaint();
    }

    private ImageIcon getIcon(char suit, char num){
        BufferedImage image = resizeImage(APIAccess.getCard(suit, num), 0.5F);
        return new ImageIcon(image);
    }

    private void clearCards(){
        for(JButton oldButton : buttonList){
            cardsPanel.remove(oldButton);
        }
        this.buttonList = new LinkedList<>();
    }

    /**
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
}
