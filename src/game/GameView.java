package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class GameView extends UserView {

    private GameLevel w;

    public void setLevel(GameLevel w) {
        this.w = w;
    }

    //images for overlay statistics
    private final Image background = new ImageIcon("data/background2.gif").getImage();
    private final Image background2 = new ImageIcon("data/background.gif").getImage();
    private final Image background3 = new ImageIcon("data/background3.gif").getImage();
    private final Image gameOverImage = new ImageIcon("data/gameover.gif").getImage();
    private final Image keyCollected = new ImageIcon("data/key tick.png").getImage();
    private final Image keyNotCollected = new ImageIcon("data/key x.png").getImage();
    private final Image health4 = new ImageIcon("data/health4.png").getImage();
    private final Image health3 = new ImageIcon("data/health3.png").getImage();
    private final Image health2 = new ImageIcon("data/health2.png").getImage();
    private final Image health1 = new ImageIcon("data/health1.png").getImage();
    private final Image health0 = new ImageIcon("data/health0.png").getImage();
    private final Image coin = new ImageIcon("data/coin.png").getImage();
    public GameView(GameLevel world){
        super(world, 500, 500);
        w = world;
    }

    @Override
    protected void paintBackground(Graphics2D g){
        if (w instanceof Level1) {
            g.drawImage(background, 0, 0, 500, 500, this); //displays background image
        } else if (w instanceof Level2) {
            g.drawImage(background2, 0, 0, 500, 500, this); //displays background image
        } else if (w instanceof Level3) {
            g.drawImage(background3, 0, 0, 500, 500, this);
        }
    }
    protected void paintForeground(Graphics2D g) {

        g.drawImage(coin, 2, 480, 20, 20, this);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(w.getPlayer().getCoins()), 25, 495);

        if (w.getPlayer().isDead()) {
            g.drawImage(gameOverImage, -1, 80, 500, 500, this);
            w.stop();
        }

        if (w instanceof Level1){
            if (w.getPlayer().isKeyCollected()) {
                g.drawImage(keyCollected, 445, 2, 44, 18, this);
            } else {
                g.drawImage(keyNotCollected, 445, 2, 44, 18, this);
            }
        } else if (w instanceof Level2) {
            if (w.getPlayer().isKeyCollected()) {
                g.drawImage(keyCollected, 445, 380, 44, 18, this);
            } else {
                g.drawImage(keyNotCollected, 445, 380, 44, 18, this);
            }
        } else if (w instanceof Level3) {
            if (w.getPlayer().isKeyCollected()) {
                g.drawImage(keyCollected, 445, 0, 44, 18, this);
            } else {
                g.drawImage(keyNotCollected, 445, 0, 44, 18, this);
            }
        }

        if (w.getPlayer().getHealth() == 4) {
            g.drawImage(health4, 2, 5, 132, 27, this);
        } else if (w.getPlayer().getHealth() == 3) {
            g.drawImage(health3, 2, 5, 132, 27, this);
        } else if (w.getPlayer().getHealth() == 2) {
            g.drawImage(health2, 2, 5, 132, 32,this);
        } else if (w.getPlayer().getHealth() == 1) {
            g.drawImage(health1, 2, 5, 132, 27, this);
        } else {
            g.drawImage(health0, 2, 5, 132, 27, this);
        }
    }
}
