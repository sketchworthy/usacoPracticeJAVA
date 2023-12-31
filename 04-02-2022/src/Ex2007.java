import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Ex2007 {
	public static void main(String[] args) {
		JFrame f = new JFrame("Draw Line");
		f.setSize(400,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel p = new MyPanel();
		f.add(p); // automatically calls function paintComponent
		f.setLocation(800, 200);
		f.setVisible(true);
		
		
	}
	static class MyPanel extends JPanel { 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(100, 60, 50, 80);
			
			g.setColor(Color.blue);
			g.fillOval(200, 80, 80, 50);
			
			g.setColor(Color.orange);
			g.drawArc(150, 150, 60, 40, 190, 160);
		}
		
	}
}
