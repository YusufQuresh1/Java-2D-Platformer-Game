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
public class Coins extends StaticBody {

    private final static Shape coinShape = new BoxShape(1, 1);
    private final static BodyImage image = new BodyImage("data/coin.gif", 2);

    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/coinSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Coins(World world) {
        super(world, coinShape);
        this.addImage(image).setOffset(new Vec2(0, -0.5f));
    }

    @Override
    public void destroy()
    {
        coinSound.play();
        super.destroy();
    }
}
