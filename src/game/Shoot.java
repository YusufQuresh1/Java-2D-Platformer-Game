package game;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.SoundClip;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class Shoot implements MouseListener {
    private final GameLevel world;
    private final GameView view;

    private static SoundClip blastSound;

    static {
        try {
            blastSound = new SoundClip("data/blastSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Shoot(GameLevel w, GameView v) {
        world = w;
        view = v;
    }

    /**
     * A projectile will fire from the player to where the mouse was clicked
     * The images will rotate based on the direction they are fired
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        Walker blast = new Walker(world, new CircleShape(0.5f));
        blast.setName("blast");
        blast.setPosition(new Vec2(world.getPlayer().getPosition()));

        float mouseX = view.viewToWorld(e.getPoint()).x;
        float mouseY = view.viewToWorld(e.getPoint()).y;
        float playerX = world.getPlayer().getPosition().x;
        float playerY = world.getPlayer().getPosition().y;

        blast.setGravityScale(0);
        blast.setLinearVelocity(new Vec2(-(playerX - mouseX) * 1.5f, -(playerY - mouseY) * 1.5f));
        if (mouseX < playerX) {
            blast.move(new Vec2(-0.5f, 0));
            //blast.removeAllImages();
            blast.addImage(new BodyImage("data/blast.png")).setRotation(
                    (float) ((Math.asin((playerY - mouseY) / (blast.getLinearVelocity().length())))
                            + (Math.toRadians(180))));


        } else if (mouseX > playerX) {
            blast.move(new Vec2(0.5f, 0));
            blast.addImage(new BodyImage("data/blast.png")).setRotation(
                    (float) (-(Math.asin((playerY - mouseY) / (blast.getLinearVelocity().length())))));
        }

        blastSound.play();
        DestroyProjectile dp = new DestroyProjectile(blast, mouseX, playerX);
        blast.addCollisionListener(dp);


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
