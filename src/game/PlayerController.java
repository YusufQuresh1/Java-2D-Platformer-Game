package game;

import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class PlayerController implements KeyListener {

    private static final float WALKING_SPEED = 8;
    private Player player;
    public PlayerController(Player player){
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            if (player.isFacingRight()) {
                player.removeAllImages();
                player.addImage(Player.getImage()).flipHorizontal();
                player.setFacingRight(false);
            }
                player.startWalking(-WALKING_SPEED);


        }
        else if (code == KeyEvent.VK_D) {
            if (!player.isFacingRight()) {
                player.removeAllImages();
                player.addImage(Player.getImage());
                player.setFacingRight(true);
            }
                player.startWalking(WALKING_SPEED);


        }
        else if (code == KeyEvent.VK_W){
            player.jump(11);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            player.stopWalking();
            player.setLinearVelocity(new Vec2(0,0));
        } else if (code == KeyEvent.VK_D) {
            player.stopWalking();
            player.setLinearVelocity(new Vec2(0,0));
        }
    }

    public void updatePlayer(Player newPlayer){
        player = newPlayer;
    }
}
