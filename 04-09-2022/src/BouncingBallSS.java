/*
 * bouncy ball screen saver
 */
import javax.imageio.ImageIO;
import java.math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("serial")
public class BouncingBallSS extends JFrame {
	Random gen = new Random(24);
    int WIDTH = 600,  HEIGHT = 400;
    int ULx = gen.nextInt(550),  ULy = gen.nextInt(350);
    
    Random gen2 = new Random(5);
    int xSpeed = 5-gen2.nextInt(10);
    int ySpeed = 5-xSpeed;

    int UPDATE_PERIOD = 50;
    
    Color[] colors = {Color.red,Color.green,Color.blue,
    		Color.yellow,Color.cyan,Color.orange};
    int colorID=0;
    
    public BouncingBallSS() throws Exception {
        BallPanel p = new BallPanel();
        add(p);
        setSize(WIDTH, HEIGHT);
        setTitle("Ball Bounce Screensaver");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(50,100);

        ActionListener updateTask = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updatePosition(); //update the upper-left corner (x, y)
                repaint(); //refresh the JFrame, callback paintComponent()
            }
        };

        Timer t = new Timer(UPDATE_PERIOD, updateTask);
        t.start();
    }

    private class BallPanel extends JPanel {
        public void paintComponent(Graphics g) {
            drawStaticBall(ULx, ULy, g);
        }
    }

    public void drawStaticBall(int x, int y, Graphics g) {
    	g.setColor(colors[colorID]);
    	g.fillOval(x, y, 50, 50);
    	g.setColor(Color.black);
    	g.setFont(new Font("Courier New", Font.BOLD, 17));
    	g.drawString("Java", x+5, y+30);

    }
    
    private void changeColor() {
    	colorID++;
    	colorID=colorID%colors.length;
    }

    public void updatePosition() {
        ULx += xSpeed;
        ULy += ySpeed;
        if(ULx<=0||ULx>=WIDTH-65) {
        	xSpeed*=-1;
        	changeColor();
        }
        if(ULy<=0||ULy>=HEIGHT-90) {
        	ySpeed*=-1;
        	changeColor();
        }
//        if(ULx<=0 || ULy<=0 || ULx>=WIDTH-65||ULy>=HEIGHT-90) {
//        	// if beyond any sides change the speeds
//        	int deg=gen2.nextInt(360);
//        	while ((int)(5*Math.cos(deg))+ULx<=0|| (int)(5*Math.sin(deg))+ULy<=0||(int)(5*Math.sin(deg))>=HEIGHT-90||
//(int)(5*Math.cos(deg)+ULx)>=WIDTH-65){
//            	deg = gen2.nextInt(360);
//			}
//            xSpeed = (int)(5*Math.cos(deg));
//            ySpeed = (int)(5*Math.sin(deg));
//        }
    }

    public static void main(String[] args) throws Exception {
        new BouncingBallSS();
    }

}  //end of BouncingBallSS class