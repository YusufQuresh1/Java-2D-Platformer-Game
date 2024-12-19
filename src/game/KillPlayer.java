package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class KillPlayer implements CollisionListener {
    private final Player player;
    private final GameLevel currentlevel;

    public KillPlayer(Player p, GameLevel l){
        player = p;
        currentlevel = l;
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof RobotBig) {
            player.setHealth(0);
        } else if (collisionEvent.getOtherBody() instanceof RobotSmall ||
                collisionEvent.getOtherBody() instanceof RobotFlying) {
            player.setHealth(player.getHealth()-1);
        }
        if (player.getHealth() <= 0) {
            player.setDead(true);
            collisionEvent.getReportingBody().destroy();
            currentlevel.getRobotSound().close();
            currentlevel.getGameMusic().close();
            currentlevel.getGame().gameEnded();
        }
    }
}
