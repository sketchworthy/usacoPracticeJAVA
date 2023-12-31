/*
 * This program adds the graphics user interface to play Connect4.
 * The game board consists of 6x7 circles.
 * To add a piece to a column, click somewhere in the column.
 * If there is a winner or tie, a message will pop up.
 * The game can also be reset at any time.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Connect4GUI1 extends JFrame {
	static int width = 550;
	static int height = 450;

	BoardPanel  p1;   //game board
	JPanel      p2;   //reset button
	
	Connect4GUI1() {
		//game board
		p1 = new BoardPanel();
		p1.setPreferredSize(new Dimension(width, height));

		//reset button
		p2 = new JPanel();
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Courier New", Font.BOLD, 20));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.reset();
				repaint();
			}
		});
		p2.add(reset);
		p2.setBackground(Color.WHITE);

		//the JFrame adds panels in vertical direction
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(p1);
		add(p2);
		
		setTitle("Connect Four");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();

		//put the JFrame at location (50, 50)
		setLocation(50, 50);
	}

	//game board Panel
	class BoardPanel extends JPanel {
		int status;      //status of game
		int RED = 1;     //red wins
		int BLACK = 2;   //black wins
		int TIE = 3;     //game tied
		int PLAYING = 0; //still playing

		int nrows = 6;
		int ncols = 7;
		
		int diameter;   //diameter of a piece (circle)
		int radius;

		int leftMargin;
		int topMargin;
		
		int[][] board;

		int currentPlayer;  //either 1 or 2
		int currentRowId;   //the most recent piece added
		int currentColId;
	
		BoardPanel() {
			initPanel();

			addMouseListener(new MouseListener() { // mouse action!!
				public void mouseClicked(MouseEvent arg0) { }
				public void mouseEntered(MouseEvent arg0) { }
				public void mouseExited(MouseEvent arg0) { }

				public void mousePressed(MouseEvent e) {
					int x = e.getX();
					int col = (x - leftMargin) / diameter;
					if(col>=0 && col<ncols)  {
						addPiece(col);
					}
				}

				public void mouseReleased(MouseEvent arg0) { }
			});			
		}

		//set the parameters
		public void initPanel() {
			currentPlayer = 1;   //player 1 starts first
			
			currentRowId = -1;
			currentColId = -1;
			
			board = new int[nrows][ncols];
			
			diameter = Math.min(70, Math.min(width/ncols, height/nrows));
			radius = diameter / 2;
			
			leftMargin = (int) (width/2 - ncols*radius + 2);
			topMargin = 20;

			status = PLAYING;
		}
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);

			//TBI: display the board
			//     show pieces of the two players with red and black circles
			//     indicate the last added piece with a white dot at the center

			for(int r=0;r<nrows;r++) {
				for(int c=0;c<ncols;c++) {
					if(board[r][c]==0) {
						g.setColor(Color.LIGHT_GRAY);
					}
					else if(board[r][c]==1) g.setColor(Color.RED);
					else g.setColor(Color.BLACK);
					g.fillOval(leftMargin+c*diameter, topMargin+r*diameter, diameter-4, diameter-4);
				}
			}
			// mark piece just added
			g.setColor(Color.white);
			int difference=50;
			g.fillOval(leftMargin+currentColId*diameter+difference/2, topMargin+currentRowId*diameter+difference/2, diameter-difference, diameter-difference);

		}
		
		//add one piece to the specified column
		public void addPiece(int col) {
			if(status==PLAYING) {
				currentColId=col;
				for(int r=nrows-1;r>=0;r--) {
					if(board[r][col]==0) {
						board[r][col]=currentPlayer;
						currentRowId=r;
						break;
	//					return;
					}
				}
			}
			repaint();
			updateStatus();
			if(status==PLAYING) {
				currentPlayer=3-currentPlayer;
			}
			else if(status==1||status==2) {
				JOptionPane.showMessageDialog(getParent(), "Player "+status+" wins! Congratulations!");
			}
//			//TBI
//			for(int r=0;r<nrows;r++) {
//				for(int c=0;c<ncols;c++) {
//					System.out.print(board[r][c]+" ");
//				}
//				System.out.println();
//			}
		}
		
		//reset the game board
		public void reset() {
			currentPlayer = 1;
			currentRowId = -1;
			currentColId = -1;
			
			for(int r=0; r<nrows; r++) {
				for(int c=0; c<ncols; c++)
					board[r][c] = 0;
			}
			status = PLAYING;
		}
		
		//check the status of the board: red win; black win; draw; keep playing
		public void updateStatus() {
			boolean boardFull = true;
			for(int r=0; r<nrows; r++) {
				for(int c=0; c<ncols; c++) {
					if(board[r][c]==0) {
						boardFull = false;
						continue;
					}
					
					//east
					if(c+3<ncols && board[r][c+1]==board[r][c]	&& 
							board[r][c+2]==board[r][c] && board[r][c+3]==board[r][c]) {
						status = board[r][c];
						return;
					}
				
					//south-east
					if(c+3<ncols && r+3<nrows && board[r+1][c+1]==board[r][c] && 
							board[r+2][c+2]==board[r][c] && board[r+3][c+3]==board[r][c]) {
						status = board[r][c];
						return;
					}

					//south
					if(r+3<nrows && board[r+1][c]==board[r][c] && 
							board[r+2][c]==board[r][c] && board[r+3][c]==board[r][c]) {
						status = board[r][c];
						return;
					}

					//south-west
					if(c-3>=0 && r+3<nrows && board[r+1][c-1]==board[r][c] && 
							board[r+2][c-2]==board[r][c] && board[r+3][c-3]==board[r][c]) {
						status = board[r][c];
						return;
					}

				}
			}
			
			
			//no winner yet
			if(boardFull)
				status = TIE;      //tie
			else
				status = PLAYING;  //keep playing
		}			
	}
	
	public static void main(String[] args) {
		new Connect4GUI1();
	}
}