package ie.gmit.sw.ai;

import javafx.application.Application;

public class Runner {

	public static void main(String[] args) {
		/*
		 * PLEASE READ CAREFULLY
		 * ---------------------
		 * Singletons!
		 * If you need to load FCL functions to the application or
		 * train, configure and load a neural network, then it is 
		 * best to do all of this before loading the UI. Launching
		 * a UI in any language or framework and then starting a 
		 * long running task in the same thread is guaranteed to
		 * freeze the screen and crash the programme.
		 */

		
		  //Add long-running initialisation instructions here.

		/*
		 * Launch the JavaFX UI only when all the long-running AI 
		 * configuration tasks have been completed. Use the arrow 
		 * keys to move the player character and the 'Z' key to 
		 * toggle the zoom in / out.
		 */
		Application.launch(GameWindow.class, args);
	}


}