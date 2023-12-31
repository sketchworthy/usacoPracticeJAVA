/*
 * Helper class for Invader.
 */
public class Invader extends Prime {
	private int points;    //points for destroying an invader
	
	Invader() {
		super();
		points = 0;
	}
	
	Invader(int x0, int y0) {
		super(x0, y0);
		points = 0;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int p) {
		points = p;
	}
}