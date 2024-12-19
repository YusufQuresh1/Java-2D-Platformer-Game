package game;

import javax.swing.*;
import java.awt.*;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class SplashMenuBackground extends JPanel {

    private final Image background;

    public SplashMenuBackground(boolean won) {
        if (won){
            background = new ImageIcon("data/win.png").getImage();
        } else {
            background = new ImageIcon("data/gameover.png").getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 500, 500, null);
    }
}
