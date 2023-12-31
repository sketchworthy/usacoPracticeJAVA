/*Done in unanticipated way, mapping unit needs review
 * idea is basically to make a frequency table to lower # of loop layers, except it's a 'last occurency' table. 
 * possible bc the max size is only 1 mil, which is small enough to make a space for each
 * value
 */
import java.util.*;
public class Ex0401 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		int[] last = new int[1_000_001]; // last[x] is last index occurrence of x card value in A[] 
		Arrays.fill(last, -1);
		for(int j=0;j<n;j++) {
			A[j]=in.nextInt();
		}
		
		int minL = n+1;
		for(int j=0;j<n;j++) {
			int x = A[j];
			if(last[x]!=-1) {
				// x has been seen before
				minL=Math.min(minL, j-last[x]+1);
			}
			last[x]=j;
		}
		
		System.out.println(minL);
		
//		for(int i=0;i<n;i++) {
//			System.out.println(A[i]+" ");
//		}
		in.close();
	}

}
