package codeTigerCodeforcesContest;
/* full contest url: https://codeforces.com/blog/entry/104786
 * #1 complete and ez 
 */
import java.util.*;

public class practice1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for(int j=0;j<n;j++) {
			String s = in.next().toLowerCase();
			if(s.equals("yes"))System.out.println("YES");
			else System.out.println("NO");
		}
		in.close();
	}
}
