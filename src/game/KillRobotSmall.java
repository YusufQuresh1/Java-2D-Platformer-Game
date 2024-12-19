package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class KillRobotSmall implements CollisionListener, ActionListener {

    private final RobotSmall robotSmall;
    GameLevel currentlevel;
    private static SoundClip killSound;

    static {
        try {
            killSound = new SoundClip("data/killSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public KillRobotSmall(RobotSmall z, GameLevel l){
        robotSmall = z;
        currentlevel = l;
    }

    /**
     * depending on the level, there will be different behaviour as a result of collisions, e.g. some levels enemies can change
     * direction but in others they cannot
     * @param collisionEvent
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (Objects.equals(collisionEvent.getOtherBody().getName(), "blast")) {
            collisionEvent.getOtherBody().destroy();
            killSound.play();
            collisionEvent.getReportingBody().removeAllImages();
            collisionEvent.getReportingBody().addImage(new BodyImage("data/destroy.gif"));
            Timer t = new Timer(400, this);
            t.setRepeats(false);
            t.start();

        } else if (currentlevel instanceof Level1 || currentlevel instanceof Level3) {

            if (!(collisionEvent.getOtherBody() instanceof Player) &&
                    !Objects.equals(collisionEvent.getOtherBody().getName(), "ground")) {
                collisionEvent.getReportingBody().removeAllImages();
                collisionEvent.getReportingBody().addImage(new BodyImage("data/destroy.gif"));
                Timer t = new Timer(300, this);
                t.setRepeats(false);
                t.start();
            }

        } else if (currentlevel instanceof Level2) {

                if (collisionEvent.getOtherBody() instanceof RobotSmall) {
                    robotSmall.removeAllImages();
                    if (robotSmall.isFacingRight()) {
                        robotSmall.addImage(new BodyImage("data/small_robot.gif", 3f)).flipHorizontal();
                        robotSmall.setFacingRight(false);
                        robotSmall.setWalkingSpeed(-(robotSmall.getWalkingSpeed()));
                    } else {
                        robotSmall.addImage(new BodyImage("data/small_robot.gif", 3f));
                        robotSmall.setFacingRight(true);
                        robotSmall.setWalkingSpeed(-(robotSmall.getWalkingSpeed()));
                    }
                    robotSmall.stopWalking();
                    robotSmall.startWalking(robotSmall.getWalkingSpeed());
                }

                if ((Objects.equals(collisionEvent.getOtherBody().getName(), "leftWall"))) {
                    robotSmall.removeAllImages();
                    robotSmall.addImage(new BodyImage("data/small_robot.gif", 3f));
                    robotSmall.setFacingRight(true);
                    robotSmall.setWalkingSpeed(-(robotSmall.getWalkingSpeed()));
                    robotSmall.stopWalking();
                    robotSmall.startWalking(robotSmall.getWalkingSpeed());
                }

                if ((Objects.equals(collisionEvent.getOtherBody().getName(), "door")) ||
                collisionEvent.getOtherBody() instanceof Key) {
                    collisionEvent.getReportingBody().destroy();
                }

                if (collisionEvent.getOtherBody() instanceof RobotBig) {
                    collisionEvent.getReportingBody().removeAllImages();
                    collisionEvent.getReportingBody().addImage(new BodyImage("data/destroy.gif"));
                    Timer t = new Timer(400, this);
                    t.setRepeats(false);
                    t.start();
                }
            }
        }



    @Override
    public void actionPerformed(ActionEvent e) {
        robotSmall.destroy();
    }
}

