package game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class RobotFlying extends Walker implements ActionListener {

    private final static Shape robotShape = new BoxShape(1.25f, 1.5f);
    private final static BodyImage image = new BodyImage("data/robotFlying.gif", 4f);
    private float flyingSpeed = -2.5f;

    private boolean facingRight = false;



    public RobotFlying(World world) {
        super(world, robotShape);
        this.addImage(image);
        this.setGravityScale(0);

        Timer t = new Timer(2000,this);
        t.setInitialDelay(2000);
        t.start();
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
