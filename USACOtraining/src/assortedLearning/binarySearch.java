package assortedLearning;
/* Binary search study. prints -1 if no value satisfies
TODO: make this into a template
 */
import java.util.*;
import java.io.*;

public class binarySearch {
	static int t = 3; // TARGET VALUE. MANUALLY CHANGED
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		// takes in an int n, then n integers a_1, a_2, ... a_n
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A); // A MUST be sorted
		
		// search A[] for the 1st index j of A[] so that A[j]>= a target t
		int high = n-1;
		int low = 0;
		// search indices low to high inclusive repeatedly
		// first check if the value is actually possible to get
		if(A[high]<t)out.println(-1);
		else if(A[low]>=t)out.println(low);
		else {
			while(low<high) {
				int mid = (low+high)/2;
				
				if(A[mid]<t) { // mid was guessed too low
					low=mid+1;
				}
				else { // mid is correct but mb theres a lower val?
					high=mid;
				}
			}
			out.println(low); // index j
			
		}
		
		// search A[] for the last index j of A[] so that A[j]<= a target t
		high = n-1;
		low = 0;
		// search low to high inclusive repeatedly
		if(A[high]<=t)out.println(high);
		else if(A[low]>t)out.println(-1);
		else {
			while(low<high) {
				int mid = (low+high+1)/2;
				
				if(A[mid]>t) { // if mid is too high
					high=mid-1;
				}
				else { // if mid is right but there might be a later mid thats better
					low=mid;
				}
			}
			out.println(high); // index j
		}
		
		in.close();
		out.close();
	}
}
