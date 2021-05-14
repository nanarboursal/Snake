package Model;
import javax.swing.Timer;

import View.GameFrame;

/**
 * 
 * Class: Model.java
 * Purpose: Initializes AppleModel object and SnakeModel object
 *
 */

public class Model {

	public static AppleModel applemodel = AppleModel.getInstance();
	public static SnakeModel snakemodel = new SnakeModel();

}
