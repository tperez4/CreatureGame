import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

/**
 * A Creature object
 * @author tperez4
 *
 */

public class Creature extends JComponent implements MoveableShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7829204600029068L;
	private int xCoord, yCoord; // holds position of top left corner of bounding box
	private int width; //the width of the bounding box
	private int height; //the width of the bounding box
	private Rectangle boundingBox; //the bounding box
	
	/**
	 	Constructs a creature by setting the following instance variables
	   @param x	the position of the left edge of the bounding box
	   @param y the position of the top edge of the bounding box
	   @param w the width of the bounding box 
	   @param h the height of the bounding box
	 */	
	public Creature(int x, int y, int w, int h) {
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
		boundingBox = new Rectangle(xCoord, yCoord, width, height);
	}
		
	/**
	  	Sets the new x and y coordinates
	  	@param xNew the new x coordinate
	  	@param yNew the new y coordinate
	 */
	public void setXY(int xNew, int yNew){
		xCoord = xNew;
		yCoord = yNew;
		boundingBox = new Rectangle(xCoord, yCoord, width, height) ;
		repaint();
	}
	
	public void setX(int xNew) 
    {
		xCoord = xNew ;
		boundingBox = new Rectangle(xCoord, yCoord, width, height) ;
    }
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collide(MoveableShape other) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Checks whether x and y positions are contained within the bounding box
	 * @param x
	 * @param y
	 * @return true if x and y are contained within the bounding box, else return false
	 */
	public boolean contains(int x, int y) {
		if(boundingBox.contains(x,y)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	  	Draws the default shape of the creature
	  	@param g2 graphics context
	 */
	@Override
	public void draw(Graphics2D g2) {
		Ellipse2D.Double ellipse = new Ellipse2D.Double(xCoord, yCoord, width, height);
		g2.setColor(Color.BLUE);
		g2.fill(ellipse);
	}
	
	/**
	 * Method to check if Predator object is near
	 */
	public boolean predatorCheck(Predator p) {
		return false;
	}
	
	/**
	 * Creature will move a large distance
	 */
	public void evade(Predator p){
	}
	
	/**
	 *  Getter methods
	 */
	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
