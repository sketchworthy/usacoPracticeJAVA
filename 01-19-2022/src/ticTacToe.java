import java.util.Scanner;
public class ticTacToe {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		char[][] board = new char[3][3]; // make game board
		for (int r=0;r<3;r++) {
			for (int c=0;c<3;c++) {
				board[r][c]='-';
			}
		}
		
		// run loop until winner emerges or board is full
		int player = 1; // 1='X'; 2='O'
		while(true) {
			showBoard(board);
			System.out.print("Player" + player + "'s row & col index (each 0 to 2): ");
			int r= kb.nextInt();
			int c= kb.nextInt();
			
			if(board[r][c]=='-') {
				if(player==1)
					board[r][c]='X';
				else
					board[r][c]='O';
				
				int st = checkStatus(board); // 0: play, 1: player 1 wins, 
				                             // 2: player 2 wins, 3:tie
				if(st==1 || st==2) { // if winner
					System.out.println("Player " + player + " wins!");
					break;
				}
				else if(st==3) {
					System.out.println("Game tied.");
					break;
				}
				else { // st is 0
					player=3-player; // change the player turn
				}
			}
		}
		
		kb.close();
		showBoard(board);
		System.out.println("Thanks for playing!");
	}
	
	static void showBoard(char[][] board) {
		System.out.println();
		for(int r=0;r<board.length;r++) {
			for (int c=0;c<board[r].length;c++) {
				System.out.print(board[r][c]+" ");
			}
			System.out.println();
		}
	}
	static int checkStatus(char[][] board) {
		// check rows
		for (int r=0;r<3;r++) {
			if(board[r][0]!='-'&&board[r][0]==board[r][1]&&board[r][0]==board[r][2]) {
				if(board[r][0]=='X')
					return 1;
				else
					return 2;
			}
		}
		// check cols
		for (int c=0;c<3;c++) {
			if(board[0][c]!='-'&&board[0][c]==board[1][c]&&board[0][c]==board[2][c]) {
				if(board[0][c]=='X')
					return 1;
				else
					return 2;
			}
		}
		// check diagonals
		if(board[0][0]==board[1][1]&&board[0][0]==board[2][2]) { // NW-SE
			if(board[0][0]=='X')
				return 1;
			else if(board[0][0]=='O')
				return 2;
		}
		if(board[0][2]==board[1][1]&&board[2][0]==board[1][1]) { // SW-NE
			if(board[1][1]=='X')
				return 1;
			else if(board[0][0]=='O')
				return 2;
		}
		
		// check if there is any '-'
		boolean empty = false;
		for(int r=0;r<3;r++) {
			for(int c=0;c<3;c++) {
				if (board[r][c]=='-'){
					empty=true;
				}
			}
		}
		if (empty==false) {
			return 3; // tie
		}
		
		return 0; // all that false? keep going
	}
}
