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
public class RobotBigCollision implements CollisionListener, ActionListener {

    RobotBig robotBig;
    Body otherBody;
    GameLevel currentlevel;


    public RobotBigCollision(RobotBig r, GameLevel l){
        robotBig = r;
        currentlevel = l;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {

        if (collisionEvent.getOtherBody() instanceof RobotBig) {
            robotBig.jump(1);
        }

        if ((Objects.equals(collisionEvent.getOtherBody().getName(), "leftWall")||
                collisionEvent.getOtherBody() instanceof Key) && (currentlevel instanceof Level1 ||
                currentlevel instanceof Level3)) {
            collisionEvent.getReportingBody().destroy();
        }

        if ((Objects.equals(collisionEvent.getOtherBody().getName(), "leftWall")) &&
                (!(robotBig.isFacingRight()))) {
                    robotBig.setWalkingSpeed(-(robotBig.getWalkingSpeed()));
                    robotBig.removeAllImages();
                    robotBig.addImage(new BodyImage("data/robot.gif", 4f)).flipHorizontal();
                    robotBig.setFacingRight(true);
                    robotBig.stopWalking();
                    robotBig.startWalking(robotBig.getWalkingSpeed());

        }
        if (Objects.equals(collisionEvent.getOtherBody().getName(), "door")) {
            collisionEvent.getReportingBody().destroy();
        }
        else if (collisionEvent.getOtherBody() instanceof DynamicBody &&
                !(collisionEvent.getOtherBody() instanceof Walker)){

            if (!(collisionEvent.getOtherBody() instanceof Key)) {
                collisionEvent.getOtherBody().removeAllImages();
                collisionEvent.getOtherBody().addImage(new BodyImage("data/destroy.gif"));
                Timer t = new Timer(400, this);
                t.setRepeats(false);
                t.start();
                otherBody = collisionEvent.getOtherBody();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        otherBody.destroy();
    }
}

