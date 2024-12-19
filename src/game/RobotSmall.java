package game;

import city.cs.engine.*;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class RobotSmall extends Walker {
//1.26f,-1.93f, -1.28f,-1.9f, -1.03f,1.32f, -0.32f,1.99f, 1.04f,1.97f, 1.28f,-1.86f
    private final static Shape robotSmallShape = new PolygonShape(
            0.63f,-0.965f, -0.64f,-0.95f, -0.515f, 0.66f, -0.16f,0.995f, 0.52f,0.985f, 0.64f,-0.93f);

    private final static BodyImage image = new BodyImage("data/small_robot.gif", 3);
    private boolean facingRight = false;
    private float walkingSpeed = -2;

    public RobotSmall(World world) {
        super(world, robotSmallShape);
        this.addImage(image).flipHorizontal();
    }
    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setWalkingSpeed(float walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }
}
