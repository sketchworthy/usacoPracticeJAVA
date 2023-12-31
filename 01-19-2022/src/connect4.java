/*
 * Implement the game Connect 4 in console mode.
 */
import java.util.Scanner;
public class connect4 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("How many rows?");
		int nrows = kb.nextInt();
		System.out.println("How many columns?");
		int ncols = kb.nextInt();
		int[][] board = new int[nrows][ncols]; //int[][] board = new int[6][7];
		int st=0; // status
		int player = 1; // player 1 goes first
		while(true) {
			showBoard(board,nrows,ncols);
			System.out.print("Player "+player+", which column? "); // not index
			int col = kb.nextInt()-1;
			
			if(col<0||col>nrows) continue; // skip rest of current iteration
			
			// add piece to col
			int row=nrows-1; // start from bottom
			for(;row>=0;row--) {
				if(board[row][col]==0) { // go for first available spot
					break;
				}
			}
			
			if (row<0) continue; // skip rest of current iteration
			board[row][col]=player;
			st = checkStatus(board,nrows,ncols);
			if(st>0) break;
			player = 3-player;
		}
		showBoard(board,nrows,ncols);
		if(st==1 || st==2)
			System.out.println("Player "+st+" wins!");
		else if (st==3){ // if tie
			System.out.println("Game tied.");
		}
		kb.close();
	}
	// 0: playing, 1: player 1 wins, 2: player 2 wins, 3: tie
	static int checkStatus(int[][] board,int nrows,int ncols) {
		for (int r=0;r<nrows;r++){ // check rows
			for (int c=0; c<ncols-3;c++){
				if(board[r][c]!=0&&board[r][c]==board[r][c+1]&&board[r][c]==board[r][c+2]
						&&board[r][c]==board[r][c+3]) {
					return board[r][c];
				}
			}
		}
		for (int c=0;c<ncols;c++) { // check cols
			for (int r=0;r<nrows-3;r++) {
				if(board[r][c]!=0&&board[r][c]==board[r+1][c]&&board[r][c]==board[r+2][c]
						&&board[r][c]==board[r+3][c]) {
					return board[r][c];
				}
			}
		}
		for (int r=0;r<nrows-3;r++) { // check SE diagonal
			for(int c=0;c<ncols-3;c++) {
				if(board[r][c]!=0&&board[r][c]==board[r+1][c-1]&&board[r][c]==board[r+2][c-2]
						&&board[r][c]==board[r+3][c-3]) {
					return board[r][c];
				}
			}
		}
		for (int r=0;r<nrows-3;r++) { // check SW diagonal
			for(int c=0;c<ncols-3;c++) {
				if(board[r][c]!=0&&board[r][c]==board[r+1][c+1]&&board[r][c]==board[r+2][c+2]
						&&board[r][c]==board[r+3][c+3]) {
					return board[r][c];
				}
			}
		}
		// check tie
		boolean empty = false; // no more empty space
		for(int r=0;r<nrows;r++) {
			for(int c=0;c<ncols;c++) {
				if(board[r][c]==0)
					empty=true;
			}
		}
		if (empty==false) return 3;
		
		return 0;
	}
		
	static void showBoard(int[][] board, int nrows, int ncols) {
		System.out.println();
		for(int r=0;r<nrows;r++) {
			for (int c=0;c<ncols;c++) {
				if(board[r][c]>0) {
					System.out.print("| "+board[r][c]+" ");
				}
				else
					System.out.print("|   ");
			}
			System.out.println("|");
		}
	}
}
