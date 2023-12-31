// status: finished but program runs kinda slow, could be more efficient
// have checked class solution. basic idea of that was to:
/*  make 2 arrays, right[] and left[], each denoting the clump sizes
 *  to the right and left of some index j. Then at the end loop through
 *  and find the max sum of values in 2 indices. */
import java.util.*;
public class brokenNecklace {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();;
		
		Scanner in = new Scanner(System.in);
		int len = in.nextInt();
		String str = in.next();
		char[] beads = (str+str).toCharArray(); // double up the string
		
		// check if the entire string is the same color:
		boolean allSame=true;
		char c = beads[0];
		for(int j=1;j<len;j++) {
			if(beads[j]!=c) allSame=false;
		}
		if(allSame==true) {
			System.out.println(len);
		}
		else {
//			// compress into clumps and find biggest sum of 2 consec clumps
			int maxCnt = 0; // max beads collected
			for(int j=0;j<len;j++) {
				int n = beadsCollected(beads,j);
				if(n>maxCnt) {
					maxCnt=n;
				}
			}
			System.out.println(maxCnt);
			in.close();
		}
	    long end = System.currentTimeMillis();;
	    System.out.println((end - start) + " ms");
	}
	
	public static int beadsCollected(char[] beads, int index) {
		int cnt = 0;
		char c = beads[index]; 
		int place=0;
		
		// make sure thing behind you isn't the same thing
		if(beads[beads.length-1]==c||beads[beads.length-1]=='w') {
			return 0;
		}
		// if it's a white bead
		while(c=='w') {
			cnt++;
			place++;
			c=beads[index+place];
		}
		
		// look forward for next clump
		while(beads[index+place]==c||beads[index+place]=='w') {
			cnt++;
			place++;
		}
		// look even more forward for next clump
		c=beads[index+place];
		int j=0;
		while(beads[index+place+j]==c||beads[index+place+j]=='w') {
			cnt++;
			j++;
		}
		return cnt;
	}
}
