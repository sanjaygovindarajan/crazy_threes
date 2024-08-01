package view;

import interface_adapter.start_game.LoadSuccessState;
import interface_adapter.start_game.LoadSuccessViewModel;
import interface_adapter.StartGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartGameView extends JPanel implements PropertyChangeListener {
    public final String viewName = "load success";
    private final StartGameController controller;
    private final LoadSuccessViewModel viewModel;
    private JTextArea playerInfoArea;
    private JTextArea cardInfoArea;
    private JButton playCardButton;
    private JButton drawCardButton;
    private JButton saveGameButton;

    public StartGameView(StartGameController controller, LoadSuccessViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        initializeComponents();
        layoutComponents();
        display(viewModel);

        setVisible(true);

    }

    private void initializeComponents() {
        playerInfoArea = new JTextArea();
        playerInfoArea.setEditable(false);
        playerInfoArea.setBackground(new Color(0, 128, 0));

        cardInfoArea = new JTextArea();
        cardInfoArea.setEditable(false);
        cardInfoArea.setBackground(new Color(0, 128, 0));

        playCardButton = new JButton("Play Card");
        drawCardButton = new JButton("Draw Card");
        saveGameButton = new JButton("Access Rules");

        playCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play card logic
            }
        });

        drawCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Draw card logic
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save game logic
            }
        });
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0, 128, 0));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setBackground(new Color(0, 128, 0));
        infoPanel.add(new JScrollPane(playerInfoArea));
        infoPanel.add(new JScrollPane(cardInfoArea));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setBackground(new Color(0, 128, 0));
        buttonPanel.add(playCardButton);
        buttonPanel.add(drawCardButton);
        buttonPanel.add(saveGameButton);

        setLayout(new BorderLayout());
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void updateView() {
        LoadSuccessState state = viewModel.getState();

        playerInfoArea.setText("It's " + state.getPlayerName() + "'s turn!\n");
        playerInfoArea.append("Their cards:\n");
        for (String card : state.getPlayerCards()) {
            playerInfoArea.append(card + "\n");
        }

        cardInfoArea.setText("The previous card was the " + state.getPreviousCard() + "\n");
        if (state.getCurrentSuit() != null) {
            cardInfoArea.append("However, the suit was changed to " + state.getCurrentSuit() + "\n");
        }
    }

    public void display(LoadSuccessViewModel viewModel) {
        LoadSuccessState data = viewModel.getState();
        playerInfoArea.setText("It's " + data.getPlayerName() + "'s turn!\n");
        playerInfoArea.append("Their cards:\n");
        for (String card : data.getPlayerCards()) {
            playerInfoArea.append(card + "\n");
        }

        cardInfoArea.setText("The previous card was the " + data.getPlayerCards() + "\n");
//        if (data.getPlayerCards().charAt(1) == '3') {
//            String suit = data.getCurrentSuit().replace("S", "spades")
//                    .replace("C", "clubs")
//                    .replace("H", "hearts")
//                    .replace("D", "diamonds");
//            cardInfoArea.append("However, the suit was changed to " + suit + "\n");
//        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateView();
        }
    }

    // add other actions after implemented.
}
