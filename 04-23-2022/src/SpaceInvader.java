/*
 * The game can start. The tank and the invaders don't move or shoot.
 * There is no level information.
 */
import java.awt.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SpaceInvader extends JFrame {
	int panelWidth = 800;
	int panelHeight = 520;

	MyPanel p;
	
	SpaceInvader() {
		p = new MyPanel();
		p.setPreferredSize(new Dimension(panelWidth, panelHeight));
		p.setFocusable(true);
		p.requestFocusInWindow();
		add(p);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBackground(Color.BLACK);
		setTitle("Space Invader");
		setVisible(true);
		setResizable(false);
		pack();
		
		setLocation(50, 50);
	}

	@SuppressWarnings("unused")
	private class MyPanel extends JPanel {
		int nrows, ncols;         //dimensions of invaders
		Invader[][] invaders;     //the invaders

		Shield[] shields;		  //the shields
		Prime tank;				  //the tank fighter
		
		BufferedImage[] invaderImages;
		BufferedImage shieldImage, tankImage;

		int shieldImageWidth;     //preferred dimension of shield
		int shieldImageHeight;
		int maxShieldLife;        //maximum hits a shield can tolerate

		Font font;

		MyPanel() {
			initPanel();
		}
		
		public void initPanel() {
			setupStartingValues();
			loadImages();
			
			setUpInvaders();
			setUpShields();
			setUpTank();
			setUpFont();
		}
		
		//Set up the starting values of the parameters
		public void setupStartingValues() {
			nrows = 5;
			ncols = 11;
			
			shieldImageWidth = 50;
			shieldImageHeight = 50;
			maxShieldLife = 6;
		}

		//load images for invaders, shield, and tank
		public void loadImages() {
			invaderImages = new BufferedImage[3];

			try { // try+catch alternative to throws exception
				invaderImages[0] = ImageIO.read(new File("images/invader1.bmp"));
				invaderImages[1] = ImageIO.read(new File("images/invader2.bmp"));
				invaderImages[2] = ImageIO.read(new File("images/invader3.bmp"));
				shieldImage = ImageIO.read(new File("images/shield.bmp"));
				tankImage = ImageIO.read(new File("images/tank.bmp"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		public void setUpInvaders() {
			invaders = new Invader[nrows][ncols];
			for(int r=0; r<nrows; r++)
				for(int c=0; c<ncols; c++)
					invaders[r][c] = new Invader();

			//TBI
			//set up the locations, images, points, speedX, speedY of the invaders
			//Example:
//			invaders[0][0].setLocation(50, 50);
//			invaders[0][0].setImage(invaderImages[0]);
			for(int r=0;r<nrows;r++) {
				for(int c=0;c<ncols;c++) {
					invaders[r][c].setLocation((c+2)*shieldImageWidth, (r+1)*shieldImageHeight);
					invaders[r][c].setSpeedX(20);
					invaders[r][c].setSpeedY(0);
					if(r==0) {
						invaders[r][c].setImage(invaderImages[0]);
					}
					else if(r<3) {
						invaders[r][c].setImage(invaderImages[1]);
					}
					else {
						invaders[r][c].setImage(invaderImages[2]);
					}
				}
			}
			
			
			
		
		}

		public void setUpShields() {
			shields = new Shield[4];
			for(int j=0; j<4; j++)
				shields[j] = new Shield();

			//TBI: set up the locations, images, and shieldLife of the shields
			//Example:
//			shields[0].setLocation(75, 400);
//			shields[0].setImage(shieldImage);
			for(int j=0;j<4;j++) {
				shields[j].setLocation(100+j*175,400);
				shields[j].setImage(shieldImage);
				shields[j].setLife(maxShieldLife);
			}

			
			
			
		}

		public void setUpTank() {
			tank = new Prime();

			//TBI: set up the location, image, and speedX of the tank

			tank.setLocation(panelWidth/2-shieldImageWidth, panelHeight-shieldImageHeight);
			tank.setImage(tankImage);
			tank.setSpeedX(50);
		}
		
		public void setUpFont() {
			font = new Font("Arial", Font.PLAIN, 32);
		}
	
		public void paintComponent(Graphics g) {
			showLabels(g);
			showInvaders(g);
			showShields(g);
			showTank(g);
		}
		
		public void showLabels(Graphics g) {
			g.setColor(Color.RED);
			g.setFont(font);
			g.drawString("Space Invaders", 280, 40);
		}

		public void showInvaders(Graphics g) {
			for(int row=nrows-1; row>=0; row--) {
				for(int col=0; col<ncols; col++) {
					invaders[row][col].paint(g);
				}
			}
		}

		public void showTank(Graphics g) {
			tank.paint(g);
		}
	
		public void showShields(Graphics g) {
			for(int i=0; i<4; i++)
				shields[i].paint(g, shieldImageWidth, shieldImageHeight);
		}
	}
	
	public static void main(String[] args) {
		new SpaceInvader();
	}
}