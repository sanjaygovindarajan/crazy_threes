package view;

import javax.swing.*;

public class PlayThreeView extends JPanel {
    public PlayThreeView() {
        JButton playThreeButton = new JButton();
            playThreeButton.addActionListener(e -> {
                    @@ -30,19 +30,13 @@ public PlayThreeView() {

                        playThreeButton.addActionListener(e -> LoadGameMain.main(new String[]{}));

                        add(playThreeButton);
                    }


                }
    }
}