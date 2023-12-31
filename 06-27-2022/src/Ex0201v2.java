/* finished, done ez
 * basic idea: sort, then loop with pointer variables
 */
import java.util.*;

public class Ex0201v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); int m = in.nextInt();
		int[] A = new int[n];
		int[] B = new int[m];
		for(int i=0;i<n;i++)
			A[i]=in.nextInt();
		for(int j=0;j<m;j++)
			B[j]=in.nextInt();
		Arrays.sort(A);Arrays.sort(B);
//		for(int j=0;j<m;j++) {
//			System.out.print(B[j]+" ");
//		}
		in.close();
		// pointer variables
		int j=0; // index in A
		int k=0; // index in B
		while(j<n&&k<m) {
//			System.out.println(j+"+"+k);
			if(A[j]<B[k]) j++;
			else if(A[j]>B[k]) k++;
			else {
				System.out.print(A[j]+" ");
				j++;k++;
			}
		}
	}
}
