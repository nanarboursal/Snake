import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import Model.AppleModel;
import Model.SnakeModel;

class AppleModelTest {

	/**
	 * Test for apple coordinates
	 */
	@Test
	@Order(2)
	void getAppleTest() {
		AppleModel applemodel = AppleModel.getInstance();
		applemodel.newApple();
		
		int x = applemodel.getAppleX();
		int y = applemodel.getAppleY();

		boolean checkX = false;
		boolean checkY = false;

		if(x > 0 && x < 600) {
			checkX = true;
		}

		if(y > 0 && y < 600) {
			checkY = true;
		}
		
		assertTrue(checkX);
		assertTrue(checkY);

	}
	
	/**
	 * Test for checking if apple is eaten
	 */
	@Test
	@Order(1)
	void checkAppleTest() {
		AppleModel model = AppleModel.getInstance();
		int cur_applesEaten = model.getApples();
		
		SnakeModel snakemodel = new SnakeModel();
		
		model.checkApple(snakemodel.getX(), snakemodel.getY(), snakemodel.getBodyParts());
		
		boolean checkApples = false;
		if(model.getApples() >= cur_applesEaten) {
			checkApples = true;
		}
		
		assertTrue(checkApples);
	}

}
