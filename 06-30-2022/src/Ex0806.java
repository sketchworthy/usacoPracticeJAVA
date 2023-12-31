/* hard problem, redo from scratch once fully understood
 * gives bugged output, but basic idea is to split cows into 2 groups (by direction) 
 * and compare each e-cow to each n-cow
 * separate cows into 2 groups
 */
import java.util.*;
public class Ex0806 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows
		int[][] cows = new int[n][5];
		// cows[j][0]: direction 0 == east, 1 == north
		// [j][1] is x-coord, [j][2] is y-coord
		// [j][3] is distance traveled
		int cntE = 0; // # of cows going east
		for(int j=0;j<n;j++) {
			if(in.next()=="N") cows[j][0]=1;
			else {
				cows[j][0]=0; cntE++;
			}
			cows[j][1]=in.nextInt();
			cows[j][2]=in.nextInt();
			cows[j][3]=Integer.MAX_VALUE;
			cows[j][4]=j; // original order
		}
		// separate cows into 2 groups: eastbound+northbound
		// done by sorting by directions
		Arrays.sort(cows, (r1,r2) -> r1[0] - r2[0]); // east cows come first
		// sort group1 (e-headed cows) by y coords
		Arrays.sort(cows, 0, cntE, (r1,r2) -> r1[2] - r2[2]);
		// sort group2 by x coords
		Arrays.sort(cows, cntE, n, (r1,r2) -> r1[1] - r2[1]);
		
		// see cow collisions and cancel em out
		// compare cow j from e cows and cow k from n cows
		for(int j=0;j<cntE;j++) { // eastbound cow
			for(int k=cntE;k<n;k++) { // n bound cow
				// check if both are unblocked
				if(cows[j][3]<Integer.MAX_VALUE||cows[k][3]<Integer.MAX_VALUE) continue;
				// collision point for ecow (xe,ye) and ncow (xn,yn) is (xn,ye)
				// distance for ecow is xn-xe, distance for ncow ye-yn
				int eD = cows[k][1]-cows[j][1];
				int nD = cows[j][2]-cows[k][2];
				// compare to see which one is larger
				// if the larger one is negative then they never meet
				if(Math.max(eD, nD)<0) continue;
				// if theyre the same then they dont collide
				if(eD==nD) continue;
				// larger one gets set a distance traveled
				if(nD>eD) cows[k][3]=nD;
				else cows[j][3]=eD;
			}
		}
		// sort cows back to org order
		Arrays.sort(cows, (r1,r2) -> r1[4] - r2[4]);
		for(int j=0;j<n;j++) {
			if(cows[j][3]<Integer.MAX_VALUE) {
				System.out.println(cows[j][3]);
			}
			else
				System.out.println("Infinity");
		}
		
		in.close();
	}
}
