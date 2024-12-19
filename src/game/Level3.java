package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class Level3 extends GameLevel implements ActionListener {

    private RobotFlying robotFlying;
    public Level3(Game game) {
        super(game);

        //JFrame debugView = new DebugViewer(this, 500, 500);
        try {
            this.setGameMusic(new SoundClip("data/backgroundMusic3.wav"));
            this.getGameMusic().loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        //suspended platforms

        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(8, -7f));
        platform1.addImage(new BodyImage("data/metal.png"));

        StaticBody platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(1, -1));
        platform2.addImage(new BodyImage("data/metal.png"));

        StaticBody platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(-10, -6));
        platform3.addImage(new BodyImage("data/metal.png"));
        StaticBody platform4 = new StaticBody(this, platformShape);
        platform4.setPosition(new Vec2(-6, -6));
        platform4.addImage(new BodyImage("data/metal.png"));

        StaticBody platform5 = new StaticBody(this, platformShape);
        platform5.setPosition(new Vec2(-8, 7.5f));
        platform5.addImage(new BodyImage("data/metal.png"));

        StaticBody platform6 = new StaticBody(this, platformShape);
        platform6.setPosition(new Vec2(10, 7.5f));
        platform6.addImage(new BodyImage("data/metal.png"));


        // house for key
        StaticBody wall1 = new StaticBody(this, new BoxShape(0.5f, 3));
        wall1.setPosition(new Vec2(-12, -1));
        wall1.addImage(new BodyImage("data/metal.png")).setRotation(1.57f);

        StaticBody wall2 = new StaticBody(this, new BoxShape(0.5f, 3));
        wall2.setPosition(new Vec2(-4, -1));
        wall2.addImage(new BodyImage("data/metal.png")).setRotation(1.57f);

        StaticBody circle = new StaticBody(this, new CircleShape(0.5f));
        circle.setPosition(new Vec2(-8, -4));
        circle.addImage(new BodyImage("data/ring.png", 1));

        StaticBody circle2 = new StaticBody(this, new CircleShape(0.5f));
        circle2.setPosition(new Vec2(-8, 0));
        circle2.addImage(new BodyImage("data/ring.png", 1));

        DynamicBody wood2 = new DynamicBody(this, platformShape);
        wood2.setPosition(new Vec2(-8, 1));
        wood2.addImage(new BodyImage("data/wood.png"));


        //coins
        new Coins(this).setPosition(new Vec2(-10,-7.25f));
        new Coins(this).setPosition(new Vec2(-6,-7.25f));
        new Coins(this).setPosition(new Vec2(-4,-7.25f));

        new Coins(this).setPosition(new Vec2(10,-5f));
        new Coins(this).setPosition(new Vec2(8,-5f));


        new Coins(this).setPosition(new Vec2(-1,1));
        new Coins(this).setPosition(new Vec2(1,1));
        new Coins(this).setPosition(new Vec2(3,1));

        new Coins(this).setPosition(new Vec2(-8,9.5f));
        new Coins(this).setPosition(new Vec2(-10,9.5f));



        this.getDoor().setPosition(new Vec2(11, 10f));
        this.getKey().setPosition(new Vec2(-8, -3));
        this.getPlayer().setPosition(new Vec2(-10, -10));

        robotFlying = new RobotFlying(this);
        robotFlying.setPosition(new Vec2(0, 4));
        KillRobotFlying krf = new KillRobotFlying(robotFlying, this);
        robotFlying.addCollisionListener(krf);
        robotFlying.startWalking(robotFlying.getFlyingSpeed());

        Timer t = new Timer(9000, this);
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
        robotBig.setPosition(new Vec2(17,-11));
        robotBig.startWalking(-2);
        this.getRobotSound().loop();
        RobotBigCollision rc = new RobotBigCollision(robotBig, this);
        robotBig.addCollisionListener(rc);

        RobotSmall robotSmall = new RobotSmall(this);
        robotSmall.setPosition(new Vec2(11, -11));
        robotSmall.startWalking(-3f);
        KillRobotSmall kz = new KillRobotSmall(robotSmall, this);
        robotSmall.addCollisionListener(kz);

        RobotSmall robotSmall2 = new RobotSmall(this);
        robotSmall2.setPosition(new Vec2(14, -11));
        robotSmall2.startWalking(-3f);
        KillRobotSmall kz2 = new KillRobotSmall(robotSmall2, this);
        robotSmall2.addCollisionListener(kz2);




    }


}

