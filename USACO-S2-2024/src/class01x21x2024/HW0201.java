package class01x21x2024;
/* Min sum difference COMPLETE
 * Given 2 positive int arrs A and B with n eles each,
 * the absolute sum difference is defined as the sum of 
 * |A[i]-B[i]| for all i. Report the minimum absolute sum
 * diff possible if we can replace at MOST 1 ele of A with
 * another ele in A.
 * 
 * idea: there are 2 parts to this problem: 
 * 1. finding the index(es) of the largest min sum diff(s)
 * 2. finding the best possible ele to replace the best index with
 * #1 is easy, we can easily generate a list of the greatest
 * min sum diffs in 1e5 time at max.
 * For #2, we keep a sorted arr of all ele values in A, and 
 * an arr of all ele values in B. Then for each of the
 * 'best indices' in the list we generated in #1, we want to
 * find the smallest possible diff we can generate between
 * any ele in A and any ele in the chosen indices list in B.
 * To find the best ele in A for a given chosen index ele in B,
 * we use binary search
 * 
 * difficulty: p ez & straightforward
 * 
 */
import java.util.*;
import java.io.*;

public class HW0201 {
	static int w = 9;
	
	static long MOD = (long) 1e9+7;

	public static void main(String[] args) throws Exception {
		// take input
		 BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of eles
		int[] A = new int[n]; int[] B = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			B[j]=Integer.parseInt(st.nextToken());
		}
		
		long total=0;
		int[] sumDiffs = new int[n];
		int maxSumDiff=0;
		for(int j=0;j<n;j++) {
			sumDiffs[j] = Math.abs(A[j]-B[j]);
			total+=sumDiffs[j];
			maxSumDiff=Math.max(maxSumDiff, sumDiffs[j]);
		}
		
		ArrayList<Integer> bestIndices = new ArrayList<>();
		for(int j=0;j<n;j++) {
			if(sumDiffs[j]==maxSumDiff) {
				bestIndices.add(j);
			}
		}
		
		Arrays.sort(A);
		
		int minDiff=maxSumDiff; // min sum diff of possible bestIndices diffs
		
		for(int idx:bestIndices) {
			int val = B[idx];
			
			// find closest ele in A to val with binary search
			// find largest ele less than val
			int low = 0; // indexes
			int high= n-1;
			while(low<high) {
				int midI = (low+high)/2;
				if(A[midI]<val) {
					low=midI+1;
				}
				else high=midI;
			}
			minDiff = Math.min(Math.abs(val-A[low]), minDiff);
			
			// find smallest ele greater than val
			low = 0;
			high= n-1;
			while(low<high) {
				int midI = (low+high+1)/2;
				if(A[midI]>val) {
					high=midI-1;
				}
				else low=midI;
			}
			minDiff = Math.min(Math.abs(val-A[low]), minDiff);
			
		}

		out.println((total-maxSumDiff+minDiff)%(MOD));

		in.close();
		out.close();
	}
}