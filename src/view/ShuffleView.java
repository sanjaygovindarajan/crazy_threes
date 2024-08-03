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
                "Looks like you played a three! Pick a new suit",
                "Three played",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Shuffle Deck"},
                "Shuffle Deck");
        controller.execute();
    }
}
