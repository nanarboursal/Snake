package Model;


/**
 * 
 * Class: SnakeModel.java
 * Purpose: Provides model for snake. Keeps track of body parts, movements, collisions etc.
 */


public class SnakeModel {
	
	/**
	 * Numerical values for screen constants 
	 */
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;

	// The board is divided into small squares, called units

	static final int UNIT_SIZE = 25;

	static final int GAME_UNITS = (SCREEN_WIDTH * (SCREEN_HEIGHT)) / UNIT_SIZE;
	static final int DELAY = 75;
	
	/**
	 * Arrays for tracking x and y position of snake's body
	 */
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];

	/**
	 * Initializing snake parts and running state 
	 */
	int bodyParts = 4;
	boolean running = false;
	
	/**
	 * Initializing direction
	 */
	KeyDetails keyDetail = new KeyDetails('R');
	char direction = keyDetail.getDirection();
	
	/**
	 * Getter for running
	 * @return running
	 */

	public boolean getRunning() {
		return running;
	}
	

	/**
	 * Setter for running
	 * @param running
	 */

	public void setRunning (boolean running) {
		this.running = running;
	}

	/**
	 * Getter for bodyParts
	 * @return bodyParts
	 */
	public int getBodyParts() {
		return bodyParts;
	}
	
	/**
	 * Setter for bodyParts
	 * @param bodyParts
	 */
	public void setBodyParts(int bodyParts) {
		this.bodyParts = bodyParts;
	}
	
	/**
	 * Getter for keyDetail
	 * @return keyDetail
	 */
	public KeyDetails getKeyDetail() {
		return this.keyDetail;
	}

	/**
	 * Getter for direction
	 * @return direction
	 */
	public char getDirection() {
		return direction;
	}
	
	/**
	 * Setter for direction
	 * @param direction
	 */
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	/**
	 * Getter for x coordinates of snake
	 * @return x[]
	 */
	public int[] getX() {
		return x;
	}
	
	/**
	 * Getter for y coordinates of snake
	 * @return y[]
	 */
	public int[] getY() {
		return y;
	}
	
	/**
	 * move() method to move snake body across UNIT_SIZE
	 */
	
	public void move() {

		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		// Switch case for all possible directions
		// Moving the 0th elements which represent the head
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	

	/**
	 * checkCollisions() to detect collisions with snake body or walls
	 */

	public void checkCollisions() {
		// Checks if head collides with body
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false; // trigger game over
			}
		}

		// Checks if head touches left border
		if (x[0] < 0) {
			running = false;
		}

		// Checks if head touches right border
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}

		// Checks if head touches top border
		if (y[0] < 0) {
			running = false;
		}

		// Checks if head touches top border
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}

	}
	

}
