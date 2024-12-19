package game;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import java.util.Objects;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class PlayerCollision implements CollisionListener {

    Player player;
    GameLevel level;
    public PlayerCollision(Player p, GameLevel l){
        player = p;
        level = l;
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Coins) {
            player.addCoins();
            collisionEvent.getOtherBody().destroy(); //removes coin
        }

        if (collisionEvent.getOtherBody() instanceof Key) {
            player.setKeyCollected(true);
            collisionEvent.getOtherBody().destroy(); //key becomes collected
        }

        if (Objects.equals(collisionEvent.getOtherBody().getName(), "door") && player.isKeyCollected()) {
            //collisionEvent.getOtherBody().removeAllImages();
           // collisionEvent.getOtherBody().addImage(new BodyImage("data/door open.png", 4));
            level.setDoorUnlocked(true);
            if (level.isComplete()) {
                level.getGame().goToNextLevel();
            }
        }
    }
}
