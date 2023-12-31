package class10x01x2023;
/* Meeting Place COMPLETE
 * Given several points on a # line, return the min max-time
 * a given location is from the given points. each point has a
 * varying speed
 * 
 * idea: inverse binary search for the given location. using a helper
 * program i determined that the max time that ppl get to a rendevous
 * point does have a global minima that is also all local minima.
 * then our problem becomes searching for minimum. i did this by
 * having 2 central calculated points, mid and adj (mid+1, or further)
 * 
 * difficulty: once i thought abt it w a clear head implementing was ez
 * 
 * EDIT: Once I reviewed class sol I realized the faster, 'intended'
 * method would be to inverse binary search for the TIME TAKEN
 * instead, not for the given loc. This, while frustrating, teaches
 * that if inverse binary search seems too slow or complicated,
 * I'm probably searching for the wrong finishing element.
 * this 2nd method is implemented in HW0401v2.
 */
import java.util.*;
import java.io.*;

public class HW0401 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[][] A = new int[n][2]; // loc, then speed
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][0]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A,(a,b)->a[0]-b[0]);
		
		int low = A[0][0];
		int high= A[n-1][0];
		double sum=0;
		while(low<high) {
			int mid = (low+high)/2;
			int adj = mid+1;
			// if mid or adj is the rendevous point
			double sum1=0; // sum for mid
			double sum2=0; // sum for adj
			for(int j=0;j<n;j++) {
				sum1=Math.max(sum1, (double)Math.abs(A[j][0]-mid)/(double)A[j][1]);
				sum2=Math.max(sum2, (double)Math.abs(A[j][0]-adj)/(double)A[j][1]);
			}
			while(sum2==sum1&&adj<high) { // if the sums r equal
				adj++;
				sum2=0;
				for(int j=0;j<n;j++) {
					sum2=Math.max(sum2, (double)Math.abs(A[j][0]-adj)/(double)A[j][1]);
				}
			}
			if(sum1==sum2) { // if adj==high and all of mid to adj have equal times
				high=mid;
				sum=sum1;
			}
			else if(sum1<sum2) { // if minima is to the left
				high=mid; // move indices down towards mid
				sum=sum1;
			}
			else { // if minima to the right\
				low=adj;
				sum=sum2;
			}
		}
		out.print(sum);
		
		in.close();
		out.close();
	}
}