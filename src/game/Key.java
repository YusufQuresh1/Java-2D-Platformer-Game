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
public class Key extends DynamicBody {

    private final static Shape keyShape = new BoxShape(1, 0.5f);
    private final static BodyImage image = new BodyImage("data/key.png", 3);

    private static SoundClip keySound;

    static {
        try {
            keySound = new SoundClip("data/keySound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Key(World world) {
        super(world, keyShape);
        this.addImage(image).setRotation(90);
    }

    @Override
    public void destroy()
    {
        keySound.play();
        super.destroy();
    }
}
