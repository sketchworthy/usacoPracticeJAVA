package class01x21x2023makeup;


/* USACO Mar 2019 Silver 'Left Out' Problem 1 REVIEW THIS PROBLEM
 * 
 * There is an NxN grid of cows, each facing R or L. Find the smallest row- & col-
 * indexed cow so that if that cow is flipped, FJ can, by flipping entire rows or
 * cols at once, change all the cows to face the same direction
 * 
 * my INITIAL idea: n<1000, so loop thru the n^2 possible solo cows fj can flip &
 * for each one check if it works. aka check if all cows can be put into the
 * orentation the flipped cow gets put in
 *  ^Doesn't work--too slow.
 *  
 * class sol: this took a while for me to understand. but first, align all cows
 * in grid in respect to 0,0 cow. then, go thru col 0 and row 0 and flip the
 * row/col if the col 0/ row 0 cow isn't the same as the 0,0 cow. this leads to
 * a configuration where all of row 0 and col 0 cows r the same, while the
 * (n-1)*(n-1) cows not in row 0 or col 0 are all random stuff. a 'problem cow'
 * can only be identified if 1: all (n-1)^2 cows are diff from cow 0,0 or
 * 2. a row of n-1 cows is the only 1 that's different or 3. 1 cow is diff
 * ^NOTE there is 1 worry w this solution: the 'bad' cow u find might not be the
 * least-indexed possible 'bad' cow
 */

import java.io.*;

public class HW0202 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("leftout.in"));
		PrintWriter out = new PrintWriter(new FileWriter("leftout.out"));
		int n = Integer.parseInt(in.readLine()); // n^2 = # of cows
		int[][] cows = new int[n][n];
		for(int j=0;j<n;j++) {
			String line=in.readLine();
			for(int k=0;k<n;k++) {
				if(line.charAt(k)=='R')cows[j][k]=1;
				else cows[j][k]=0;
			}
		}
		
		cows=align(cows); // align in respect to cows[0][0]
		int totalDiffs=0;
		int[] rowDiffs=new int[n]; // rowDiffs[x] shows # of diff vals in row x
		int[] colDiffs=new int[n];
		for(int j=1;j<n;j++) {
			for(int k=1;k<n;k++) {
				if(cows[j][k]!=cows[0][0]) {
					totalDiffs++;
					rowDiffs[j]++;
					colDiffs[k]++;
				}
			}
		}
		
		boolean found=true;
		int r = -1;
		int c = -1;
		// analyze totalDiffs
		if(totalDiffs==(n-1)*(n-1)) {
			r=0;
			c=0;
		}
		else if(totalDiffs==n-1) {
			// check which offending row/col bad cow is in
			for(int j=1;j<n;j++) {
				if(colDiffs[j]==n-1) {
					c=j; r=0;
					break;
				}
				if(rowDiffs[j]==n-1) {
					r=j; c=0;
					break;
				}
			}
		}
		else if(totalDiffs==1){
			Outer:
			for(int j=1;j<n;j++) {
				for(int k=1;k<n;k++) { // look for bad cow
					if(cows[j][k]!=cows[0][0]) {
						r=j; c=k;
						break Outer;
					}
				}
			}
		}
		else found=false;
		
		if(found)out.print((r+1)+" "+(c+1));
		else out.print(-1);
		in.close();
		out.close();
	}
	
	public static int[][] align(int[][] cows){
		// flips all of row[0] & col[0] w respect to cows[0][0]
		int n = cows.length;
		for(int j=1;j<n;j++) {
			if(cows[j][0]!=cows[0][0]) { // flip row
				for(int k=0;k<n;k++) {
					cows[j][k]=1-cows[j][k];
				}
			}
			if(cows[0][j]!=cows[0][0]) { // flip col
				for(int k=0;k<n;k++) {
					cows[k][j]=1-cows[k][j];
				}
			}
		}
		return cows;
	}
	
}
