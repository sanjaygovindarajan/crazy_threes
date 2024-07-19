package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    public GamePanel() {
        setLayout(new GridLayout(5, 1));


        JLabel titleLabel = new JLabel("Crazy Threes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton saveGameButton = new JButton("Save Game");
        JButton accessRulesButton = new JButton("Access Rules");

        newGameButton.addActionListener(e -> {
            //update this line after the view of NewGame is implemented
            JOptionPane.showMessageDialog(null, "Start a new game!");
        });

        loadGameButton.addActionListener(e -> LoadGameMain.main(new String[]{}));

        saveGameButton.addActionListener(e -> {
             //update this line after the view of SaveGame is implemented
            JOptionPane.showMessageDialog(null, "Game saved!");
        });

        accessRulesButton.addActionListener(e -> {
            //update this line after the view of AccessRules is implemented
            JOptionPane.showMessageDialog(null, "Accessing rules...");
        });

        add(newGameButton);
        add(loadGameButton);
        add(saveGameButton);
        add(accessRulesButton);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Crazy Threes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        frame.getContentPane().add(panel);

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
