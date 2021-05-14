package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.AppleModel;
import Model.KeyDetails;
import Model.SnakeModel;

/**
 * 
 * Class: GamePanel.java
 * Purpose: Extends JPanel. Creates the game's panel, incorporating components like snake and apple 
 *
 */
public class GamePanel extends JPanel implements ActionListener {

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
	 * KeyAdapter to keep track of keys pressed
	 */
	MyKeyAdapter adapter = new MyKeyAdapter();

	Timer timer;
	Random random;

	/**
	 * UI elements and model objects
	 */
	JPanel scorePanel = new JPanel();
	JLabel scorelabel = new JLabel();

	AppleModel applemodel;
	SnakeModel snakemodel = new SnakeModel();
	BlockingQueue<KeyDetails> queue;
	KeyDetails keyDetail;
	
	boolean running = snakemodel.getRunning();
	char direction;

	/**
	 * 
	 * @param queue
	 * @param applemodel
	 * @param snakemodel
	 * 
	 * Constructor to initialize all models and queue
	 * Also sets the basic JPanel settings
	 */
	public GamePanel(BlockingQueue<KeyDetails> queue, AppleModel applemodel, SnakeModel snakemodel) {
		this.queue = queue;
		this.applemodel = applemodel;
		this.snakemodel = snakemodel;

		keyDetail = snakemodel.getKeyDetail();
		direction = keyDetail.getDirection();

		random = new Random();

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(adapter);
		startGame();
	}

	/**
	 * Starts game by setting "running" to true and starting timer
	 */
	public void startGame() {
		applemodel.newApple();
		snakemodel.setRunning(true);

		// Initialize timer with DELAY value and this keyword
		// because this class implements ActionListener
		timer = new Timer(DELAY, this);
		timer.start();
	}

	/**
	 * Paint component based on state of variable "running"
	 */
	public void paintComponent(Graphics g) {
		running = snakemodel.getRunning();
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if (running) {
			// Create grids
			for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			}

			// Draw an apple
			g.setColor(Color.green);
			g.fillOval(applemodel.getAppleX(), applemodel.getAppleY(), UNIT_SIZE, UNIT_SIZE);

			// Draw the Snake body
			for (int i = 0; i < snakemodel.getBodyParts(); i++) {
				// Just snake head
				if (i == 0) {
					g.setColor(Color.orange);
					g.fillRect(snakemodel.getX()[i], snakemodel.getY()[i], UNIT_SIZE, UNIT_SIZE);
				}
				// Paint body
				else {
					g.setColor(new Color(45, 180, 0));
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.fillRect(snakemodel.getX()[i], snakemodel.getY()[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			String text = "score: " + applemodel.getApples();
			g.drawString(text, (SCREEN_WIDTH - metrics.stringWidth(text)) / 2, g.getFont().getSize());

		} 
		// If running = false, game over
		else {
			gameOver(g);
		}
	}

	/**
	 * Game over when running is false
	 * Displays screen with score and game over sign
	 * @param g
	 */
	public void gameOver(Graphics g) {
		// Game over text to be displayed
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		// Positioning string to be at the center of the screen
		String text = "GAME OVER x_x";
		g.drawString(text, (SCREEN_WIDTH - metrics1.stringWidth(text)) / 2, SCREEN_HEIGHT / 2);
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		String score_text = "score: " + applemodel.getApples();
		g.drawString(score_text, (SCREEN_WIDTH - metrics2.stringWidth(score_text)) / 2, g.getFont().getSize());
	}

	/**
	 * Invoked when an action is performed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// if running = true, go through the series of methods
		if (running) {
			snakemodel.move();
			applemodel.checkApple(snakemodel.getX(), snakemodel.getY(), snakemodel.getBodyParts());
			snakemodel.checkCollisions();
			running = snakemodel.getRunning();
		}
		// else repaint()
		repaint();
	}

	/**
	 * Class for KeyAdapter
	 *
	 */
	public class MyKeyAdapter extends KeyAdapter {

		/**
		 * Invoked when a key is pressed
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			queue.clear();
			// To avoid snake from making a 180 degree turn into itself,
			// case statements to prevent that and only then change direction variable
			switch (e.getKeyCode()) { // getKeyCode() returns corresponding numerical value of key pressed
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
					keyDetail.setDirection(direction);
					try {
						queue.put(keyDetail);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
					keyDetail.setDirection(direction);
					try {
						queue.put(keyDetail);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
					keyDetail.setDirection(direction);
					try {
						queue.put(keyDetail);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
					keyDetail.setDirection(direction);
					try {
						queue.put(keyDetail);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
			}
		}
	}
} // end of class