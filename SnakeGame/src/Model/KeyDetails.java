package Model;

public class KeyDetails implements Message {
	
	/**
	 * char direction
	 */
	char direction;
	
	/**
	 * @param direction
	 * Sets direction to some initial value
	 */
	public KeyDetails(char direction) {	
		this.direction = direction;
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

}
