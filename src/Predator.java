import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 * A Predator shape
 * @author tperez4
 */
public class Predator extends Creature {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 909754447874051712L;
	private static boolean	down		= false;
	private static boolean	up			= false;
	private static boolean	left		= false;
	private static boolean	right		= true;

	
	/**
	  Constructs a prey using the given parameters
	  @param x	the position of the left edge of the bounding bpx
	  @param y  the position of the top edge of the bounding box
	  @param w  the width of the bounding box
	  @param h  the height of the bounding box
	 */
	public Predator(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	/**
	 * Checks which direction the Predator object is currently moving in
	 * @return String of the current direction
	 */
	
	public String getDirection() {
		String direction = "";
		if (down == true) {
			direction = "down";
		}
		else if (left == true) {
			direction = "left";
		}
		else if (right == true) {
			direction = "right";
		}
		else if (up == true) {
			direction = "up";
		}
		return direction;
	}
	
	/**
	 	Moves the Predator object
	 	Ensures that the Predator stops moving if it reaches the boundaries of the Game Panel
	 */
	public void move() {
		if (down == true && getY() != GamePanel.GAME_PANEL_HEIGHT){
			setXY(getX(),getY()+5);
		}
		
		else if (down == true && getY() == GamePanel.GAME_PANEL_HEIGHT && getX() == 0) {
				setRight();
		}
		
		else if (down == true && getY() == GamePanel.GAME_PANEL_HEIGHT && getX() != 0) {
				setLeft();
		}
		
		else if (left == true && getX() != 0) {
			setXY(getX()-5, getY());
			if (getX()==0 && getY() != GamePanel.GAME_PANEL_HEIGHT) {
				setDown();
			}
			if (getX() == 0 && getY() == GamePanel.GAME_PANEL_HEIGHT) {
				setUp();
			}
		} 
		
		else if (right == true && getX() != GamePanel.GAME_PANEL_WIDTH) {
			setXY(getX()+5,getY());
			if (getX() == GamePanel.GAME_PANEL_WIDTH && getY() == 0) {
				setDown();
			}
			else if (getX() == GamePanel.GAME_PANEL_WIDTH && getY() == GamePanel.GAME_PANEL_HEIGHT) {
				setUp();
			}
			else if (getX() == GamePanel.GAME_PANEL_WIDTH) {
				setDown();
			}
		}
		
		else if (up == true && getY() == 0) {
			if (getX() ==0) {
				setRight();
			}
			else {
				setLeft();
			}
		}
		
		else if (up == true && getY() != 0) {
			setXY(getX(), getY()-5);
			if (getX() == GamePanel.GAME_PANEL_WIDTH){
				setLeft();
			}
		}
	}
	
	/**
	 * Methods to set the direction the Predator object is moving
	 */
	public void setDown() {
		down = true;
		left = false;
		right = false;
		up = false;
	}
	
	public void setLeft() {
		down = false;
		left = true;
		right = false;
		up = false;
	}
	
	public void setRight(){
		down = false;
		left = false;
		right = true;
		up = false;
	}
	
	public void setUp(){
		down = false;
		left = false;
		right = false;
		up = true;
	}
	
	/**
	 	Checks whether the Predator object is colliding with any other Creature object
	 */
	public boolean collide(MoveableShape ms) {
		Creature creature = (Creature) ms;
		boolean collision;
		if (contains(creature.getX(), creature.getY())) {
			collision = true;
		}
		else {
			collision = false;
		}
		return collision;
	}
	
	/**
	   Draws the Predator object
	 */
	public void draw(Graphics2D g2) {
		final int BODY_SIZE = 60;
		final int EYE_SIZE = 10;
		
		double headX = getX();
		double headY = getY();
		Ellipse2D.Double head = new Ellipse2D.Double(headX+2, headY+2, BODY_SIZE, BODY_SIZE);
		
		double eyeRightX = getX() + (BODY_SIZE*0.55);
		double eyeRightY = getY() + (BODY_SIZE*0.25);
		Ellipse2D.Double eyeRight = new Ellipse2D.Double(eyeRightX, eyeRightY, EYE_SIZE, EYE_SIZE);
		
		double eyeLeftX = getX() + (BODY_SIZE*0.25);
		double eyeLeftY = getY() + (BODY_SIZE*0.25);
		Ellipse2D.Double eyeLeft = new Ellipse2D.Double(eyeLeftX, eyeLeftY, EYE_SIZE, EYE_SIZE);

		double arcX = getX() + (BODY_SIZE*0.25);
		double arcY = getY() + (BODY_SIZE*0.45);
		Arc2D.Double smile = new Arc2D.Double(arcX, arcY, 30, 20, 180, 180, Arc2D.CHORD);
		
		g2.setColor(Color.YELLOW);
		g2.fill(head);
		g2.setColor(Color.WHITE);
		g2.draw(eyeRight);
		g2.draw(eyeLeft);
		g2.setColor(Color.BLACK);
		g2.fill(eyeRight);
		g2.fill(eyeLeft);
		g2.setColor(Color.RED);
		g2.fill(smile);
		
	}

}
