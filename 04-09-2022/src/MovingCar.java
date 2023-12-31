import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MovingCar extends JFrame {
    int WIDTH = 600,  HEIGHT = 300;
    int ULx = 100,  ULy = 100;

    int xSpeed = 6;
    int ySpeed = 0;

    int UPDATE_PERIOD = 50; 
    
    BufferedImage car;

    public MovingCar() throws Exception {
        CarPanel p = new CarPanel();
        add(p);
        setSize(WIDTH, HEIGHT);
        setTitle("Moving Car1");
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
        
        car = ImageIO.read(new File("car.jpg"));
    }

    private class CarPanel extends JPanel {
        public void paintComponent(Graphics g) {
            drawStaticCar(ULx, ULy, g);
        }
    }

    public void drawStaticCar(int x, int y, Graphics g) {
//    	g.setColor(Color.green);
//    	g.fillRect(x, y, 100, 50);
//    	g.setColor(Color.blue);
//    	g.fillOval(x, y+50, 25, 25);
//    	g.fillOval(x+75, y+50, 25, 25);
    	g.drawImage(car, x, y, 100,50,this);


    }

    public void updatePosition() {
        ULx += xSpeed;
        ULy += ySpeed;
        if(ULx > WIDTH ) {
            ULx = 0;
        }
        if(ULy > HEIGHT) {
            ULy = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        new MovingCar();
    }

}  //end of MovingCar class