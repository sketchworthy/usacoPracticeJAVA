/* USACO 2016 January Contest, Bronze Problem 3. Mowing the Field
 * pretty easy, basic idea is just the simulation xd
 * notes: failed edge case 8 at first bc forgot that after you step on a square
 * you've stepped on twice before, you need to rewrite the time on that square so you
 * can actually get the min time
 * 
 * took about 35ish min
 */
import java.util.*;
import java.io.PrintWriter;
import java.io.File;
public class mowingField {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("mowing.in"));
		PrintWriter out = new PrintWriter(new File("mowing.out"));
		int n = in.nextInt();
		int[][] grid=new int[2001][2001]; // if grid[x][y] is -1 FJ hasn't been there
// if he has, grid[x][y]=t where t is the time FJ visited ||| grid[1001][1001] is 0,0
		// fill grid with -1s
		for(int j=0;j<2001;j++) {
			for(int k=0;k<2001;k++) grid[j][k]=-1;
		}
		String[] directionsD=new String[n];// directionsD[k][0]=N,E,S or W
		int[] directionsS=new int[n];  // directionsS[k][1]=
									   //      # of steps taken in that direction
		for(int j=0;j<n;j++) {
			directionsD[j]=in.next();
			directionsS[j]=in.nextInt();
		}
		
		int x=-1; // max # of time it takes for grass to grow back AKA min # of time 
									// between FJ stepping on the same grass cell twice
		int cx=1000; // FJ's current x location
		int cy=1000; // FJ's current y location
		int t=0; // time
		grid[1000][1000]=0; // cut cell at origin
		for(int j=0;j<n;j++) { // for each direction
			// simulate the direction
			String D = directionsD[j]; // N,E,S or W
			int S = directionsS[j];    // # of steps taken in direction D
			for(int k=0;k<S;k++) { // for each step you take
				if(D.equals("N"))cy++;
				else if(D.equals("E"))cx++;
				else if(D.equals("S"))cy--;
				else cx--;
				t++;
				if(grid[cx][cy]!=-1) { // if FJ has been at this square before
					// find time between FJ meeting here now and last time
					int timeBetween = t-grid[cx][cy];
					if(x==-1)x=timeBetween;
					else x=Math.min(x, timeBetween);
				}
				// leave your mark on this square
				grid[cx][cy]=t;
				
//				System.out.println("x: "+x+". cx: "+cx+". cy: "+cy+".");
			}
		}
		out.print(x);
		
		in.close();
		out.close();
	}
}
