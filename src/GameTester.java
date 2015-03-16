import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class to launch the game
 * @author tperez4
 *
 */

public class GameTester {

	public static void main(String[] args) {
		final int WIDTH = 700;
		final int HEIGHT = 650;
		JFrame frame = new JFrame("Pacman Game");
		frame.setLayout(new BorderLayout());

		//adds the TimerPanel to the frame
		JPanel timerPanel = new TimerPanel();
		frame.add(timerPanel, BorderLayout.NORTH);
		
		//adds the GamePanel to the frame
		JPanel gamePanel = new GamePanel();
		frame.add(gamePanel, BorderLayout.CENTER);
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
		
}
