import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Model.SnakeModel;

class SnakeModelTest {

	/**
	 * Test for initial direction of snake
	 */
	@Test
	void getDirectionTest() {
		SnakeModel snakeModel = new SnakeModel();
		char direction = snakeModel.getDirection();
		
		boolean checkDir = false;
		
		if(direction == 'R') {
			checkDir = true;
		}
		
		assertTrue(checkDir);
	}
	
	/**
	 * Test for initial movement of snake head
	 */
	@Test
	void moveTest() {
		SnakeModel snakeModel = new SnakeModel();

		snakeModel.move();
		
		int new_x[] = snakeModel.getX();

		boolean checkX = false;
		
		if((snakeModel.getDirection() == 'R') && (new_x[0] == 25)) {
			checkX = true;
		}
		
		assertTrue(checkX);
	}

}
