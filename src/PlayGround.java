import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayGround extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titleImage;
	private int[] snakeX = new int[750];
	private int[] snakeY = new int[750];
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private ImageIcon leftMouth;
	private ImageIcon rightMouth;
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeImage;
	private int snakeLength = 3;
	private int move = 0;
	private ImageIcon enemyImage;
	private Random rand = new Random();
	private int Xpos = rand.nextInt(34);
	private int Ypos = rand.nextInt(23);

	private int[] enemyposX = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] enemyposY = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };
	private int score = 0;

	public PlayGround() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		if (move == 0) {
			snakeX[2] = 50;
			snakeX[1] = 75;
			snakeX[0] = 100;

			snakeY[2] = 100;
			snakeY[1] = 100;
			snakeY[0] = 100;
		}

		// Draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);

		// Draw the title image
		titleImage = new ImageIcon("src/images/snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);

		// Border for playing ground
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);

		// Background for playground
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);

		// Draw Score board
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 780, 30);

		// Draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Length: "+ snakeLength, 780, 50);

		rightMouth = new ImageIcon("src/images/rightmouth.png");
		rightMouth.paintIcon(this, g, snakeX[0], snakeY[0]);

		for (int i = 0; i < snakeLength; i++) {
			if (i == 0 && right) {
				rightMouth = new ImageIcon("src/images/rightmouth.png");
				rightMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}

			if (i == 0 && left) {
				leftMouth = new ImageIcon("src/images/leftmouth.png");
				leftMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}

			if (i == 0 && up) {
				upMouth = new ImageIcon("src/images/upmouth.png");
				upMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}

			if (i == 0 && down) {
				downMouth = new ImageIcon("src/images/downmouth.png");
				downMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
			}

			if (i != 0) {
				snakeImage = new ImageIcon("src/images/snakeimage.png");
				snakeImage.paintIcon(this, g, snakeX[i], snakeY[i]);
			}
		}
		enemyImage = new ImageIcon("src/images/enemy.png");
		if (enemyposX[Xpos] == snakeX[0] && enemyposY[Ypos] == snakeY[0]) {
			score++;
			snakeLength++;
			Xpos = rand.nextInt(34);
			Ypos = rand.nextInt(23);
		}

		enemyImage.paintIcon(this, g, enemyposX[Xpos], enemyposY[Ypos]);

		for (int i = 1; i < snakeLength; i++) {
			if(snakeX[i] == snakeX[0] && snakeY[i]== snakeY[0]){
				right = false;
				left = false;
				up = false;
				down = false; 
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press SpaceBar to Restart", 320, 340);
			}
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if (right) {
			for (int i = snakeLength; i >= 0; i--) {
				snakeY[i + 1] = snakeY[i];
			}

			for (int i = snakeLength; i >= 0; i--) {
				if (i == 0) {
					snakeX[i] = snakeX[i] + 25;
				} else {
					snakeX[i] = snakeX[i - 1];
				}
				if (snakeX[i] > 850) {
					snakeX[i] = 25;
				}
			}

			repaint();
		}

		if (left) {
			for (int i = snakeLength; i >= 0; i--) {
				snakeY[i + 1] = snakeY[i];
			}

			for (int i = snakeLength; i >= 0; i--) {
				if (i == 0) {
					snakeX[i] = snakeX[i] - 25;
				} else {
					snakeX[i] = snakeX[i - 1];
				}
				if (snakeX[i] < 25) {
					snakeX[i] = 850;
				}
			}

			repaint();
		}

		if (down) {
			for (int i = snakeLength; i >= 0; i--) {
				snakeX[i + 1] = snakeX[i];
			}

			for (int i = snakeLength; i >= 0; i--) {
				if (i == 0) {
					snakeY[i] = snakeY[i] + 25;
				} else {
					snakeY[i] = snakeY[i - 1];
				}
				if (snakeY[i] > 625) {
					snakeY[i] = 75;
				}
			}

			repaint();
		}

		if (up) {
			for (int i = snakeLength; i >= 0; i--) {
				snakeX[i + 1] = snakeX[i];
			}

			for (int i = snakeLength; i >= 0; i--) {
				if (i == 0) {
					snakeY[i] = snakeY[i] - 25;
				} else {
					snakeY[i] = snakeY[i - 1];
				}
				if (snakeY[i] < 75) {
					snakeY[i] = 625;
				}
			}

			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			score = 0;
			move = 0;
			snakeLength = 3;
			repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			move++;
			right = true;
			if (!left) {
				right = true;
			} else {
				left = true;
				right = false;
			}
			up = false;
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			move++;
			left = true;
			if (!right) {
				left = true;
			} else {
				right = true;
				left = false;
			}
			up = false;
			down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			move++;
			up = true;
			if (!down) {
				up = true;
			} else {
				down = true;
				up = false;
			}
			right = false;
			left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			move++;
			down = true;
			if (!up) {
				down = true;
			} else {
				up = true;
				down = false;
			}
			right = false;
			left = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
