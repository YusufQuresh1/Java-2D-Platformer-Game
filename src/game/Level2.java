package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

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
public class Level2 extends GameLevel implements ActionListener {


    public Level2(Game game){
        super(game);
        //JFrame debugView = new DebugViewer(this, 500, 500);

        try {
            this.setGameMusic(new SoundClip("data/backgroundMusic2.wav"));
            this.getGameMusic().loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        this.getRightWall1().setPosition(new Vec2(15.5f, -12f));

        //suspended platforms

        //top
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(1.5f, 6.5f));
        platform1.addImage(new BodyImage("data/metal.png"));

        StaticBody platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(-4f, 6.5f));
        platform2.addImage(new BodyImage("data/metal.png"));

        //middle
        StaticBody platform4 = new StaticBody(this, platformShape);
        platform4.setPosition(new Vec2(-10f, 1));
        platform4.addImage(new BodyImage("data/metal.png"));

        StaticBody platform5 = new StaticBody(this, platformShape);
        platform5.setPosition(new Vec2(-5f, 1));
        platform5.addImage(new BodyImage("data/metal.png"));

        //bottom
        StaticBody platform6 = new StaticBody(this, platformShape);
        platform6.setPosition(new Vec2(1f, -5));
        platform6.addImage(new BodyImage("data/metal.png"));

        StaticBody platform7 = new StaticBody(this, platformShape);
        platform7.setPosition(new Vec2(-5f, -5));
        platform7.addImage(new BodyImage("data/metal.png"));

        // house for key
        StaticBody wall = new StaticBody(this, new BoxShape(0.5f, 3));
        wall.setPosition(new Vec2(5, 4));
        wall.addImage(new BodyImage("data/metal.png")).setRotation(1.57f);

        StaticBody platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(12f, -1));
        platform3.addImage(new BodyImage("data/metal.png"));

        StaticBody circle = new StaticBody(this, new CircleShape(1));
        circle.setPosition(new Vec2(9, 4));
        circle.addImage(new BodyImage("data/ring.png", 2));

        DynamicBody wood = new DynamicBody(this, platformShape);
        wood.setPosition(new Vec2(9, 6));
        wood.addImage(new BodyImage("data/wood.png"));

        //coins

        new Coins(this).setPosition(new Vec2(-12,-10f));
        new Coins(this).setPosition(new Vec2(-10,-10f));

        new Coins(this).setPosition(new Vec2(12,12f));
        new Coins(this).setPosition(new Vec2(10,12f));
        new Coins(this).setPosition(new Vec2(8,12f));

        new Coins(this).setPosition(new Vec2(-3,0f));
        new Coins(this).setPosition(new Vec2(-5,0f));
        new Coins(this).setPosition(new Vec2(-7,0f));

        new Coins(this).setPosition(new Vec2(-3,-6f));
        new Coins(this).setPosition(new Vec2(-5,-6f));
        new Coins(this).setPosition(new Vec2(-7,-6f));

        new Coins(this).setPosition(new Vec2(10,-2));
        new Coins(this).setPosition(new Vec2(12,-2));




        this.getPlayer().setPosition(new Vec2(-6f, 7f));
        this.getDoor().setPosition(new Vec2(11, -9.5f));
        this.getKey().setPosition(new Vec2(9, 0));
        //this.getKey().setGravityScale(0);



        Timer t = new Timer(8000,this);
        t.setInitialDelay(0);
        t.start();

    }

    @Override
    public boolean isComplete() {
        return this.isDoorUnlocked();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RobotBig robotBig = new RobotBig(this);
        robotBig.setPosition(new Vec2(3,16));
        robotBig.startWalking(-2f);
        this.getRobotSound().loop();
        RobotBigCollision rc = new RobotBigCollision(robotBig, this);
        robotBig.addCollisionListener(rc);

        if (!(getPlayer().getPosition().x >= -4 && getPlayer().getPosition().x <= -2))  {
            if (!(getPlayer().getPosition().y <= 4 && getPlayer().getPosition().y >= 2)) {
                RobotSmall robotSmall = new RobotSmall(this);
                robotSmall.setPosition(new Vec2(-3, 3));
                robotSmall.startWalking(-2.75f);
                KillRobotSmall kz = new KillRobotSmall(robotSmall, this);
                robotSmall.addCollisionListener(kz);
            }
        }
        if (!(getPlayer().getPosition().x >= 1 && getPlayer().getPosition().x <= 3))  {
            if (!(getPlayer().getPosition().y <= -3 && getPlayer().getPosition().y >= -5)) {
                RobotSmall robotSmall2 = new RobotSmall(this);
                robotSmall2.setPosition(new Vec2(2, -4));
                robotSmall2.startWalking(-2.75f);
                KillRobotSmall kz2 = new KillRobotSmall(robotSmall2, this);
                robotSmall2.addCollisionListener(kz2);
            }
        }

    }


}

