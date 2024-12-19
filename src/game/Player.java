package game;

import city.cs.engine.*;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class Player extends Walker {

    private final static Shape playerShape = new PolygonShape(
            -1.39f,-1.94f, -1.4f,0.76f, -0.44f,1.98f,
            0.71f,1.98f, 1.38f,0.16f, 1.08f,-1.98f);
    private static final BodyImage image = new BodyImage("data/player.gif", 4);

    private boolean facingRight = true;
    private int health;

    private int coins;

    private boolean keyCollected = false;

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    private boolean dead = false;

    public Player(World world){
        super(world, playerShape);
        this.addImage(image);
        health=4;
    }

    public static BodyImage getImage() {
        return image;
    }

    public boolean isFacingRight() {
        return facingRight;
    }
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;

    }

    public void addCoins() {
        setCoins(getCoins()+1);
        //coins = coins + 1
    }

    public boolean isKeyCollected() {
        return keyCollected;
    }

    public void setKeyCollected(boolean keyCollected) {
        this.keyCollected = keyCollected;

    }
}

