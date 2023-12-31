/*
 * The game can start. The tank and the invaders can shoot. 
 * There is no level information. The tank bullet can penetrate
 * the shields -- unrealistic. There is only one tank bullet 
 * at any time in the game window.
 */
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SpaceInvader2 extends JFrame {
	int panelWidth = 800;
	int panelHeight = 520;

	MyPanel p;
	
	SpaceInvader2() {
		p = new MyPanel();
		add(p);
		
		p.setPreferredSize(new Dimension(panelWidth, panelHeight));
		p.setFocusable(true);
		p.requestFocusInWindow();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setTitle("Space Invader II");
		setVisible(true);
		setResizable(false);
		pack();
		
		ActionListener updateTask = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.moveInvaders();
				repaint();
			}
		};

		javax.swing.Timer t = new javax.swing.Timer(50, updateTask);
		t.start();
		
		setLocation(50, 50);
	}

	private class MyPanel extends JPanel {
		int nrows, ncols;         //rows and columns of invaders
		Invader[][] invaders;     //the invaders

		Shield[] shields;		  //the shields
		Prime tank;				  //the tank fighter
		
		Prime invaderBullet;       //bullet from invader
		Prime tankBullet;          //bullet from tank
		
		BufferedImage invaderImage1, invaderImage2, invaderImage3;
		BufferedImage shieldImage, tankImage, explosionImage;

		int shieldImageWidth;     //preferred dimension of shield
		int shieldImageHeight;
		int maxShieldLife;        //maximum hits a shield can tolerate

		Font font;

		int score, highestScore;
		boolean gameOver;
		
		MyPanel() {
			initPanel();
			
			addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {processKeyPressed(e);}

				public void keyReleased(KeyEvent arg0) { }

				public void keyTyped(KeyEvent arg0) { }
			});
		}
		
		public void initPanel() {
			setupStartingValues();
			loadImages();

			setUpInvaders();
			setUpShields();
			setUpTank();
			setUpFont();
			
			setupInvaderBullet();
			setupTankBullet();
			
			//add all points from invaders to highestScore
			highestScore = 0;
			for(int i=0; i<nrows; i++) {
				highestScore += invaders[i][0].getPoints() * ncols; 
			}
		}

		//Set up the starting values of the parameters
		public void setupStartingValues() {
			nrows = 5;
			ncols = 11;
			
			shieldImageWidth = 50;
			shieldImageHeight = 50;
			maxShieldLife = 6;

			score = 0;
			gameOver = false;
		}
		
		public void loadImages() {
			try {
				invaderImage1 = ImageIO.read(new File("images/invader1.bmp"));
				invaderImage2 = ImageIO.read(new File("images/invader2.bmp"));
				invaderImage3 = ImageIO.read(new File("images/invader3.bmp"));
				shieldImage = ImageIO.read(new File("images/shield.bmp"));
				tankImage = ImageIO.read(new File("images/tank.bmp"));
				explosionImage = ImageIO.read(new File("images/explosion.bmp"));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	
		public void setUpInvaders() {
			invaders = new Invader[nrows][ncols];

			for(int r=0; r<nrows; r++) {
				for(int j=0; j<ncols; j++) {
					invaders[r][j] = new Invader(50+j*50, 80 + r*30);
					invaders[r][j].setPoints(200 - (r+1)/2*50);
					invaders[r][j].setSpeedX(2);
					invaders[r][j].setSpeedY(1);

					if(r==0)
						invaders[r][j].setImage(invaderImage1);
					else if(r==1 || r==2 )
						invaders[r][j].setImage(invaderImage2);
					else
						invaders[r][j].setImage(invaderImage3);
				}
			}
		}

		public void setUpShields() {
			shields = new Shield[4];

			for(int j=0; j<4; j++) {
				shields[j] = new Shield(50+j*200, 400);
				shields[j].setImage(shieldImage);
				shields[j].setLife(maxShieldLife);
			}
		}

		public void setUpTank() {
			tank = new Prime(375, 480);
			tank.setImage(tankImage);
			tank.setSpeedX(20);
		}

		public void setupInvaderBullet() {
			invaderBullet = new Prime();
			invaderBullet.setVisible(false);
			invaderBullet.setSpeedY(15);
		}
		
		public void setupTankBullet() {
			tankBullet = new Prime();
			tankBullet.setVisible(false);
			tankBullet.setSpeedY(-20);
		}
		
		public void setUpFont() {
			font = new Font("Arial", Font.PLAIN, 32);
		}
	
		public void paintComponent(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, panelWidth, panelHeight);
			
			gameOver = checkForGameOver();
			if(gameOver) {
				g.setFont(font);
				
				if(score == highestScore) {
					g.setColor(Color.RED);
					g.drawString("You've conquered SpaceInvader!", 180, 150);
					g.drawString("Score: " + score, 180, 250);
					g.drawString("Press 'r' to replay the game.", 180, 350);
				}
				
				else {
					g.setColor(Color.WHITE);
					g.drawString("Score: " + score, 180, 250);
					g.drawString("Press 'r' to replay the game.", 180, 350);
				}    
				return;
			}
	  
			showLabels(g);
			showInvaders(g);
			showShields(g);
			showTank(g);

			showInvaderBullet(g);
			showTankBullet(g);
		}
		
		public void showLabels(Graphics g) {
			g.setColor(Color.RED);
			g.setFont(font);
			g.drawString("Space Invaders", 280, 40);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			g.drawString("Score: " + score, 630, 40);
//			g.drawString("Lives: "+ , , );
		}

		public void showInvaders(Graphics g) {
			Invader enemy;

			for(int row=nrows-1; row>=0; row--) {
				for(int col=0; col<ncols; col++) {
					enemy = invaders[row][col];
					if(enemy.isVisible()) {
						if(checkForExplosion(enemy))
							explode(enemy, g);
						else
							enemy.paint(g, 40, 30);
					}
				}
			}
		}

		public void showTank(Graphics g) {
			tank.paint(g);
		}
	
		public void showShields(Graphics g) {
			for(int i=0; i<4; i++) {
				shields[i].paint(g, shieldImageWidth, shieldImageHeight);
				
			}
		}

		//update the positions of all invaders
		public void moveInvaders() {

			//TBI: move the invaders (if boundary is touched, bounce back)
			//  We can get the speeds from invaders[0][0].getSpeedX(), 
			//  and invaders[0][0].getSpeedY()
			
			// check for leftmost+rightmost column still alive
			int leftmost = -1;
			for(int r=0;r<nrows;r++) {
				for(int c=0;c<ncols;c++) {
					if(invaders[r][c].isVisible()) {
						leftmost=c; break;
					}
				}
			}
			int rightmost = -1;
			for(int r=0;r<nrows;r++) {
				for(int c=ncols-1;c>=0;c--) {
					if(invaders[r][c].isVisible()) {
						rightmost=c; break;
					}
				}
			}
			
			
			for(int r=0;r<nrows;r++) {
				for(int c=0;c<ncols;c++) {
					if(invaders[r][rightmost].getSpeedX()+invaders[r][rightmost].getX()>panelWidth-40
							||invaders[r][leftmost].getSpeedX()+invaders[r][leftmost].getX()<0) {
						invaders[r][c].setSpeedX(invaders[r][c].getSpeedX()*(-1));
					}
					invaders[r][c].moveX(invaders[r][c].getSpeedX());
				}
			}

			
			
			
			
			//let one invader shoot
			int tmp = (int) (Math.random() * 2);
			if(tmp==0)
				oneInvaderShoots();
		}

		//show tank bullet if it is within window.
		public void showTankBullet(Graphics g) {
			if(!tankBullet.isVisible()) return; //bullet dies after hitting an invader

			tankBullet.move();

			//if the invaderBullet is off the screen
			if(tankBullet.getY() <= 0) {
				tankBullet.setVisible(false);
			}
			else {
				g.setColor(Color.RED);
				g.fillOval(tankBullet.getX(), tankBullet.getY(), 10, 10);
			}
		}
	
		//Whether tank bullet hits invader
		public boolean checkForExplosion(Invader enemy) {
			if(!enemy.isVisible())
				return false;
			
			int x0 = enemy.getX();
			int y0 = enemy.getY();
	
			int w = enemy.getImage().getWidth();
			int h = enemy.getImage().getHeight();
	
			if(tankBullet.getX() >= x0 && tankBullet.getX() <= x0 + w && 
					tankBullet.getY() >= y0 && tankBullet.getY() <= y0 + h) {
				return true;			
			}
			return false;
		}
	
		//tank bullet hits invader; display explosion; set invader to die; increase score
		public void explode(Invader enemy, Graphics g) {
			g.drawImage(explosionImage, enemy.getX(), enemy.getY(), null);
			enemy.destroy();
			score += enemy.getPoints();
			tankBullet.destroy();
		}
	
		//one invader will shoot
		public void oneInvaderShoots() {
			if(!invaderBullet.isVisible()) {
				int col = (int) (Math.random() * ncols);
				for(int row=nrows-1; row>=0; row--) {
					Invader enemy = invaders[row][col];
					if(enemy.isVisible()) {
						invaderBullet.setX(enemy.getX() + enemy.getImage().getWidth()/2);
						invaderBullet.setY(enemy.getY() + enemy.getImage().getHeight());

						invaderBullet.setVisible(true);
						return;
					}
				}
			}
		}
	
		public void showInvaderBullet(Graphics g) {
			if(invaderBullet.isVisible()) {
				g.setColor(Color.WHITE);
				g.fillOval(invaderBullet.getX(), invaderBullet.getY(), 10, 10);
				
				invaderBullet.move();
				
				//if the invaderBullet is off the screen
				if(invaderBullet.getY() > panelHeight-10) {
					invaderBullet.setVisible(false);
				}
				
				//if invaderBullet hits the shield
				int x1 = invaderBullet.getX(), x2 = x1 + 10;
				int y1 = invaderBullet.getY(), y2 = y1 + 10;
				for(int i=0; i<4; i++) {
					int x3 = shields[i].getX(), x4 = x3 + shieldImageWidth;
					int y3 = shields[i].getY(), y4 = y3 + shieldImageHeight;
					if(shields[i].isVisible() &&
							Math.min(x2, x4) > Math.max(x1, x3) &&
							Math.min(y2, y4) > Math.max(y1, y3)) {
							
						invaderBullet.setVisible(false);
						shields[i].decreaseLife();
						break;
					}
				}
			}
		}
		
		//If all invaders are destroyed or the tank is destroyed, return true.
		public boolean checkForGameOver() {
			if(score == highestScore)
				return true;
	
			if(invaderBullet.getX() >= tank.getX()
					&& invaderBullet.getX() <= tank.getX() + tankImage.getWidth()
					&& invaderBullet.getY() >= tank.getY()
					&& invaderBullet.getY() <= tank.getY() + tankImage.getHeight())
				return true;

			for(int row=nrows-1; row>=0; row--) {
				for(int col=0; col<ncols; col++) {
					Invader enemy = invaders[row][col];
					if(enemy.getY() >= shields[0].getY()-24 && enemy.isVisible())
						return true;
				}
			}

			return false;
		}

		public void processKeyPressed(KeyEvent e) {
			int kc = e.getKeyCode();
			
			if(gameOver) {  //if game is over, only accept 'r'
				if(kc == KeyEvent.VK_R)
					initPanel();
			}
			
			else { //game is not over yet
				if(kc == KeyEvent.VK_SPACE) { //space key to fire
					if(!tankBullet.isVisible()) {
						tankBullet.setX( tank.getX() + tankImage.getWidth()/2 );
						tankBullet.setY( tank.getY() ); 
						tankBullet.setVisible(true);
					}
				}
		
				else if(kc == KeyEvent.VK_RIGHT && tank.getX() < panelWidth-50) {
					tank.moveX(20);
				}
		
				else if (kc == KeyEvent.VK_LEFT && tank.getX() >= 20) {
					tank.moveX(-20);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new SpaceInvader2();
	}
}