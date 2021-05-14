import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.Controller;
import Model.KeyDetails;

import Model.Model;
import View.GameFrame;

/**
 * 
 * Class: App.java
 * Purpose: Contains main() to start the snake game project. Creates the MVC components.
 */

public class App {

	public static void main(String[] args) {

		BlockingQueue<KeyDetails> queue = new LinkedBlockingQueue<>();
		Model model = new Model();
		GameFrame view = new GameFrame(queue, model.applemodel, model.snakemodel);
		Controller controller = new Controller(queue, model, view);
		controller.mainLoop();
	}
}
