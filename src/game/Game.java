package game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author      Mohammed, Qureshi, mohammed.qureshi.3@city.ac.uk
 * @version     19
 * @since       2023
 */
public class Game {

    private GameLevel currentLevel;
    private final GameView view;

    private static boolean won;
    private static JFrame frame = null;

    private static SplashMenu menu;
    private final PlayerController controller;
    /** Initialise a new Game. */
    public Game() {

        //make an empty game world
        currentLevel = new Level1(this);

        //make a view to look into the game world
        view = new GameView(currentLevel);

        //allows the player to be controlled (move and jump)
        controller = new PlayerController(currentLevel.getPlayer());
        view.addKeyListener(controller);

        GiveFocus gf = new GiveFocus(view);
        view.addMouseListener(gf);

        //allows player to shoot projectiles
        Shoot d = new Shoot(currentLevel, view);
        view.addMouseListener(d);


        //create a Java window (frame) and add the game
        //   view to it
        frame = new JFrame("City Game");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        // start our game world simulation!
        currentLevel.start();
    }

    /**
     * method to go from one level to the next. Ends the music along with the rest of the current level and depending on
     * what level it is, it will create a new level of the following level and start it. If it is the final level it will
     * call the gameEnded method once it is completed.
     */
    public void goToNextLevel(){

        currentLevel.getGameMusic().close();
        currentLevel.getRobotSound().stop();
        currentLevel.stop();
        Player prevPlayer = currentLevel.getPlayer();
        if (currentLevel instanceof Level1) {
            currentLevel = new Level2(this);
        } else if (currentLevel instanceof Level2) {
            currentLevel = new Level3(this);
        } else if (currentLevel instanceof Level3){
            currentLevel.getRobotSound().close();
            won = true;
            gameEnded();
        }
        Player newPlayer = currentLevel.getPlayer();
        newPlayer.setHealth(prevPlayer.getHealth());
        newPlayer.setCoins(prevPlayer.getCoins());
        view.setWorld(currentLevel);
        view.setLevel(currentLevel);
        controller.updatePlayer(newPlayer);
        Shoot d = new Shoot(currentLevel, view);
        view.addMouseListener(d);
        currentLevel.start();
    }

    public void gameEnded(){
        frame.remove(view);
        menu = new SplashMenu();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.add(menu.getMainPanel());
        frame.pack();
    }

    public static void restartGame(){
        frame.setVisible(false);
        new Game();
    }

    public static void closeGame(){
        frame.setVisible(false);
    }

    public static boolean isWon() {
        return won;
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
