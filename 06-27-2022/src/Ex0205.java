/* finished, done fairly easily
 * tic tac toe with 26 players
 * basic idea: one winners is super ez, 2 winners is 2 are equal and the third is not
 */
import java.util.*;
public class Ex0205 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][] board = new char[3][3];
		for(int r=0;r<3;r++) {
			board[r]=in.next().toCharArray();
		}
		System.out.println(oneWinners(board));
		System.out.println(twoWinners(board));
		in.close();
	}
	
	public static int oneWinners(char[][] board) {
		// returns # of 3-in-rows with only 1 contributor 
		int wins=0;
			// check rows
			for(int r=0;r<3;r++) {
				if(board[r][0]==board[r][1] && board[r][2]==board[r][0]) wins++;
			}
			// check cols
			for(int c=0;c<3;c++) {
				if(board[1][c]==board[0][c] && board[2][c]==board[0][c]) wins++;
			}
			// check diagonals
			if(board[1][1]==board[0][0] && board[2][2]==board[0][0]) wins++;
			if(board[1][1]==board[0][2] && board[2][0]==board[0][2]) wins++;
			return wins;
	}
	public static int twoWinners(char[][] board) {
		// returns # of 3-in-a-rows with 2 contributors
		int wins=0;
		
		// logic here:
		// if a cow only gets 2 parts of a row and not the 3rd then wins++
		// check rows
		for(int r=0;r<3;r++) {
			if(board[r][0]==board[r][1] && board[r][2]!=board[r][0]) wins++; // r2 is different
			else if(board[r][0]!=board[r][1] && board[r][2]==board[r][0]) wins++; // r1 is different
			else if(board[r][0]!=board[r][1] && board[r][2]==board[r][1]) wins++; // r0 is different
		}
		// check cols
		for(int c=0;c<3;c++) {
			if(board[1][c]==board[0][c] && board[2][c]!=board[0][c]) wins++; // 2c is dif
			else if(board[1][c]!=board[0][c] && board[2][c]==board[0][c]) wins++; // 1c is dif
			else if(board[1][c]==board[2][c] && board[2][c]!=board[0][c]) wins++; // 0c is dif
		}
		// check diagonals
		if(board[1][1]==board[0][0] && board[2][2]!=board[0][0]) wins++; // 22 is dif
		else if(board[1][1]!=board[0][0] && board[2][2]==board[0][0]) wins++; // 11 is dif
		else if(board[1][1]==board[2][2] && board[2][2]!=board[0][0]) wins++; // 00 is dif
		
		if(board[1][1]==board[0][2] && board[2][0]!=board[0][2]) wins++; // 20 is dif
		else if(board[1][1]!=board[0][2] && board[2][0]==board[0][2]) wins++; // 11 is dif
		else if(board[1][1]==board[2][0] && board[2][0]!=board[0][2]) wins++; // 02 is dif
		return wins;
	}
}
