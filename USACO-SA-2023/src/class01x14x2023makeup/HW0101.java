package class01x14x2023makeup;
/*
 * given string of n letters, rearrange letters into 2d and find largest
 * clump (horizontal, vertical, diagonal) where 1<=n<=25000. 
 * 
 * key idea: for a clump of letter x to occur, the distance between the x's
 * must be constant. so loop thru looking at startIndex, possible distances 
 * between x's, and find max clump size for each of that.
 * 
 * notes: solution might be a little slow idk. all i know is that it gives the 
 * right answers. 
 * the 'in-class' solution decrees to check all the possible
 * 2D array row lengths and check each diagonal, col, row, etc for the max clump len
 * which i think is faster bc i basically do the same w the 'check all possible row
 * lens', but it only checks each diag, col, row instead of me looping thru aaaaall
 * the possible distances? actually on 2nd thought, idk. mb i will ask teacher
 * 
 */

import java.io.*;

public class HW0101 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		char[] letters = in.readLine().toCharArray();

		int maxClump=1;
		for(int startIndex=0;startIndex<=n-2;startIndex++) {
			// max dist for starting index i: (n-1-i)/maxClump
			int maxDist=(n-1-startIndex)/maxClump;
			for(int dist=1;dist<=maxDist;dist++) {
				// find max clump that can be made from these conditions
				int cClump=1;
				int cPos=startIndex;
				while(cPos+dist<n) {
					cPos=cPos+dist;
					if(letters[cPos]==letters[startIndex]) {
						cClump++;
					}
					else break;
				}
				maxClump=Math.max(cClump, maxClump);
			}
		}
		out.println(maxClump);
		
		in.close();
		out.close();
	}
}
