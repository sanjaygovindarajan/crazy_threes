package view;

import interface_adapter.start_game.StartGameController;
import use_case.game_actions.start_game.StartGameInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class InputPlayersView extends JPanel implements ActionListener, PropertyChangeListener {
    StartGameController controller;
    JButton currentButton;
    JTextField playerName;
    List<JLabel> addedPlayers;
    List<JPanel> addedPlayerRows;
    JButton confirmButton;
    JPanel confirmPanel;
    public InputPlayersView(StartGameController controller){
        this.controller = controller;
        this.setLayout(new GridLayout(24, 2));
        this.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        playerName = new JTextField(25);
        currentButton = new JButton("Add player");
        confirmButton = new JButton("Start game");
        currentButton.addActionListener(this);
        confirmButton.addActionListener(this);
        confirmPanel = new JPanel();
        confirmPanel.add(confirmButton);
        JPanel currentPanel = new JPanel();
        currentPanel.add(playerName);
        currentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        currentPanel.add(currentButton);
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Add up to five players to start the game!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.add(welcomeLabel);
        add(welcomePanel);
        add(currentPanel);
        addedPlayers = new ArrayList<>();
        addedPlayerRows = new ArrayList<>();
        addedPlayerRows.add(currentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmButton){
            List<String> playerNames = new ArrayList<>();
            for(JLabel label : addedPlayers){
                playerNames.add(label.getText());
            }
            controller.execute(playerNames);
        } else if(e.getSource() == currentButton){
            JLabel newPlayer = new JLabel(playerName.getText());
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel oldPanel = addedPlayerRows.getLast();
            oldPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            oldPanel.remove(playerName);
            oldPanel.remove(currentButton);
            oldPanel.add(newPlayer);
            JLabel filler = new JLabel();
            filler.setPreferredSize(new Dimension(120, (int) filler.getPreferredSize().getHeight()));
            oldPanel.add(filler);
            newPanel.add(playerName);
            newPanel.add(currentButton);
            addedPlayers.add(newPlayer);
            addedPlayerRows.add(newPanel);
            remove(confirmPanel);
            add(newPanel);
            add(confirmPanel);
            playerName.setText("");
            revalidate();
            repaint();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
