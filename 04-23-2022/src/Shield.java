/*
 * Helper class for shield.
 */
public class Shield extends Prime {
	private int life;   //the maximum hits tolerable before being destroyed
	
	Shield() {
		super();
		life = 4;
	}
	
	Shield(int x0, int y0) {
		super(x0, y0);
		life = 4;
	}

	public int getLife() {
		return life;
	}
	
	public void setLife(int L) {
		life = L;
	}
	
	public void decreaseLife() {
		if(life>1)
			life--;
		else
			setVisible(false);
	}
}