package codeTigerCodeforcesContest;
/* complete & p ez
 * 
 */
import java.util.*;

public class practice3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int j=0;j<t;j++) {
			int n = in.nextInt(); // # of wheels
			int[] wheels = new int[n];
			for(int k=0;k<n;k++) wheels[k]=in.nextInt();
			for(int k=0;k<n;k++) { // find org # on each wheel
				int nn=in.nextInt(); // # of moves
				char[] moves = in.next().toCharArray();
				for(int i=0;i<nn;i++) {
					if(moves[i]=='D')wheels[k]++;
					else wheels[k]--;
				}
				wheels[k]=(wheels[k]+1000)%10;
				// print
				System.out.print(wheels[k]+" ");
			}
			System.out.println();
			
		}
		
		in.close();
	}
}
