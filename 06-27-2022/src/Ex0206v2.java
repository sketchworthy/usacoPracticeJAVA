/* finished, done ez
 * basic idea: 2d freq array sized 1001x1001 keeping track of neighbors,
 * keep track of cows using another array
 */
import java.util.*;

public class Ex0206v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] board = new int[1001][1001];
		int[][] cowCoords = new int[n][2];
		
		for(int j=0;j<n;j++) { // for cow added
			cowCoords[j][0]=in.nextInt(); // x
			cowCoords[j][1]=in.nextInt(); // y
			// add 1 to all adjacent vertices
			if(cowCoords[j][0]>0)
				board[cowCoords[j][0]-1][cowCoords[j][1]]+=1;
			if(cowCoords[j][0]<1000)
				board[cowCoords[j][0]+1][cowCoords[j][1]]+=1;
			if(cowCoords[j][1]>0)
				board[cowCoords[j][0]][cowCoords[j][1]-1]+=1;
			if(cowCoords[j][1]<1000)
			board[cowCoords[j][0]][cowCoords[j][1]+1]+=1;
			// check each cow if it has 3 neighboring cows on board
			int comfCnt = 0; // # of comfortable cows
			for(int k=0;k<=j;k++) {
				if(board[cowCoords[k][0]][cowCoords[k][1]]==3) comfCnt++;
			}
			System.out.println(comfCnt);
		}
		
		in.close();
	}
}
