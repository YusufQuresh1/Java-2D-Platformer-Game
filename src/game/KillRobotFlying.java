package game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class KillRobotFlying implements CollisionListener, ActionListener {

    RobotFlying robotFlying;
    GameLevel currentlevel;


    public KillRobotFlying(RobotFlying r, GameLevel l){
        robotFlying = r;
        currentlevel = l;
    }

    /**
     * In the event of a collision between the Flying Robot and another body, a variety of consequences will occur.
     * If it collides with the edges of the screen it will change direction and fly in the opposite direction.
     * If the player shoots it whilst flying it will drop to the ground and walk instead. If shot another time it will die.
     * @param collisionEvent
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {

        if (collisionEvent.getOtherBody() instanceof RobotBig) {
            robotFlying.jump(2);
        }

        if ((Objects.equals(collisionEvent.getOtherBody().getName(), "leftWall"))) {
            robotFlying.setFlyingSpeed(-(robotFlying.getFlyingSpeed()));
            robotFlying.removeAllImages();
            robotFlying.addImage(new BodyImage("data/robotFlying.gif", 4f)).flipHorizontal();
            robotFlying.setFacingRight(true);
            robotFlying.stopWalking();
            robotFlying.startWalking(robotFlying.getFlyingSpeed());

        } else if ((Objects.equals(collisionEvent.getOtherBody().getName(), "rightWall"))) {
            robotFlying.setFlyingSpeed(-(robotFlying.getFlyingSpeed()));
            robotFlying.removeAllImages();
            robotFlying.addImage(new BodyImage("data/robotFlying.gif", 4f));
            robotFlying.setFacingRight(false);
            robotFlying.stopWalking();
            robotFlying.startWalking(robotFlying.getFlyingSpeed());
        }
        if (Objects.equals(collisionEvent.getOtherBody().getName(), "blast")) {
            collisionEvent.getOtherBody().destroy();
            if (robotFlying.getGravityScale() == 0) {
                robotFlying.setGravityScale(3);
            } else {
                collisionEvent.getReportingBody().removeAllImages();
                collisionEvent.getReportingBody().addImage(new BodyImage("data/destroy.gif"));
                Timer t = new Timer(400, this);
                t.setRepeats(false);
                t.start();
            }
        }

        if (collisionEvent.getOtherBody() instanceof DynamicBody &&
                !(collisionEvent.getOtherBody() instanceof Walker) &&
                !(collisionEvent.getOtherBody() instanceof Key)){
            collisionEvent.getOtherBody().destroy();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        robotFlying.destroy();

    }
}

