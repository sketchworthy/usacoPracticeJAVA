/* kinda hard to think of an idea for, ok to implement
 * basic idea: use a direction variable and loop through it, use a visited boolean array
 * and use a while loop to keep going until you hit a border or a visited, and then turn
 * and keep going that way
 */
import java.util.*;
public class Ex0901 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int dir = 0; // right = 0, down = 1, left = 2, up = 3
		int[][] A = new int[m][n];
		boolean[][] visited = new boolean[m][n];
		for(int j=0;j<m;j++) {
			for(int k=0;k<n;k++) {
				A[j][k]=in.nextInt();
			}
		}
		
		// start at top left corner, direction right
		int cR = 0; int cC = 0; // current row and current column
		int nR = 0; int nC = 0; // next row and next col
		int cnt=m*n;
//		int cnt = 10;
		System.out.print("1 "); visited[0][0]=true;
		while(cnt>1) {
			nR=cR; nC=cC;
//			System.out.println("CR: "+cR+" CN: "+cC+" dir: "+dir);
			// head in the direction and change stuff you pass to true in visited
			if(dir==0) nC++; // right
			else if(dir==1) nR++; // down
			else if(dir==2) nC--; // left
			else nR--; // up
			// if the next number you hit is already visited, change direction
			if(nR<0||nR>=m||nC<0||nC>=n||visited[nR][nC]) {
				dir=(dir+1)%4; continue;
			}
			cR=nR; cC=nC; // confirm the shift
			System.out.print(A[cR][cC]+" ");
			visited[cR][cC]=true;
			cnt--;
		}
		in.close();
	}
}
