/* finished, done easily
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board
position during the course of a valid tic-tac-toe game.
basic idea: come up with a list of conditions that makes a position false, go through and implement/account
for each of these conditions to be false. otherwise true
 */
import java.util.*;
public class Ex0202 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][] board = new char[3][3];
		for(int j=0;j<3;j++) {
			String str = in.next();
			board[j]=str.toCharArray();
		}
		int xCnt=0;
		int oCnt=0;
		for(int j=0;j<3;j++) {
			for(int k=0;k<3;k++) {
				if(board[j][k]=='X') xCnt++;
				else if(board[j][k]=='O') oCnt++;
			}
		}
		if(oCnt>xCnt) {
			System.out.println("FALSE");
		}
		else if(xCnt-oCnt>1) {
			System.out.println("FALSE");
		}
		else if (xWon(board)==true){
			if(oWon(board)==true) System.out.println("FALSE");
			else if(xCnt==oCnt) System.out.println("FALSE");
			else System.out.println("TRUE");
		}
		else System.out.println("TRUE");
		in.close();
	}
	public static boolean xWon(char[][] board) {
		// checks for a 3-in-row with X's
		// check rows
		for(int r=0;r<3;r++) {
			if(board[r][0]=='X' && board[r][1]=='X' && board[r][2]=='X') return true;
		}
		// check cols
		for(int c=0;c<3;c++) {
			if(board[0][c]=='X' && board[1][c]=='X' && board[2][c]=='X') return true;
		}
		// check diagonals
		if(board[0][0]=='X' && board[1][1]=='X' && board[2][2]=='X') return true;
		if(board[0][2]=='X' && board[1][1]=='X' && board[2][0]=='X') return true;
		return false;
	}
	public static boolean oWon(char[][] board) {
		// checks for a 3-in-row with O's
				// check rows
				for(int r=0;r<3;r++) {
					if(board[r][0]=='O' && board[r][1]=='O' && board[r][2]=='O') return true;
				}
				// check cols
				for(int c=0;c<3;c++) {
					if(board[0][c]=='O' && board[1][c]=='O' && board[2][c]=='O') return true;
				}
				// check diagonals
				if(board[0][0]=='O' && board[1][1]=='O' && board[2][2]=='O') return true;
				if(board[0][2]=='O' && board[1][1]=='O' && board[2][0]=='O') return true;
				return false;
	}
}

/* What makes a position false:
* more O's than X's
* (more than 1) more X's than O's
* both players have 3-in-a-row
* X has 3-in-a-row and X and O have same # of counters
*/




