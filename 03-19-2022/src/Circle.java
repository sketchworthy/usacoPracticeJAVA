public class Circle {
	
	// variables are usually private for security or convenience
	// public variables can be changed accidentally
	private int cx, cy; // center's x and y values
	private int radius;
	
	Circle() { // constructor, initializes variables
		cx = 1;
		cy = 1;
		radius = 2;
	}
	
	Circle(int x, int y, int r) {
		cx = x;
		cy = y;
		radius = r;
	}
	
	Circle(int x, int y) {
		cx = x;
		cy = y;
		radius = 0;
	}
	
	public double getArea() {
		return (radius*radius*Math.PI);
	}
	
	public double getPerimeter() {
		return (2*radius*Math.PI);
	}
	
	public boolean isInside(int x, int y) {
		int d2 = (cx-x)*(cx-x)+(cy-y)*(cy-y);
		return d2<radius*radius;
	}
	
	public int getCx() { // getter
		return cx;
	}
	
	public int getCy() { // getter
		return cy;
	}

	public void setCx(int v) { // setter
		cx = v; // set cx to value v
	}
	
	public void setCy(int v) { // setter
		cy = v; // set cy to value v
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int _radius) {
		radius = _radius;
	}
	
	public void laugh() { // function defining how a circle laughs
		System.out.println("I am too round.");
	}
	
}
