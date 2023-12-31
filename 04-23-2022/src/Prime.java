/*
 * Helper class for the basic space object.
 */
import java.awt.*;
import java.awt.image.*;

public class Prime {
	private int x, y;             //location of the space object
	private int speedX, speedY;   //speeds along x and y directions
	private BufferedImage image;  //the image of the object
	private boolean isVisible;    //whether the object is visible

	Prime() {
		init(0, 0);
	}

	Prime(int x0, int y0) {
		init(x0, y0);
	}

	private void init(int x0, int y0) {
		x = x0;
		y = y0;
		speedX = 0;
		speedY = 0;
		image = null;
		isVisible = true;
	}

	//let the invader die
	public void destroy() {
		isVisible = false;
	}

	//change the value of "isVisible"
	public void setVisible(boolean tf) {
		isVisible = tf;
	}

	//display image with the actual dimension
	public void paint(Graphics g) {
		if(isVisible)
			g.drawImage(image, x, y, null);
	}

	//display image with preferred width and height
	public void paint(Graphics g, int prefWidth, int prefHeight) {
		if(isVisible)
			g.drawImage(image, x, y, prefWidth, prefHeight, null);
	}

	//set the location of the space object
	public void setLocation(int x0, int y0) {
		x = x0;
		y = y0;
	}

	//move the space object by its speeds
	public void move() {
		x += speedX;
		y += speedY;
	}
	
	//move object in x-direction by x0
	public void moveX(int x0) {
		x += x0;
	}
	
	//move object in y-direction by y0
	public void moveY(int y0) {
		y += y0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isVisible() {
		return isVisible;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	//set the image for the invader
	public void setImage(BufferedImage img) {
		image = img;
	}
}