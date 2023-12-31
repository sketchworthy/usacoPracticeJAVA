/* annoying to code
 * decently easy, idea super simple
 */
import java.util.*;

public class Ex0201 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		int[] steps = new int[n-1];
		for(int j=0;j<n-1;j++)
			steps[j]=in.nextInt();
		Arrays.fill(A, 0);
//		System.out.println(Arrays.toString(A));
//		System.out.println(in.next());
		
		int cI=1; // current index
//		System.out.println("lol");
		for(int j=0;j<n-1;j++) { // remove n-1 ppl
			int step = steps[j];
//			System.out.println(step);
			while (step>1) {
//				System.out.println("Step: "+step+". cI: "+cI+". A[cI]="+A[cI]);
				if(A[cI]==0)
					step--;
				// shift cI
				if(cI==n-1) cI=0;
				else cI++;
			}
			while(A[cI]!=0) { // make sure you're removing a person 
				// who isn't already removed
				if(cI==n-1) cI=0;
				else cI++;
			}
			A[cI]++;
//			System.out.println(Arrays.toString(A));
		}
//		System.out.println("lol2");
		// search array for 0
		for(int j=0;j<n;j++) {
			if(A[j]==0) {
				System.out.println(j+1); // print ID
				break;
			}
		}
		
		
		in.close();
	}
}
