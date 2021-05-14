package Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import Model.KeyDetails;
import Model.Message;
import Model.Model;
import View.GameFrame;

/**
 * 
 * Class: Controller.java 
 * Purpose: Controls the key events and sets direction of snake accordingly
 */

public class Controller {

	BlockingQueue<KeyDetails> queue;
	Model model;
	GameFrame view;
	private List<Valve> valves = new LinkedList<Valve>();

	public Controller(BlockingQueue<KeyDetails> queue, Model model, GameFrame view) {
		this.queue = queue;
		this.model = model;
		this.view = view;
		valves.add(new NewValve());
	}

	/**
	 * Takes events from queue and sets direction
	 */
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		
		while (response != ValveResponse.FINISH) {
			try {
				message = queue.take();
			} catch (InterruptedException exception) {
				// do nothing
			}

			for (Valve valve : valves) {
				response = valve.execute(message);
				
				if(response != ValveResponse.MISS) {
					break;
				}
			}
		}
	}

	private class NewValve implements Valve {

		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != KeyDetails.class) {
				return ValveResponse.MISS;
			}

			// Set snake direction
			KeyDetails kd = (KeyDetails) message;
			if (kd.getDirection() == 'R') {
				Model.snakemodel.setDirection('R');
			} else if (kd.getDirection() == 'L') {
				Model.snakemodel.setDirection('L');
			} else if (kd.getDirection() == 'U') {
				Model.snakemodel.setDirection('U');
			} else if (kd.getDirection() == 'D') {
				Model.snakemodel.setDirection('D');
			}
			return ValveResponse.EXECUTED;
		}

	}

	private interface Valve {
		/**
		 * Performs certain actions in response to a message
		 */
		public ValveResponse execute(Message message);
	}
} // end of class
