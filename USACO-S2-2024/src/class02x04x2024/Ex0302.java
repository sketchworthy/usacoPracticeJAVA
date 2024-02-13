package class02x04x2024;
/* Max Median COMPLETE
 * Given A[] with len N, report the largest median of a 
 * subarr A[x...y] with length >=K. 
 * 
 * idea:  
 We want to find the max median of a subarr of A[] with len N.
To do this, we binary search for possible #s being the max median.
To test if a median works, we evaluate each index in A[] and
create a new array B[], each ele being -1 if it's less than the
median and 1 if >= the median. We then make ps[], a prefix sum
arr of B[]. We want to find a ps[x]-ps[y] that is positive and x-y>=k-1
(FIX PS ARRS TO HAVE n+1 INDICES). if at least one exist then the median
works, else it doesn't. 

to do this calculation, we might loop through every possible
y index of ps[]. then we want to see if there exists a x index s.t. 
x>=y+k-1 and ps[x]-ps[y]>0. to easily find the latter, we can have
a suffix max array for ps[], smxps[]. then we can just check if
smxps[y+k-1]-ps[y]>0 and that's sufficient!
 * 
 * difficulty: i would have never thought of the idea... or would
 * i have? tbh i just have to keep in mind for the future that
 * if it asks to find the largest of something, think of inverse
 * binary search!!!
 * i also had a lot of trouble fine debugging and fine tuning indices 
 * to be correct for the ps arrays and smxps arrays.
 * also there was just ONE place where it was a > instead of a
 * >= which messed up all my test cases :/
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0302 {
	static int w = 9;

	public static void main(String[] args) throws Exception {
		// take input
		 BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // len of A[]
		int k = Integer.parseInt(st.nextToken()); // len at least K
		int[] A = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]= Integer.parseInt(st.nextToken());
		}
		
		// inverse binary search for median
		int low = 1;
		int high = n;
		while(low<high) {
			int med = (low+high+1)/2; // finding largest possible median
			
			// temp arr of -1s and 1s
			int[] B = new int[n];
			for(int j=0;j<n;j++) {
				if(A[j]<med) B[j]=-1;
				else B[j]=1;
			}
			
			// make prefix sum arr of B[]'s -1s and 1s
			int[] ps = new int[n]; // ps[j]=B[0]+B[1]+...+B[j]
			ps[0]=B[0];
			for(int j=1;j<n;j++) {
				ps[j]=ps[j-1]+B[j];
			}
			
			int smxPs[] = new int[n]; // suffix max arr of ps[]
			// smxPs[x]=max(ps[x],ps[x+1],ps[x+1],...ps[n-1])
			// = max(B[0]+...+B[x], B[0]+...+B[x+1], ..., B[0]+...+B[n-1])
			smxPs[n-1]=ps[n-1];
			for(int j=n-2;j>=0;j--) {
				smxPs[j]=Math.max(ps[j], smxPs[j+1]);
			}
			
			int pmnPs[] = new int[n];
			pmnPs[0]=ps[0];
			for(int j=1;j<n;j++) {
				pmnPs[j]=Math.min(ps[j], pmnPs[j-1]);
			}
			
			boolean works=false;
			// find min of ps[y] for y from 0 thru n-k instead of
			// looping thru each possible index y. then check if there
			// exists an x s.t. ps[x]-ps[y]>=0 and x-y+1>=k
			//  ^ Do this by checking if smxPs[y+k-1]>=ps[y]
			for(int y=0;y<n-k;y++) {
				if(smxPs[y+k-1]>pmnPs[y]) {
					works=true;
					break;
				}
			}
			
			if(works) {
				low=med;
			}
			else {
				high=med-1;
			}
		}

		out.println(low);

		in.close();
		out.close();
	}
}

// sample test cases:
//8 3
//3 0 2 5 3 0 6 3

//6 3
//0 6 3 0 0 0 0