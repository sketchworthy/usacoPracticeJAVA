package class10x01x2023;
/* USACO 2020 March Silver Social Distancing COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1038
 * 
 * idea: binary search for D within [1,10^18], making sure if D works
 * that there is a way to arrange the cows across the intervals so
 * that the min distance between any 2 cows is D
 * 
 * difficulty: had debug trouble, but it ended up being bc my mid
 * was (low+high)/2 not (low+high+1)/2. gotta be careful abt that.
 * again, usually inverse binary search errors are bc my range for the
 * search space was wrong somehow. also ran into an error with
 * initializing last accidentally as 0 instead of as grass[0][0].
 * i gotta be careful with what i initialize variables as. */
import java.util.*;
import java.io.*;

public class HW0402 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("socdist.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("socdist.out"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows
		int m = Integer.parseInt(st.nextToken()); // # of grass intervals
		long[][] grass = new long[m][2]; // start and stop points of grass intervals
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			grass[j][0]=Long.parseLong(st.nextToken());
			grass[j][1]=Long.parseLong(st.nextToken());
		}
		Arrays.sort(grass,(a,b)->(int)(a[1]-b[1])); // also sorts beginnings of intervals (mutually dijoint)
		long low = 1;
		long high = (long)1e18;
//		long high = grass[m-1][1];
		
		while(low<high) {
			long mid = (low+high+1)/2;
			
			boolean works=false;
			// check if it'll work for mid to be the min dist between any 2 cows
			int lastI=0; // index of current interval where cows are being placed
			long num=1; // # of cows alr placed. 1 alr at 0
			long last=grass[0][0]; // loc where last cow was placed
			while(lastI<m) {
				long first=Math.max(grass[lastI][0],last+mid); // 1st pos where cow can be placed
				long span = grass[lastI][1]-first+1;// dist span in current interval where cow can be placed
				
				if(span>=1) { // you can put at least 1 cow in this interval
					long add = 1; span--; // first default cow added
					last=first; // update last
					add += span/mid; // add further # of cows addable in this interval
					last+=(add-1)*mid; // update last further
					num+=add;
				}
				// now, all cows addable in this interval have been added
				// update variables
				lastI++;
				
				if(num>=n) {
					works=true;
					break;
				}
			}
			
			if(works) { // if num>=n
				low=mid;
			}
			else { // num<n, # of placed cows < n
				high=mid-1;
			}
		}
		out.println(high);
		
		in.close();
		out.close();
	}
}
