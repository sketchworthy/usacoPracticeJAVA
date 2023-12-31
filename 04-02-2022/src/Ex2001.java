import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Ex2001 {
	public static void main(String[] args) {
		JFrame f = new JFrame("Graphics 1");
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

		// defined inside Ex2001, so it becomes a member class
		
		public void paintComponent(Graphics g) {
				// overwriting a JPanel function with the same name
			g.setColor(Color.RED); // doesn't matter if upper or lowercase
						// but letters have to all be upper or lowercase
			g.drawString("Hello World!!!", 50, 50);
			
			g.setFont(new Font("Consolas", Font.PLAIN, 20));
			g.setColor(Color.green);
			g.drawString("Java is COOL!!!", 50, 100);
		}
		
	}
}
