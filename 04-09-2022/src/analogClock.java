// unfinished, see 2022-04-09's folder 'unit 21', LiveClock

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class analogClock extends JFrame {
    int WIDTH = 600,  HEIGHT = 300;
    int ULx = 75,  ULy = 0;

    int xSpeed = 0;
    int ySpeed = 0;

    int UPDATE_PERIOD = 60; 
    
//    BufferedImage car;

    public analogClock() throws Exception {
        ClockPanel p = new ClockPanel();
        add(p);
        setSize(WIDTH, HEIGHT);
        setTitle("Analog Clock");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200,100);

        ActionListener updateTask = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updatePosition(); //update the upper-left corner (x, y)
                repaint(); //refresh the JFrame, callback paintComponent()
            }
        };

        Timer t = new Timer(UPDATE_PERIOD, updateTask); // delay in milliseconds, thing to do
        t.start();
        
//        car = ImageIO.read(new File("car.jpg"));
    }

    private class ClockPanel extends JPanel {
        public void paintComponent(Graphics g) {
            drawStaticClock(ULx+300, ULy+100, g);
        }
    }

    public void drawStaticClock(int x, int y, Graphics g) {
    	g.drawOval(200, 0, 200, 200);
    	g.drawLine(300, 100, x, y); // second hand


    }

    public void updatePosition() {
    	xSpeed++;
        ULx = (int) (75*Math.cos(Math.toDegrees(xSpeed)));
        ULy = (int) (75*Math.sin(Math.toDegrees(xSpeed)));
    }

    public static void main(String[] args) throws Exception {
        new analogClock();
    }

}  //end of analogClock class