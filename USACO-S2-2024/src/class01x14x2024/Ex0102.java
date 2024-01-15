package class01x14x2024;
/* USACO 2017 Feb Silver Cows Cross Road I COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=714
 * 
 * idea: sort the cows by endtimes first, then by first starttimes.
 * my initial idea was to use 2 pointers, but that didn't work bc
 * i could've had an earlier cow with a later startpoint so
 * i would have skipped over chickens. rather i should have kept
 * a treeMap (aka multiset) of chickens and crossed em off 1 by 1
 * but not in order
 * 
 * difficulty:
 * remember that 
 *   *  *  <-chickens
 *     --- <-cow1 (considered earlier)
 * ------------ <-cow2 (considered later)
 * also start doing cpp coding more,
 * multisets in cpp are treeMaps in java
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0102 {
	public static void main(String[] args) throws Exception {
		// take input
//		 BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new FileWriter("helpcross.out"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(st.nextToken()); // # of chickens
		int n = Integer.parseInt(st.nextToken()); // # of cows
		
		int[] chickTimes = new int[c]; // times chickens are available
		
		// keep a TreeMap as a multiset
		TreeMap<Integer, Integer> freq = new TreeMap<>(); 
		// <chicken value, freq>
		for(int j=0;j<c;j++) {
			int temp = Integer.parseInt(in.readLine());
			freq.put(temp, freq.getOrDefault(temp, 0)+1);
		}
//		for(int j=0;j<c;j++) 
//			chickTimes[j]= Integer.parseInt(in.readLine());
//		Arrays.sort(chickTimes);
		
		int[][] cowRanges = new int[n][2]; // endpoints of cow time ranges
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			cowRanges[j][0] = Integer.parseInt(st.nextToken());
			cowRanges[j][1] = Integer.parseInt(st.nextToken());
		}
		// sort cows ascending order, first by endpoint, then by startpoint
		Arrays.sort(cowRanges,(a,b)-> a[1]==b[1] ? a[0]-b[0] : a[1]-b[1] );
		
		int ans=0;
		
		int coI = 0; // current index for cows arr
		while(coI<n) { // check for earliest chicken that satisfies cow
			int startPoint = cowRanges[coI][0];
			int endPoint = cowRanges[coI][1];
			Integer tmp = freq.ceilingKey(startPoint);
			if(tmp!=null && tmp<=endPoint) {
				ans++;
				if(freq.get(tmp)==1)freq.remove(tmp);
				else freq.put(tmp, freq.get(tmp)-1);
			}
			
			coI++;
		}
		
		
		// previous incorrect idea with 2 pointers
		//  doesn't work b/c an 'earlier' cow might be later startpoint
		//  than a 'later' cow. for ex: 
		//  *  *   chickens
		//    ___  cow1
		// _______ cow2
		// if when considering cow1 you skip past the 1st chicken, 
		// then you've lost out
		
//		int chI = 0; // current index for chickTimes arr
//		int coI = 0; // current index for cows arr
//		while(chI<c && coI<n) {
//			int startPoint = cowRanges[coI][0];
//			int endPoint = cowRanges[coI][1];
//			if(startPoint>chickTimes[chI]) { // 1st chicken too early
//				chI++; continue;
//			}
//			if(chickTimes[chI]>endPoint) { // 1st cow too early
//				coI++; continue;
//			}
//			
//			// else, they pair up
//			ans++;
//			chI++;
//			coI++;
//		}
		
		out.println(ans);

		in.close();
		out.close();
	}
}