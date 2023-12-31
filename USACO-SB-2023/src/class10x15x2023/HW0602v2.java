package class10x15x2023;
/* USACO 2016 Mar Silver Diamond Collector COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=643
 * Given N #s, find the max # of them that can be
 * contained in 2 spans of K
 * 
 * idea: loop thru the sorted list of diamond sizes and try to find
 * # of diamonds that could fit in a display. this can be done by
 * binary search, bc the list is sorted! then, we hit an unexpected
 * problem in that we want the max overlap of 2 spans somehow.
 * note that its most beneficial to look at spans that dont overlap
 * at all. so we just loop thru at O(n^2), which doesnt work until
 * u only look at non-overlapping, so the time needed is lessened.
 * 
 * difficulty: okay once i realized binary search, my debugging
 * of reducing to below tle was just removing consideration of
 * unnecessary cases
 * 
 */
import java.util.*;
import java.io.*;

public class HW0602v2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new FileWriter("diamond.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of diamonds
		int k = Integer.parseInt(st.nextToken()); // max # 2 sizes in a case can differ by
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(in.readLine());
		}
		Arrays.sort(A); // diamond sizes can be sorted, since it doesn't matter
		// since the diamond sizes can be sorted, you can also do binary search 
		//  to find the max index of the diamond that is within this starting diamonds range
		
		int[] ends = new int[n]; // ends[j]= endpoint of span that starts from j
				
		for(int j=0;j<n;j++) {
			int low = j;
			int high = n-1;
			while(low<high) {
				int mid = (low+high+1)/2;
				
				if(A[mid]<=A[j]+k) { // in span!
					low=mid;
				}
				else { // 
					high=mid-1;
				}
			}
			ends[j]=high;
		}
		
		int mx = 0; // max overlap of 2 spans
		// loop through ends[] and return max overlap of 2 spans
		for(int j=0;j<n-1;j++) {
			mx=Math.max(mx, ends[j]-j+1);
			for(int i=ends[j]+1;i<n;i++) {
				if(ends[i]<n) {
					mx=Math.max(mx, ends[j]-j+1+ends[i]-i+1);
				}
				else {
					mx=Math.max(mx, ends[j]-j+1+n-i+1);
				}
			}
		}
		
		out.print(mx);
	
		in.close();
		out.close();
	}
}
