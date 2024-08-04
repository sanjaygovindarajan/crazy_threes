package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the view for inputting player names with up to a maximum of 5 names.
 */
public class NameInputView extends JPanel implements ActionListener {
    private final NameInputViewModel nameInputViewModel;
    private JTextField[] nameFields;
    private JButton submitButton;
    private JComboBox<Integer> playerCountSelector;

    /**
     * Constructs a NameInputView screen with fields for up to 5 names and a submit button.
     * @param nameInputViewModel the view model managing the name input logic
     */
    public NameInputView(NameInputViewModel nameInputViewModel) {
        this.nameInputViewModel = nameInputViewModel;
        setLayout(new GridLayout(7, 1));  // Increased rows to accommodate the player count selector

        // Player count selector
        JLabel playerCountLabel = new JLabel("Select number of players:", SwingConstants.CENTER);
        playerCountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(playerCountLabel);

        Integer[] playerCounts = {1, 2, 3, 4, 5};
        playerCountSelector = new JComboBox<>(playerCounts);
        playerCountSelector.setFont(new Font("Arial", Font.PLAIN, 18));
        playerCountSelector.addActionListener(e -> updateNameFields((int) playerCountSelector.getSelectedItem()));
        add(playerCountSelector);

        // Initialize name fields
        nameFields = new JTextField[5];
        for (int i = 0; i < nameFields.length; i++) {
            JLabel nameLabel = new JLabel("Enter name " + (i + 1) + ":", SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            add(nameLabel);

            nameFields[i] = new JTextField();
            add(nameFields[i]);
        }

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(submitButton);
        submitButton.addActionListener(this);

        // Initial update
        updateNameFields((int) playerCountSelector.getSelectedItem());
    }

    /**
     * Updates the visibility of name fields based on the number of players selected.
     * @param playerCount The number of players selected.
     */
    private void updateNameFields(int playerCount) {
        for (int i = 0; i < nameFields.length; i++) {
            nameFields[i].setVisible(i < playerCount);
            ((JLabel) getComponent(2 + i * 2)).setVisible(i < playerCount);
        }
    }

    /**
     * Handles "submit" button being clicked.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            int playerCount = (int) playerCountSelector.getSelectedItem();
            String[] names = new String[playerCount];
            boolean allNamesValid = true;

            for (int i = 0; i < playerCount; i++) {
                String name = nameFields[i].getText().trim();
                if (name.isEmpty()) {
                    allNamesValid = false;
                    JOptionPane.showMessageDialog(this, "Name " + (i + 1) + " cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                names[i] = name;
            }

            if (allNamesValid) {
                nameInputViewModel.setPlayerNames(names);
            }
        }
    }
}
