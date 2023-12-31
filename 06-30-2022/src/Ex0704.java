/* finished
 * basic idea supplied by others but p simple to implement
 * basically go through each point and add 1 to it, so you go through all the possible options
 * without doing redundant work
 * usaco server got 3/15 cases, timed out on everything else
 */
import java.util.*;
import java.io.*; // todo: change scanner to file and submit to usaco server
public class Ex0704 {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("balancing.in"));
		PrintWriter out = new PrintWriter(new File("balancing.out"));
		int n = in.nextInt();
//		in.nextInt(); // for b (upper bound)
		int[][] cowcoords = new int[n][2];
		for(int j=0;j<n;j++) {
			cowcoords[j][0]=in.nextInt(); // x value
			cowcoords[j][1]=in.nextInt(); // y value
		}
		int m = Integer.MAX_VALUE;
		int minM = Integer.MAX_VALUE;
		for(int x=0;x<n-1;x++) {
			for(int y=0;y<n-1;y++) {
				// find m when the lines are x=cowcoords[x][0]+1 and y=cowcoords[y][0]+1
				int c1=0; // count in quadrant 1
				int c2=0; // q2
				int c3=0; // q3
				int c4=0; // q4
				for(int j=0;j<n;j++) {
					if(cowcoords[j][0]>cowcoords[x][0]+1 && cowcoords[j][1]>cowcoords[y][0]+1) c1++;
					if(cowcoords[j][0]<cowcoords[x][0]+1 && cowcoords[j][1]>cowcoords[y][0]+1) c2++;
					if(cowcoords[j][0]<cowcoords[x][0]+1 && cowcoords[j][1]<cowcoords[y][0]+1) c3++;
					if(cowcoords[j][0]>cowcoords[x][0]+1 && cowcoords[j][1]<cowcoords[y][0]+1) c4++;
				}
				m=Math.max(Math.max(c3, c4), Math.max(c2, c1));
				minM=Math.min(minM, m);
			}
		}
		out.print(minM);
		out.close();
		in.close();
	}
}
