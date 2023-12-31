import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class trafficLights extends JFrame {
	int WIDTH=500, HEIGHT=500;
	int ULx = 0, ULy = 0; // traffic light position
	int light = 0; // 2 is red, 1 is yellow, 0 is green
	int timer = 0; // 10 max for red+green, 4 for yellow
	int circDiameter=50; // diameter of one light
	int UPDATE_PERIOD = 300; // in milliseconds
	
	public trafficLights() throws Exception {
		TrafficLightPanel p = new TrafficLightPanel();
		add(p);
		setSize(WIDTH,HEIGHT);
		setTitle("Traffic Light");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(50,50);
		
		ActionListener updateTask = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateScreen();
				repaint();
			}
		};
		
		Timer t = new Timer(UPDATE_PERIOD, updateTask);
		t.start();
	}
	
	private class TrafficLightPanel extends JPanel{
		public void paintComponent(Graphics g) {
			drawLight(ULx,ULy,g);
		}
	}
	
	public void drawLight(int x, int y, Graphics g) {
		// traffic light rectangle
		g.setColor(Color.darkGray);
		g.fillRect(ULx, ULy, circDiameter+10, circDiameter*3+30);
		g.setColor(Color.red);
		g.fillOval(ULx+5, ULy+5, circDiameter, circDiameter);
		g.setColor(Color.yellow);
		g.fillOval(ULx+5, ULy+circDiameter+10,circDiameter,circDiameter);
		g.setColor(Color.green);
		g.fillOval(ULx+5, ULy+2*circDiameter+15,circDiameter,circDiameter);
		if(light==0) { // green
			g.setColor(Color.green);
			g.drawOval(ULx,ULy+2*circDiameter+10,circDiameter+10,circDiameter+10);
		}
		else if(light==1) {
			g.setColor(Color.yellow);
			g.drawOval(ULx,ULy+circDiameter+5,circDiameter+10,circDiameter+10);
		}
		else { // red
			g.setColor(Color.red);
			g.drawOval(ULx,ULy,circDiameter+10,circDiameter+10);
		}
	}
	
	public void updateScreen() {
		timer++;
		if(timer>=4&&light==1) { // if yellow light
			light++;
			timer=0;
		}
		else if(timer==10&&light==0) { // if light green
			timer=0;
			light++;
		}
		else if(timer==10&&light==2) {
			timer=0;
			light=0;
		}
	}
	
	public static void main(String[] args)throws Exception{
		new trafficLights();
	}	
}


