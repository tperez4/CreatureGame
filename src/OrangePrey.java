import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 * An Orange Prey shape
 * @author tperez4
 */

public class OrangePrey extends Creature {

	/**
	 * 
	 */
	private static final long 	serialVersionUID 			= 4162157066795466075L;
	private boolean				predatorNear				= true;
	private boolean				forward						= true;
	private int 				steps 						= 0;
	private static final int	MAX_STEPS					= 15;
	

	/**
	  Constructs a prey using the given parameters
	  @param x	the position of the left edge of the bounding bpx
	  @param y  the position of the top edge of the bounding box
	  @param w  the width of the bounding box
	  @param h  the height of the bounding box
	 */
	public OrangePrey(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 	Returns true if Predator is within a certain distance
	 	@param p the Predator object 
	 */
	public boolean predatorCheck(Predator p) {
		int distance1 = getX() - p.getX();
		int distance2 = p.getX() - getX();
		int absoluteValue1 = Math.abs(distance1);
		int absoluteValue2 = Math.abs(distance2);
		
		if ((absoluteValue1 <= 25) || (absoluteValue2 <=25)) 
		{
			return predatorNear;
		}		
		return false;
	}
	
	/**
	   Prey evades if Predator object near
	   The Prey will move up or down depending on the location of the Predator
	 */
	public void evade(Predator p) {
		int predatorLocation = p.getY();
		int creatureLocation = getY();
		if (getX() != GamePanel.GAME_PANEL_WIDTH && !(getY() >= GamePanel.GAME_PANEL_HEIGHT-10) && (predatorLocation-creatureLocation < 0)) {
			setXY(getX(), getY()+3);
		}
		else if (getX() != GamePanel.GAME_PANEL_WIDTH && !(getY() <= 5) && (predatorLocation-creatureLocation > 0)) {
			setXY(getX(), getY()-3);
		}
	}
	
	/**
	 	Orange Prey object moves diagonally in one position
	 */
	@Override
	public void move() {
		if (steps == MAX_STEPS) {
			forward=false;
		}
		
		if (steps == 0) {
			forward = true;
		}
		
		if (forward == true && (getX() != GamePanel.GAME_PANEL_WIDTH) && (getY() != GamePanel.GAME_PANEL_HEIGHT)) {
			steps++; 
			setXY(getX()+7, getY());
			repaint();
		}
		else if (forward == false && (getX() != GamePanel.GAME_PANEL_WIDTH) && (getY() != GamePanel.GAME_PANEL_HEIGHT)) {
			steps--; 
			setXY(getX()-7, getY());
			repaint();
		}
	}
	
	/**
	   Draws the Orange Prey object
	 */
	public void draw(Graphics2D g2) {
		final int BODY_WIDTH = 20;
		final int BODY_HEIGHT = 25;
		final int EYE_SIZE = 5;
		
		double headHeight = BODY_HEIGHT*0.8;
		double headX = getX();
		double headY = getY();
		Ellipse2D.Double head = new Ellipse2D.Double(headX, headY, BODY_WIDTH, headHeight);
		
		double leftEyeX = getX() + BODY_WIDTH*0.15;
		double leftEyeY = getY() + BODY_WIDTH*0.2;
		Ellipse2D.Double leftEye = new Ellipse2D.Double(leftEyeX, leftEyeY, EYE_SIZE, EYE_SIZE);
		
		double rightEyeX = getX() + BODY_WIDTH*0.60;
		double rightEyeY = getY() + BODY_WIDTH*0.2;
		Ellipse2D.Double rightEye = new Ellipse2D.Double(rightEyeX, rightEyeY, EYE_SIZE, EYE_SIZE);
		
		double arcX = getX() + (BODY_WIDTH*0.20);
		double arcY = getY() + (BODY_HEIGHT*0.45);
		Arc2D.Double smile = new Arc2D.Double(arcX, arcY, 10, 4, 0, 180, Arc2D.CHORD);
		
		int bottomY = getY() + 9;
		int bottomHeight = (int)(BODY_HEIGHT * 0.3);
		Rectangle bottom = new Rectangle(getX(), bottomY, BODY_WIDTH, bottomHeight);
		
		g2.setColor(Color.ORANGE);
		g2.fill(head);
		g2.fill(bottom);
		g2.setColor(Color.WHITE);
		g2.draw(leftEye);
		g2.draw(rightEye);
		g2.setColor(Color.GREEN);
		g2.fill(leftEye);
		g2.fill(rightEye);
		g2.setColor(Color.RED);
		g2.fill(smile);
	}

}
