package codeTigerCodeforcesContest;
/* complete & ez
 * 
 */
import java.util.*;

public class practice2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t=in.nextInt(); 
		
		for(int j=0;j<t;j++) {
			int cnt = 0; // # of balloons
			int n = in.nextInt();
			cnt+=n;
			char[] solves = in.next().toCharArray();
			boolean[] solved = new boolean[26];
			Arrays.fill(solved, false);
			for(int k=0;k<n;k++) {
				if (solved[solves[k]-'A']==false) {
					solved[solves[k]-'A']=true;
					cnt++;
				}
			}
			System.out.println(cnt);
			
			
			
		}
		in.close();
	}
}
