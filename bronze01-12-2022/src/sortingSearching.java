/*
 * https://us02web.zoom.us/rec/play/XbxDedjz63kS_4whWXiV2mlXAEinhpkORWhusXUTBcHoJ3MBnQYUW3eFJe33KWsOSuDhMJPbBTU2TDcH.zDP_0e6G_tYAnJGP?continueMode=true&_x_zm_rtaid=R_X0WyVSQkKb7SoSC4MruQ.1644795548324.2a122c540a2e52f36c4215b0fc8cabb9&_x_zm_rhtaid=847
 */
import java.util.*;
public class sortingSearching {
	public static void main(String[] args) {
		Random gen23 = new Random(350);
		System.out.println(gen23.nextInt());
		Random gen = new Random(1);
		int n=10;
		int[]A=new int[n];
		// assign A[i]
		for(int i=0;i<n;i++) {
			A[i]=gen.nextInt();
		}
		System.out.println(Arrays.toString(A));
		Arrays.sort(A);
		System.out.println(Arrays.toString(A));
		
		// Collections.sort(B);
		
		Integer[] C = new Integer[n];
		for(int j=0;j<n;j++) {
			C[j]=gen.nextInt(20);
		}
		System.out.println(Arrays.toString(C));
		Arrays.sort(C,(x1,x2) -> x1-x2); // sort in ascending order
	          // comparison function: if x1-x2 is neg, x1 goes first (x1 and x2 are elements in C)
		System.out.println(Arrays.toString(C));
		
		Arrays.sort(C,(x1,x2) -> x2-x1); // sort in descending order
		System.out.println(Arrays.toString(C));
		
		// sorting for 2D arrays
		int[][]D = new int[n][2];
		for(int j=0;j<n;j++) {
			System.out.println(Arrays.toString(D[j]));
		}
		
//		// sort by ascending y coordinate
//		System.out.println();
//		Arrays.sort(D,(e1,e2) -> e1[1]-e1[2]);
//		for(int j=0;j<n;j++) {
//			System.out.println(Arrays.toString(D[j]));
//		}
	}
}
