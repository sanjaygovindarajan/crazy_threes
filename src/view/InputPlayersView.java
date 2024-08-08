package view;

import interface_adapter.start_game.StartGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/**
 * View for inputting new players when starting a new game.
 */
public class InputPlayersView extends JPanel implements ActionListener{
    StartGameController controller;
    JButton currentButton;
    JTextField playerName;
    List<JLabel> addedPlayers;
    List<JPanel> addedPlayerRows;
    JButton confirmButton;
    JPanel confirmPanel;

    /**
     * Creates this view based on the controller.
     * @param controller The controller for starting the game
     */
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
        JLabel botLabel = new JLabel("To add a bot, begin the player name with '#'");
        JPanel botPanel = new JPanel();
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        botLabel.setFont(new Font("Arial", Font.BOLD, 14));
        botPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.add(welcomeLabel);
        botPanel.add(botLabel);
        add(welcomePanel);
        add(botPanel);
        add(currentPanel);
        addedPlayers = new ArrayList<>();
        addedPlayerRows = new ArrayList<>();
        addedPlayerRows.add(currentPanel);
    }

    /**
     * Handle add new player button and start game button
     * @param e the click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmButton){
            List<String> playerNames = new ArrayList<>();
            for(JLabel label : addedPlayers){
                playerNames.add(label.getText());
            }
            controller.execute(playerNames);
        } else if(e.getSource() == currentButton){
            addPlayer();
        }
    }

    /**
     * Helper method for adding a new player based on the input field.
     * Breaks up larger actionPerformed() method.
     */
    private void addPlayer(){
        if(!isValidName(playerName.getText())){
            JOptionPane.showMessageDialog(this, "Please enter a valid player name! \n" +
                    "Player name must be nonempty and must not contain the characters ,;/&");
            return;
        }
        if(addedPlayers.isEmpty() && playerName.getText().charAt(0) == '#'){
            JOptionPane.showMessageDialog(this, "The first player cannot be a bot!");
            return;
        }
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
        if(addedPlayers.size() < 5) {
            add(newPanel);
        }
        add(confirmPanel);
        playerName.setText("");
        revalidate();
        repaint();
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
     * Resets the list of players.
     */
    public void clearPlayers(){
        addedPlayers.clear();
        for(JPanel panel: addedPlayerRows){
            remove(panel);
        }
        JPanel currentPanel = addedPlayerRows.getLast();
        addedPlayerRows.clear();
        addedPlayerRows.add(currentPanel);
        add(currentPanel);
        revalidate();
        repaint();
    }
}
