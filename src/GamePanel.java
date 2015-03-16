import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A Game Panel object
 * @author tperez4
 *
 */

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long 			serialVersionUID				= 4798445055965105091L;
	
	public static ArrayList<Creature>	creatures;
	
	private Random 						random = new Random() ;
	
	private static int					totalClicks						= 0;
	private static Predator 			predator;
	
	private static Timer				predatorTimer;
	private static Timer 				preyTimer;
	
	private final int					PREDATOR_SPEED					= 17;
	private final int 					PREY_SPEED						= 80; 
	
	private final int 					NUMBER_OF_ORANGE_PREY 			= 5 ;
	private final int 					NUMBER_OF_PINK_PREY 			= 20 ;
	
	public final static int 			GAME_PANEL_WIDTH 				= 600;
	public final static int 			GAME_PANEL_HEIGHT 				= 500;
	
	/**
	   Constructs a GamePanel
	 */
	public GamePanel() {
		super();
		creatures = new ArrayList<Creature>();
		predator = new Predator(0,0,60,60);
		
		for (int i=0; i < NUMBER_OF_PINK_PREY; i++) {
			int x = random.nextInt(GAME_PANEL_WIDTH-50);
			int y = random.nextInt(GAME_PANEL_HEIGHT-100);
			PinkPrey pinkPrey = new PinkPrey(x,y,15,20);
			creatures.add(pinkPrey);
		}
		
		for (int i=0; i < NUMBER_OF_ORANGE_PREY; i++) {
			int x = random.nextInt(GAME_PANEL_WIDTH-50);
			int y = random.nextInt(GAME_PANEL_HEIGHT-100);
			OrangePrey orangePrey = new OrangePrey(x,y,15,20);
			creatures.add(orangePrey);
		}
		
		//Action Listener for the predatorTimer
		ActionListener predatorListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!creatures.isEmpty()) {
					predator.move();
					repaint();
				}
				
				//Checks whether Prey object should avoid Predator object 
				for (Creature creature : creatures) {
					if ( creature.predatorCheck(predator) == true ) {
						creature.evade(predator);
					}
				}
				
				if (creatures.isEmpty()) {
					TimerPanel.getTimer().stop();
				}
			}
		};
		
		//Action Listener for the preyTimer
		ActionListener preyListener = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					for (Creature creature : creatures) {
						if (predator.collide(creature)) {
							creatures.remove(creature);
							break;
						}
					}
					
					for (Creature creature : creatures) {
						creature.move();
					}
					repaint();
						
				}
			};
		
		//initialize the timers
		predatorTimer = new Timer(PREDATOR_SPEED, predatorListener);
		preyTimer = new Timer(PREY_SPEED,preyListener);
		
		class GamePanelListener implements MouseListener {

			@Override
			public void mousePressed(MouseEvent arg0) {
				TimerPanel.getTimer().start();
				preyTimer.start();
				predatorTimer.start();
				
				//Predator object changes direction only after the first click
				if (totalClicks > 0) {
					changeDirection(arg0.getButton());
					predator.move();
				}
				totalClicks++;
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		GamePanelListener gamePanelListener = new GamePanelListener();
		this.addMouseListener(gamePanelListener);
	}
		
	/**
	   Paints the Game Panel
	 */
	public void paintComponent(Graphics g)
    {
		
		Graphics2D g2 = (Graphics2D) g ;
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		for (Creature creature : creatures) {
			creature.draw(g2);
		}
		
		predator.draw(g2);
	 }
	
	/**
	 * Method to get the Creature ArrayList
	 */
	public static ArrayList<Creature> getCreatureList() {
		return creatures;
	}
	
	/**
	 * Method to change the direction of the Predator object
	 * 1 is passed if left mouse button is clicked
	 * 3 is passed if right mouse button is clicked
	 * @param mouseButton
	 */
	public void changeDirection(int mouseButton) {
		String currentDirection = predator.getDirection();
		
		if (mouseButton == 1 && currentDirection.equals("down")) {
			predator.setRight();
		}
		
		else if (mouseButton == 1 && currentDirection.equals("left") ) {
				predator.setDown();
		}
		
		else if (mouseButton == 1 && currentDirection.equals("right")) {
			predator.setUp();
		}
		
		else if (mouseButton == 1 && currentDirection.equals("up")) {
			predator.setLeft();
		}
		
		else if (mouseButton == 3 && currentDirection.equals("down") ){
			predator.setLeft();
		}
		else if (mouseButton == 3 && currentDirection.equals("left")) {
			predator.setUp();
		}
		else if (mouseButton == 3 && currentDirection.equals("right")) {
			predator.setDown();
		}
		else if (mouseButton == 3 && currentDirection.equals("up")){
			predator.setRight();
		}
		
	}
	
}
