package view;
import interface_adapter.shuffle.ShuffleController;

import javax.swing.*;

public class ShuffleView{
    private final ShuffleController controller;
    public ShuffleView(ShuffleController controller){
        this.controller = controller;
    }

    public void displayShuffleView(){
        JOptionPane.showOptionDialog(null,
                "The discard pile is out of cards! Please shuffle the deck.",
                "Shuffle Deck",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Shuffle Deck"},
                "Shuffle Deck");
        controller.execute();
    }
}
