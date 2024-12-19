package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class RobotBig extends Walker {

    private final static Shape robotShape = new BoxShape(1.25f, 1.5f);
    private final static BodyImage image = new BodyImage("data/robot.gif", 4f);
    private float walkingSpeed = -1.75f;

    private boolean facingRight = false;



    public RobotBig(World world) {
        super(world, robotShape);
        this.addImage(image);
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setWalkingSpeed(float walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }
}
