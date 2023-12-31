/* unfinished, basically lots of array bash tho
 * 
 */
import java.util.*;
public class Ex0206 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] locs = new int[n][2]; // x and y locations for n cows
		for(int j=0;j<n;j++) {
			locs[j][0]=in.nextInt();
			locs[j][1]=in.nextInt();
		}
		int[] f = new int[5];
		// f[x] is the # of cows with x neighbor cows
		
		// occupied[x][y]==true iff x,y occupied by cow
		boolean[][] occupied = new boolean[1001][1001];
		
		for(int j=0;j<n;j++) {
			int x = locs[j][0];
			int y = locs[j][1];
			occupied[x][y]=true; // add cow at position
			// check info for this position
			int neighbors = 0;
			if(occupied[x][y+1]==true) neighbors++; // check above
			if(occupied[x+1][y]==true) neighbors++; // check right
			if(occupied[x][y-1]==true) neighbors++; // check down
			if(occupied[x-1][y]==true) neighbors++; // check left
			f[neighbors]++;
			
			// update information for 4 neighbor positions
			if(occupied[x][y+1]==true) { // above neighbor
				
			}
			if(occupied[x+1][y]==true) neighbors++; // right
			if(occupied[x][y-1]==true) neighbors++; // down
			if(occupied[x-1][y]==true) neighbors++; // left
			// update array f
			
		}
	}
}
