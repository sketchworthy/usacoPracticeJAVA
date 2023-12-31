import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class BouncingBall extends JFrame {
    int WIDTH = 600,  HEIGHT = 400; // offset 15 for width, 40 for height
    int ULx = 0,  ULy = 0;

    int xSpeed = 5;
    int ySpeed = 0;

    int UPDATE_PERIOD = 50; 
    
//    BufferedImage car;

    public BouncingBall() throws Exception {
        BallPanel p = new BallPanel();
        add(p);
        setSize(WIDTH, HEIGHT);
        setTitle("Corner Ball Bounce");
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
        
//        car = ImageIO.read(new File("car.jpg"));
    }

    private class BallPanel extends JPanel {
        public void paintComponent(Graphics g) {
            drawStaticBall(ULx, ULy, g);
        }
    }

    public void drawStaticBall(int x, int y, Graphics g) {
    	g.setColor(Color.green);
    	g.fillOval(x, y, 25, 25);


    }

    public void updatePosition() {
        ULx += xSpeed;
        ULy += ySpeed;
        if(ULx == WIDTH-40 && ULy==0) { // if touching NE corner
        	// start moving down
            xSpeed=0;
            ySpeed=5;
        }
        if(ULx == WIDTH-40&&ULy==HEIGHT-65) { // if touching SE corner
        	// start moving left
            xSpeed=-5;
            ySpeed=0;
        }
        if(ULx == 0 && ULy==HEIGHT-65) { // if touching SW corner
        	// start moving up
            xSpeed=0;
            ySpeed=-5;
        }
        if(ULx == 0 &&ULy==0) { // if touching NW corner
        	// start moving right
            xSpeed=5;
            ySpeed=0;
        }
    }

    public static void main(String[] args) throws Exception {
        new BouncingBall();
    }

}  //end of BouncingBall class