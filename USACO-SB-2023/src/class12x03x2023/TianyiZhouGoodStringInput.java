package class12x03x2023;
/* Good String Input COMPLETE
 * A string is good if its left cyclic character shift
 * (ex: abc -> bca) is equal to its right cyclic character shift
 * (ex: abc -> cab). Given a string, find the min amount of chars
 * you need to erase from it to make it good.
 * 
 * idea:
 * If you can reduce to a string with an odd number of letters left
 * you must have all remaining letters be the same
 * If you can reduce to a string with an even number of letters left
 * either all remaining lettrs are the same or every other letter is
 * equal (so string can be of the form a b a b a b  character-wise)
 * 
 * However, choosing what to remove takes long time. so instead of choosing what
 * 8 or 9 digits to remove, choose 2 or 1 digits to keep
 * 
 * difficulty: once the key idea of realizing i need to look at max chars
 * i can keep instead of min chars i can remove was told, it was easy.
 * had to be careful with clarity tho
 * 
 * Could be further optimized: Instead of keeping track of first and last
 * chars explicitly, just -1 to length if the subseq has an odd length.
 * Could be further organized by: Instead of having a 10by10 arr,
 * have a function that finds how many max keep for chars a, b, and str s.
 * It makes everything so much more organized
 * 
 */
import java.util.*;
import java.io.*;

public class TianyiZhouGoodStringInput {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		long eraseCnt=0; // sum of characters that need to be erased
		for(int q = Integer.parseInt(in.readLine());q>0;q--) {
			char[] str = in.readLine().toCharArray();
			int n = str.length;
						
			// find out how many chars needed to remove to get an odd number of
			// alternating sequence chars
			
			// BUT: Change perspective! if we iterate thru string, what is most
			// amount of 1 or 2 chars we can keep?
			// pickUpFreqs[a][b] gives # of alternating a and b sequences
			int[][] pickUpFreqs = new int[10][10]; // a<=b always
			// records 1st char in a,b,a... sequence so last char won't 
			int[][] firstCharInSeq = new int[10][10]; //be the same as the 1st
			for(int j=0;j<10;j++) Arrays.fill(firstCharInSeq[j], -1);
			int[][] lastCharInSeq = new int[10][10]; // last char in every seq
			boolean[][] justPickedUp = new boolean[10][10];
			// justPickedUp[a][b] denotes if a was just picked up in a/b seq,
			// justPickedUp[b][a] denotes if b was just picked up in a/b seq
			
			for(int i=0;i<n;i++) {
				int c = str[i]-'0'; // # of character
				
				// look at alternating sequences
				for(int j=0;j<10;j++) {
					if(c==j) continue; 
					if(justPickedUp[c][j]==false) {
						if(c<j) {
							pickUpFreqs[c][j]++;
							if(firstCharInSeq[c][j]==-1) {
								firstCharInSeq[c][j]=c;
							}
							lastCharInSeq[c][j]=c;
							
						}
						else {
							pickUpFreqs[j][c]++;
								if(firstCharInSeq[j][c]==-1) {
								firstCharInSeq[j][c]=c;
							}
							lastCharInSeq[j][c]=c;
						}

						justPickedUp[j][c]=false;
						justPickedUp[c][j]=true;
					}
				}
				
				pickUpFreqs[c][c]++; // if all equal
			}
			
			
			int maxKeep = 0; // max # of chars that can be kept
			for(int j=0;j<10;j++) {
				maxKeep = Math.max(maxKeep, pickUpFreqs[j][j]);
			}
			for(int b=0;b<10;b++) {
				for(int a=0;a<b;a++) {
					// prune if last are not the same as 1st chars
					if(lastCharInSeq[a][b]==firstCharInSeq[a][b]) {
						pickUpFreqs[a][b]--;
					}
					// maxKeep update
					maxKeep = Math.max(maxKeep, pickUpFreqs[a][b]);
				}
			}
			
			eraseCnt+=n-maxKeep;
		}
		
		out.print(eraseCnt);
		
		in.close();
		out.close();
	}
}
