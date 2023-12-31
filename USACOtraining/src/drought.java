/* USACO 2022 Jan Bronze Problem 3 NOT COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1181
 */
import java.util.*;

public class drought {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int t=in.nextInt();t>0;t--) {
			int n = in.nextInt();
			int[] hungers = new int[n];
			for(int j=0;j<n;j++) hungers[j]=in.nextInt();
			
			int cnt=0;
			boolean impossible = false;
			int uptoi=0; // consistent up to index [uptoi]. so if uptoi=2, indices 0-2 are
			// currently valued the same
			if (hungers[0]>hungers[1]) System.out.println(-1);
			else if (!(n==0|| n==1)){
				while(uptoi<n) {
					
				}
				
				// decrease hungers[1] & hungers[2] until hungers[1] equals hungers[0].
				// if hungers[0]>=hungers[2], decrease hungers[0] til they're the same val
				// else if hungers[0]<hungers[2],decrease hungers[3]
				// if at any point any index gets lower than 0, return impossible & break
				
				
			}
			
			// print
			if(impossible)System.out.println(-1);
			else System.out.println(cnt);
			
//			System.out.println(works(n,hungers));
			
			// if the numbers on the edge are larger than their consec interior nums
			//  it's not possible.
			//   ex: 5 2 2 3 6 2 5 2 not possible. b/c 5 (index 0) > 2 (index 1)
			
			// otherwise if they are the same value, just remove them for now--they
			//  aren't part of your 'seeking if it is possible' quest or not. later
			//   when ur checking how much corn to feed, however, it is necessary
			//    to check them.
			
			// if they are of lesser value, you need to decrease the 2nd one. so
			// decrease that until its same val as char 1, and then recursively head
			// into the str.
			
			// note that u can only remove 2 blocks of identical chars at the front
			
		}
		in.close();
	}
//	public static int works(int n, int[] hungers) { // hungers[] size n. returns min # of
//		// decreases needed, or -1 if impossible
//		if(n==0 || n==1)return 0;
//		cnt=0;
//		
//		if(hungers[0]>hungers[1])return -1;
//		
//		
//		return cnt;
//	}
}
