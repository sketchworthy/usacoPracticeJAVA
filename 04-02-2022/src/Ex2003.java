import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Ex2003 {
	public static void main(String[] args) {
		JFrame f = new JFrame("Draw Line");
		f.setSize(400,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel p = new MyPanel();
		f.add(p); // automatically calls function paintComponent
		
		f.setVisible(true);
		
		
	}
	static class MyPanel extends JPanel { 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g) {
			g.drawLine(50, 50, 300, 50);
			g.drawLine(50, 50, 40, 100);
			g.drawLine(300, 50, 40, 100);
			g.drawString("A", 45, 45);
			g.drawString("B", 305, 50);
			g.drawString("C", 25, 105);
		}
		
	}
}
