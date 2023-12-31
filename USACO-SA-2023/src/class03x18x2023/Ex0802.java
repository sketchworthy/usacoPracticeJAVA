package class03x18x2023;
/* USACO Silver 2018 Jan Problem 1 Lifeguards 
 * 
 * og idea: (implemented in Ex0802)
 * loop thru all cows and keep a set of what total times they take up.
 * then, loop thru each cow, making a copy of the set each time, and check
 * which times they are cut out of if only looking at times covered by looping
 * thru all other cows. complexity: N^4 (shudders. jesus. does NOT work)
 * 
 * class idea: (implemented in Ex0802v2)
 * Simplify this by only keeping track of a lifeguard's startpoints
 * and endpoints (idk why i didn't think of this sooner).
 * To even FURTHER simplify this, look at startpoints as endpoints as well.
 * then u have an array of size [n*2][2] that stores the time/index of the xth
 * endpoint in [x][0] and the cow num in [x][1]. then u can sort by time,
 * and find what the min amnt of time lost is when just 1 cow index is ignored
 * then, loop thru the endpoints in ascending order, keeping track of each
 * cow's time on their own w an array. to keep track of the cows currently
 * on deck, use a hashset.
 * 
 * difficulty: the ideas to simplify this problem are a bit hard to find, but
 * mb that's just bc my first instinct upon seeing a problem isn't to find
 * a data structure, its to mad bash. the implementation wasnt too bad i think
 */
import java.util.*;
import java.io.*;

public class Ex0802 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("lifeguards.out"));
		int n = Integer.parseInt(in.readLine());
		int[][] cows = new int[n][2]; // x,0 is start index; x,1 is end index
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			cows[j][0]=Integer.parseInt(st.nextToken()); // start index
			cows[j][1]=Integer.parseInt(st.nextToken()); // end index
		}
		HashSet<Integer> s = new HashSet<>();
		for(int j=0;j<n;j++) { // put all cows into the set
			for(int x=cows[j][0];x<cows[j][1];x++) {
				s.add(x);
			}
		}
		long maxCowCoverage=0; // max amnt n-1 cows can cover
		for(int c=0;c<n;c++) { // for each cow, check how much all the other
														// cows cover
			s.clear();
			for(int c2=0;c2<n;c2++) {
				if (c2==c)continue;
				for(int x=cows[c2][0];x<cows[c2][1];x++) {
					s.add(x);
				}
			}
			maxCowCoverage=Math.max(maxCowCoverage, s.size());
		}
		out.print(maxCowCoverage);
		
		in.close();
		out.close();
	}
}
