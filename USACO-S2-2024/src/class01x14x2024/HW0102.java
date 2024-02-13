package class01x14x2024;
/* USACO 2018 Mar Silver Out of Sorts COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=834
 * 
 * idea: the # of moo's that will happen is just the max
 * distance any ele will have to go left to get to it's place
 *  in the sorted arr
 * 
 * difficulty: ez
 * 
 */
import java.util.*;
import java.io.*;

public class HW0102 {
	public static void main(String[] args) throws Exception {
		// take input
//		 BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		PrintWriter out = new PrintWriter(new FileWriter("sort.out"));
		int n = Integer.parseInt(in.readLine()); // # of eles in A[]
		int[] A = new int[n];
		int[] sorted=new int[n];
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(in.readLine());
			sorted[j]=A[j];
		}
		Arrays.sort(sorted);
		
		int ans=0;
		
		for(int j=0;j<n;j++) {
			int ele = A[j];
			int place=-1; // location ele is found in sorted[]
			
			// binary search for largest place
			int low=0;
			int high=n-1;
			while(low<high) {
				int mid = (low+high+1)/2;
				
				if(sorted[mid]>ele) {
					high = mid-1;
				}
				else if(sorted[mid]<ele){
					low = mid+1;
				}
				else {
					low=mid;
				}
			}
			
			place=low;
			
			ans = Math.max(ans, j-place+1);
		}

		out.println(ans);

		in.close();
		out.close();
	}
}