package class03x18x2023;
/* USACO 2015 March Bronze P4 (DONE W/O RECURSION)
 * 
 * idea: create a grid of sets. just looking at the top left triangle, for each
 * square fill its set with all the possible strs so far it could contain.
 * once u get to the middle diagonal, make a copy of the grid and look at the
 * bottom right triangle instead, then find the # of equal 'halves' of the
 * middle sets
 * 
 * difficulty: thinking up idea & implementation both annoying af
 * 
 * note: can simplify code by making hashset of strs an object Cell, so u can
 * make 2d array of Cell[][] instead of 2d arraylist
 */
import java.util.*;
import java.io.*;

public class Ex0801 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("palpath.in"));
		PrintWriter out = new PrintWriter(new FileWriter("palpath.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		char[][] grid = new char[n][n];
		for(int j=0;j<n;j++) {
			grid[j]=in.readLine().toCharArray();
		}
		
		ArrayList<ArrayList<HashSet<String>>> strs = new ArrayList<>(); 
									// ^ table of possible strs
		for(int r=0;r<n;r++) {
			ArrayList<HashSet<String>> row = new ArrayList<>();
			for(int c=0;c<n;c++) {
				HashSet<String> s = new HashSet<>();
				row.add(s);
			}
			strs.add(row);
		}
		
		// top left triangle
		strs.get(0).get(0).add(grid[0][0]+"");
		for(int r=1;r<n;r++) { // for each square, its set is combo of left & top
			for(int c=0;c<=r;c++) { // iterate thru squares grid[r-c][c]
				// grid[r-c][c] is a combo of grid[r-c-1][c] and grid[r-c][c-1]
				 //  as long as those squares exist
				if(c>0) { // add all of grid[r-c][c-1] to grid[r-c][c]
					for(String s:strs.get(r-c).get(c-1)) {
						strs.get(r-c).get(c).add(s+""+grid[r-c][c]);
					}
				}
				if(c<r) { // add all of grid[r-c-1][c] to grid[r-c][c]
					for(String s:strs.get(r-c-1).get(c)) {
						strs.get(r-c).get(c).add(s+""+grid[r-c][c]);
					}
				}
			}
		}
		
		// bottom right triangle
		strs.get(n-1).get(n-1).add(grid[n-1][n-1]+"");
		for(int r=2;r<n;r++) { // each square's set is combo of bottom & right
			for(int c=n-r;c<n;c++) { // iterate thru squares grid[2n-1-r-c][c]
				// grid[2n-1-r-c][c] is combo of [2n-r-c][c] & [2n-1-r-c][c+1]
				if(c<n-1) { // add all of grid[2n-r-1-c][c+1]
					for(String s:strs.get(2*n-r-1-c).get(c+1)) {
						strs.get(2*n-r-1-c).get(c).add(s+""+grid[2*n-r-1-c][c]);
					}
				}
				if(c!=n-r) { // add all of grid[2n-r-c][c] 
					for(String s:strs.get(2*n-r-c).get(c)) {
						strs.get(2*n-r-1-c).get(c).add(s+""+grid[2*n-r-1-c][c]);
					}
				}
			}
		}
		
		// for last diagonal, add any already-there palindromes to set pals
		HashSet<String> pals = new HashSet<>(); // set of palindromes
		for(int c=0;c<n;c++) { // finish up last SW-NE diagonal from bot right
			if(c<n-1) {
				for(String s:strs.get(n-1-c).get(c+1)) {
					if(strs.get(n-1-c).get(c).contains(s+""+grid[n-1-c][c]))
						pals.add(s+""+grid[n-1-c][c]);
				}
			}
			if(c>0) {
				for(String s:strs.get(n-c).get(c)) {
					if(strs.get(n-1-c).get(c).contains(s+""+grid[n-1-c][c]))
						pals.add(s+""+grid[n-1-c][c]);
				}
			}	
		}
		
		out.print(pals.size());
		in.close();
		out.close();
	}
}
