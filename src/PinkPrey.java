import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 * A Pink Prey shape
 * @author tperez4
 */

public class PinkPrey extends Creature {

	/**
	 * 
	 */
	private static final long 	serialVersionUID 			= -3193097429282046468L;
	private boolean				forward						= true;
	private int 				steps 						= 0;
	private static final int	MAX_STEPS					= 35;
	
	/**
	  Constructs a prey using the given parameters
	  @param x	the position of the left edge of the bounding bpx
	  @param y  the position of the top edge of the bounding box
	  @param w  the width of the bounding box
	  @param h  the height of the bounding box
	 */
	public PinkPrey(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	   Pink Prey object moves left to right
	 */
	@Override
	public void move() {
		if (steps == MAX_STEPS) {
			forward=false;
		}
		
		if (steps == 0) {
			forward = true;
		}
		
		if (forward == true) {
			steps++; 
			setXY(getX()+1, getY());
			repaint();
		}
		else if (forward == false) {
			steps--; 
			setXY(getX()-1, getY());
			repaint();
		}
	}
	
	public void draw(Graphics2D g2) {
		final int BODY_WIDTH = 15;
		final int BODY_HEIGHT = 20;
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
		
		g2.setColor(Color.PINK);
		g2.fill(head);
		g2.fill(bottom);
		g2.setColor(Color.WHITE);
		g2.draw(leftEye);
		g2.draw(rightEye);
		g2.setColor(Color.BLUE);
		g2.fill(leftEye);
		g2.fill(rightEye);
		g2.setColor(Color.RED);
		g2.fill(smile);
	}
	

}

