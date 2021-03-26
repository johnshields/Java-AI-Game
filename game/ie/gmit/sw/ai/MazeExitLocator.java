package ie.gmit.sw.ai;

import javafx.application.Platform;

/**
 * Class MazeExitLocator
 * Works with TempRadius to help the Player find the mazeExit.
 *
 * @author John Shields - G00348436
 */
public class MazeExitLocator {
    public static void mazeExitLocator() {
        TempRadius fc = new TempRadius();
        // Set inputs
        int fuzzyValue = fc.getTempRadius(GameWindow.playerLocation, GameWindow.mazeExit);
        // Working out Player's location.
        //System.out.println("Player Location: " + GameWindow.playerLocation + "\nFuzzy Value: " + fuzzyValue);

        // if statement to use getTempRadius return value to determinate the Player's tempRadius.
        if (fuzzyValue >= 180) {
            System.out.println("Player is frostbite");
        } else if (fuzzyValue >= 150) {
            System.out.println("Player is freezing");
        } else if (fuzzyValue >= 120) {
            System.out.println("Player is cold");
        } else if (fuzzyValue >= 80) {
            System.out.println("Player is chilly");
        } else if (fuzzyValue >= 60) {
            System.out.println("Player is warmish");
        } else if (fuzzyValue >= 30) {
            System.out.println("Player is warm");
        } else if (fuzzyValue >= 20) {
            System.out.println("Player is hot");
        } else if (fuzzyValue >= 10) {
            System.out.println("Player is red hot");
        }
        // if Player is at the exit of the maze (randomly generated) game is won.
        if (GameWindow.playerLocation == GameWindow.mazeExit) {
            System.out.println("You escaped the Maze!\nGame Won!");
            Platform.exit(); // Exit GUI.
        }
    }
}