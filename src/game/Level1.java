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
public class Level1 extends GameLevel implements ActionListener {

    public Level1(Game game){
        super(game);

        //JFrame debugView = new DebugViewer(this, 500, 500);
        try {
            this.setGameMusic(new SoundClip("data/background music.wav"));
            this.getGameMusic().loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        //suspended platforms
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, -4f));
        platform1.addImage(new BodyImage("data/metal.png"));

        StaticBody platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(8, -6.5f));
        platform2.addImage(new BodyImage("data/metal.png"));

        StaticBody platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(6f, 2.5f));
        platform3.addImage(new BodyImage("data/metal.png"));

        StaticBody platform4 = new StaticBody(this, platformShape);
        platform4.setPosition(new Vec2(10, 7f));
        platform4.addImage(new BodyImage("data/metal.png"));

        // house for key
        StaticBody wall3 = new StaticBody(this, new BoxShape(0.5f, 3));
        wall3.setPosition(new Vec2(-12, 11));
        wall3.addImage(new BodyImage("data/metal.png")).setRotation(1.57f);

        StaticBody wall4 = new StaticBody(this, new BoxShape(0.5f, 1));
        wall4.setPosition(new Vec2(-4, 9));
        wall4.addImage(new BodyImage("data/metalwall.png")).setRotation(1.57f);

        DynamicBody wall5 = new DynamicBody(this, new BoxShape(0.5f, 2));
        wall5.setPosition(new Vec2(-4, 12));
        wall5.addImage(new BodyImage("data/halfwood.png")).setRotation(1.57f);

        StaticBody circle = new StaticBody(this, new CircleShape(1));
        circle.setPosition(new Vec2(-8, 7));
        circle.addImage(new BodyImage("data/ring.png", 2));

        DynamicBody platform5 = new DynamicBody(this, platformShape);
        platform5.setPosition(new Vec2(-8, 8));
        platform5.addImage(new BodyImage("data/wood.png"));

        //coins
        new Coins(this).setPosition(new Vec2(10,-7.25f));
        new Coins(this).setPosition(new Vec2(8,-7.25f));
        new Coins(this).setPosition(new Vec2(6,-7.25f));

        new Coins(this).setPosition(new Vec2(10,-4.75f));
        new Coins(this).setPosition(new Vec2(8,-4.75f));

        new Coins(this).setPosition(new Vec2(8,4.25f));
        new Coins(this).setPosition(new Vec2(6,4.25f));

        new Coins(this).setPosition(new Vec2(-6,-2.25f));
        new Coins(this).setPosition(new Vec2(-8,-2.25f));

        new Coins(this).setPosition(new Vec2(8.5f,8.75f));

        this.getDoor().setPosition(new Vec2(11, 9.5f));
        this.getKey().setPosition(new Vec2(-8, 9));
        this.getPlayer().setPosition(new Vec2(-10, -10));



        Timer t = new Timer(8500,this);
        t.setInitialDelay(1000);
        t.start();

    }

    /**
     * Overrides the isComplete method inherited from the GameLevel class
     * @return whether the door is unlocked which in turn would mean the level has been completed
     */
  @Override
    public boolean isComplete() {
       return this.isDoorUnlocked();
   }

    /**
     * Actions that occur based off the timer created. Different enemies spawning at different rates and positions
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        RobotBig robotBig = new RobotBig(this);
        robotBig.setPosition(new Vec2(3,16));
        robotBig.startWalking(-1.75f);
        this.getRobotSound().loop();
        RobotBigCollision rc = new RobotBigCollision(robotBig, this);
        robotBig.addCollisionListener(rc);

        RobotSmall robotSmall = new RobotSmall(this);
        robotSmall.setPosition(new Vec2(14, -11));
        robotSmall.startWalking(-2);
        KillRobotSmall kz = new KillRobotSmall(robotSmall, this);
        robotSmall.addCollisionListener(kz);

        RobotSmall robotSmall2 = new RobotSmall(this);
        robotSmall2.setPosition(new Vec2(17, -11));
        robotSmall2.startWalking(-2);
        KillRobotSmall kz2 = new KillRobotSmall(robotSmall2, this);
        robotSmall2.addCollisionListener(kz2);


    }


}

