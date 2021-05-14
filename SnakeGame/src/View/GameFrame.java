package View;

import java.awt.FlowLayout;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.AppleModel;
import Model.KeyDetails;
import Model.SnakeModel;

import View.GamePanel;

public class GameFrame extends JFrame {

	/**
	 * 
	 * @param queue
	 * @param applemodel
	 * @param snakemodel
	 * 
	 * Create the JFrame where GamePanel is added
	 */
	public GameFrame(BlockingQueue<KeyDetails> queue, AppleModel applemodel, SnakeModel snakemodel) {

		GamePanel panel = new GamePanel(queue,applemodel, snakemodel);
		this.add(panel);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// Fit JFrame around all other view components
		this.pack();
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		// Set window in the middle of the screen
		this.setLocationRelativeTo(null);
	}
}
