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
import java.beans.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TurnView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JButton saveButton;
    private final JButton playButton;
    private final JButton drawButton;
    private final JButton rulesButton;
    private final JTextField gameName;
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        saveButton = new JButton("Save game");
        playButton = new JButton("Play card");
        drawButton = new JButton("Draw card");
        rulesButton = new JButton("View Rules");
        gameName = new JTextField(25);
        JLabel instructions = new JLabel("Give the game a name before saving it.");

        saveButton.setBounds(250, 30, 100, 50);
        playButton.setBounds(250, 50, 100, 50);
        drawButton.setBounds(300, 30, 100, 50);
        rulesButton.setBounds(300, 50, 100, 50);

        saveGameController = saveController;
        playCardController = playController;
        drawCardController = drawController;

        JPanel savePanel = new JPanel();
        savePanel.add(instructions);
        savePanel.add(gameName);
        savePanel.add(saveButton);

        JPanel playPanel = new JPanel();
        playPanel.add(playButton);
        playPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel drawPanel = new JPanel();
        drawPanel.add(drawButton);
        drawPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        saveButton.addActionListener(this);
        playButton.addActionListener(this);
        drawButton.addActionListener(this);

        add(savePanel);
        add(playPanel);
        add(drawPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            saveGameController.execute(gameName.getText());
        } else if(e.getSource() == playButton){
            playCardController.switchView();
        } else if(e.getSource() == drawButton){
            drawCardController.drawCard();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Get rid of the old cards
        for(JButton oldButton : buttonList){
            remove(oldButton);
        }
        this.buttonList = new LinkedList<>();
        //Add new cards
        for(int i = 0;i < viewModel.getCardSuits().size();i++){
            JButton button = new JButton(viewModel.getCardSuits().get(i) + viewModel.getCardNums().get(i));
            button.addActionListener(this);
            buttonList.add(button);
            add(button);
        }
        //Add new discard
        discard.setText(viewModel.getDiscardSuit() + viewModel.getDiscardNum());
    }
}
