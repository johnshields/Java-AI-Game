package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;

import javafx.concurrent.Task;

/*
 * CharacterTask represents a Runnable (Task is a JavaFX implementation
 * of Runnable) game character. The character wanders around the game
 * model randomly and can interact with other game characters using
 * implementations of the Command interface that it is composed with.
 *
 * You can change how this class behaves is a number of different ways:
 *
 * 1) DON'T CHANGE THIS CLASS
 * 	  Configure the class constructor with an instance of Command. This can
 *    be a full implementation of a fuzzy controller or a neural network. You
 *    can also parameterise this class with a lambda expression for the
 *    command object.
 *
 * 2) CHANGE THIS CLASS
 * 	  You can extend this class and provide different implementations of the
 * 	  call by overriding (not recommended). Alternatively, you can change the
 *    behaviour of the game character when it moves by altering the logic in
 *    the IF statement:
 *
 * 		if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
 * 	    	//Implementation for moving to an occupied cell
 * 	    }else{
 *      	//Implementation for moving to an unoccupied cell
 *      }
 */

public class CharacterTask extends Task<Void> {
    private static final int SLEEP_TIME = 300; //Sleep for 300 ms
    private static ThreadLocalRandom rand = ThreadLocalRandom.current();
    private boolean alive = true;
    private GameModel model;
    private int row;
    private int col;
    public static char enemyID;

    /*
     * Configure each character with its own action. Use this functional interface
     * as a hook or template to connect to your fuzzy logic and neural network. The
     * method execute() of Command will execute when the Character cannot move to
     * a random adjacent cell.
     */
    private final Command cmd;

    public CharacterTask(GameModel model, char enemyID, int row, int col, Command cmd) {
        this.model = model;
        this.row = row;
        this.col = col;
        this.cmd = cmd;
        CharacterTask.enemyID = enemyID;
    }

    @Override
    public Void call() throws Exception {
        /*
         * This Task will remain alive until the call() method returns. This
         * cannot happen as long as the loop control variable "alive" is set
         * to true.
         */
        while (alive) {
            Thread.sleep(SLEEP_TIME);

            synchronized (model) {
                //Randomly pick a direction up, down, left or right
                int temp_row = row, temp_col = col;
                if (rand.nextBoolean()) {
                    temp_row += rand.nextBoolean() ? 1 : -1;
                } else {
                    temp_col += rand.nextBoolean() ? 1 : -1;
                }

                if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
                    model.set(temp_row, temp_col, enemyID);
                    model.set(row, col, '\u0020');
                    row = temp_row;
                    col = temp_col;
                } else {
                    model.set(FuzzyCharacters.gRow, FuzzyCharacters.gCol, enemyID);
                    cmd.execute();
                }
            }
        }
        return null;
    }
}