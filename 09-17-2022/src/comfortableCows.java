/* USACO 2021 February Contest, Bronze Problem 2. Comfortable Cows
 * I think this is my 2nd time doing this problem
 * difficulty: very ez, solved in less than 30 min i think!
 * Basic idea was to make grid and keep track of the # of neighboring cows and how the
 *  # changes
 */
import java.util.*;

public class comfortableCows {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows
		int[][] coords = new int[n][2]; // coords[k][0] is x-coord of kth cow,
		//                                 coords[k][1] is y-coord of kth cow
		int[][] grid = new int[1001][1001]; // if grid[x][y]=j; that means cow j resides
																		// in that square
		// fill array grid with -1s
		for(int j=0;j<1001;j++) {
			for(int k=0;k<1001;k++)grid[j][k]=-1;
		}
		int[] neighbors = new int[n]; // neighbors[j] is the # of neighbors of cow j
		for(int j=0;j<n;j++) {
			coords[j][0]=in.nextInt();
			coords[j][1]=in.nextInt();
		}
		
		// start simulating and printing # of comfy cows
		int comfN =0; // current # of comfy cows
		for(int j=0;j<n;j++) {
			// fill in the grid square of that cow 
			grid[coords[j][0]][coords[j][1]]=j;
			// check if any neighboring squares have cows & see how comfN is affected
			// check top square
			if(coords[j][1]<1000) { // see if bounds are possible
				if(grid[coords[j][0]][coords[j][1]+1]!=-1) { // check if neighbor present
					neighbors[j]++;
					int neighCnum=grid[coords[j][0]][coords[j][1]+1]; // neighbor cow #
					// change # of neighbors for neighbor cow
					neighbors[neighCnum]++;
					if(neighbors[neighCnum]==3)comfN++;
					else if(neighbors[neighCnum]==4)comfN--;
				}
			}
			// check left square
			if(coords[j][0]>0) { // see if bounds are possible
				if(grid[coords[j][0]-1][coords[j][1]]!=-1) { // check if neighbor present
					neighbors[j]++;
					int neighCnum=grid[coords[j][0]-1][coords[j][1]]; // neighbor cow #
					// change # of neighbors for neighbor cow
					neighbors[neighCnum]++;
					if(neighbors[neighCnum]==3)comfN++;
					else if(neighbors[neighCnum]==4)comfN--;
				}
			}
			// check right square
			if(coords[j][0]<1000) { // see if bounds are possible
				if(grid[coords[j][0]+1][coords[j][1]]!=-1) { // check if neighbor present
					neighbors[j]++;
					int neighCnum=grid[coords[j][0]+1][coords[j][1]]; // neighbor cow #
					// change # of neighbors for neighbor cow
					neighbors[neighCnum]++;
					if(neighbors[neighCnum]==3)comfN++;
					else if(neighbors[neighCnum]==4)comfN--;
				}
			}
			// check bottom square
			if(coords[j][1]>0) { // see if bounds are possible
				if(grid[coords[j][0]][coords[j][1]-1]!=-1) { // check if neighbor present
					neighbors[j]++;
					int neighCnum=grid[coords[j][0]][coords[j][1]-1]; // neighbor cow #
					// change # of neighbors for neighbor cow
					neighbors[neighCnum]++;
					if(neighbors[neighCnum]==3)comfN++;
					else if(neighbors[neighCnum]==4)comfN--;
				}
			}
			System.out.println(comfN);
		}
		
		in.close();
	}
}
