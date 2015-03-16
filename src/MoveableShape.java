import java.awt.Graphics2D;

/**
 * MoveableShape interface
 * @author tperez4
 *
 */

public interface MoveableShape {
	
	void move();
	boolean collide(MoveableShape other);
	void draw(Graphics2D g2);
	
}
