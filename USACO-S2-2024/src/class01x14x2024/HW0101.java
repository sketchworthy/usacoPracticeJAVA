package class01x14x2024;
/* Inversion Pairs _status{COMPLETE or not}_ DONE IN CPP
 * Given n [1,e5] integers A[i], find the #
 * of pairs (j,k) s.t. j<k, but A[j]>A[k].
 * 
 * For example, if A is (2,4,1,3), we should get 3 b/c
 * 2>1, 4>1, and 4>3.
 * 
 * idea: essentially we just want to find the # of pairs
 * of eles s.t. the greater element is before the smaller.
 * to do this, we loop thru each of the eles.
 * we keep an arraylist of eles before the curr ele.
 * each time we add another ele that is before the
 * curr ele, we re-sort the array (EDIT: THIS TAKES TOO LONG)
 * 
 * implementation: 
 * 
 * difficulty:
 *
 * 
 */
import java.util.*;
import java.io.*;

public class HW0101 {
	static int w = 1;

	public static void main(String[] args) throws Exception {
		// take input
		// BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of eles
		int[] A = new int[n]; // A[x][0&1]: [actual ele & x(index of ele)]
		int[] sorted=new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j] = Integer.parseInt(st.nextToken());
//			A[j][1] = j;
			sorted[j]=A[j];
		}
		Arrays.sort(sorted);
		
//		int ans=0;
//		
//		for(int j=0;j<n;j++) {
//			int ele = A[j];
//			int place=-1; // location ele is found in sorted[]
//			
//			// binary search for largest place
//			int low=0;
//			int high=n-1;
//			while(low<high) {
//				int mid = (low+high+1)/2;
//				
//				if(sorted[mid]>ele) {
//					high = mid-1;
//				}
//				else if(sorted[mid]<ele){
//					low = mid+1;
//				}
//				else {
//					low=mid;
//				}
//			}// 7 + 5 + 0 + 4 + 2 + 0 + 1
//			
//			place=low;
//			
//			ans+=Math.abs(j-place);
//		}

		out.println(ans);

		in.close();
		out.close();
	}
}