/* finished, passed all test cases on 1st solution! :D
 * basic idea:
 * work backwards
 * from last patch, change goal to the smallest patch that can reach the last patch
 */
import java.util.*;
public class jumpTianyiZhou {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of patches
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=in.nextInt();
		}
		System.out.println(jumpTo(n-1,0,A));
		
		in.close();
	}
	public static int jumpTo(int goal, int p, int[] A){
		// given the goal to jump to, your current position, and each patch's info,
		 // return the # of jumps needed
		if(p==goal) return 0;
		else if(p+A[p]>=goal) return 1;
		int jcnt=0;
		boolean canGet=false;
		for(int j=0;j<A.length-1;j++) { // find smallest patch that will get to goal
			if(j+A[j]>=goal) {
				canGet=true;
				jcnt++;
				jcnt+=jumpTo(j,p,A);
				break;
			}
		}
		if(canGet==false) {
			return -1;
		}
		return jcnt;
	}
}
