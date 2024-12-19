package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class DestroyProjectile implements CollisionListener, ActionListener {
    Walker projectile;
    RobotBig robot;
    float mouseX;
    float playerX;

    public DestroyProjectile(Walker p, float mx, float px){
        projectile = p;
        mouseX = mx;
        playerX = px;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if ((collisionEvent.getOtherBody() instanceof StaticBody)) {
            collisionEvent.getReportingBody().destroy(); //projectile will be destroyed if it hits a static body
        }  else if (collisionEvent.getOtherBody() instanceof RobotBig) //dynamic bodies apart from the player
         {
            projectile.destroy();
            robot = (RobotBig) collisionEvent.getOtherBody();
            if (mouseX < playerX) { //if mouse was clicked left of player
                robot.startWalking(-30); //enemy will be knocked back to the left
            } else {
                robot.startWalking(30);//enemy will be knocked back to the right
            }
            Timer t = new Timer(1000,this);
            t.setRepeats(false);
            t.start();


        }  else if (collisionEvent.getOtherBody() instanceof DynamicBody &&
                !(collisionEvent.getOtherBody() instanceof Walker)){
            collisionEvent.getReportingBody().destroy(); //projectile will destroy itself if it hits a dynamic body
                                                        // that is not a walker
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        robot.startWalking(robot.getWalkingSpeed()); //after 1 second the enemy will walk forward again
    }
}


