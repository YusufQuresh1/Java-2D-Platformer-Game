package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public abstract class GameLevel extends World {

    private boolean doorUnlocked = false;
    private final Player player;
    private final Game game;
    private SoundClip gameMusic;
    private final StaticBody door;
    private final Key key;
    private StaticBody rightWall1;

    public Key getKey() {
        return key;
    }

    public Game getGame() {
        return game;
    }

    private static SoundClip robotSound;

    public SoundClip getRobotSound() {
        return robotSound;
    }

    static {
        try {
            robotSound = new SoundClip("data/robotSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public GameLevel(Game g) {

        game = g;

        //ground
        Shape shape = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -12f));
        ground.setName("ground");
        ground.addImage(new BodyImage("data/ground.png"));

        //left wall
        Shape wallShape = new BoxShape(0.5f, 25);
        StaticBody leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-13f, -12f));
        leftWall.setName("leftWall");

        //right wall
        rightWall1 = new StaticBody(this, wallShape);
        rightWall1.setPosition(new Vec2(14.5f, 18f));
        rightWall1.setName("rightWall");
        StaticBody rightWall2 = new StaticBody(this, wallShape);
        rightWall2.setPosition(new Vec2(19f, -12));


        //door
        Shape doorShape = new BoxShape(1, 2f);;
        door = new StaticBody(this, doorShape);
        door.setName("door");
        door.addImage(new BodyImage("data/door.png", 4));

        //adds the key
        key = new Key(this);

        //makes the player character
        player = new Player(this);
        player.setName("player");

        //allows for player to pick up items
        PlayerCollision pu = new PlayerCollision(player, this);
        player.addCollisionListener(pu);

        //allows player to be killed
        KillPlayer kp = new KillPlayer(player, this);
        player.addCollisionListener(kp);




    }

    public Player getPlayer() {
        return player;
    }

    public StaticBody getDoor() {
        return door;
    }

    public abstract boolean isComplete();

    public boolean isDoorUnlocked() {
        return doorUnlocked;
    }

    public void setDoorUnlocked(boolean doorUnlocked) {
        this.doorUnlocked = doorUnlocked;
    }

    public SoundClip getGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(SoundClip gameMusic) {
        this.gameMusic = gameMusic;
    }

    public StaticBody getRightWall1() {
        return rightWall1;
    }
}
