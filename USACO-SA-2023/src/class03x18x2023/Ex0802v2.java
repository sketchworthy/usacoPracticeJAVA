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
 * a data structure, its to mad bash. the implementation wasnt too bad i think.
 * i just need to think of a way to iterate in a LINEAR fashion
 */
import java.util.*;
import java.io.*;

public class Ex0802v2 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lifeguards.out"));
		int n = Integer.parseInt(in.readLine()); // # of cows
		int n2 = n*2; // # of endpoints, including startpoints
		int[][] pts = new int[n2][2]; // x,0 is time; x,1 is cow num/ID
		for(int j=0;j<n2;j+=2) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			pts[j][0]=Integer.parseInt(st.nextToken()); // start index
			pts[j][1]=j/2; // cow id
			pts[j+1][0]=Integer.parseInt(st.nextToken()); // end index
			pts[j+1][1]=j/2; // cow id
		}
		Arrays.sort(pts,(x1,x2)->x1[0]-x2[0]); // sort in ascending time order
		// loop through time-sorted endpoints, keep track of cows on deck w
		//  a hashset, keep track of each cow's time being only 1 on duty w
		//   n-sized array
		HashSet<Integer> present = new HashSet<>(); // nums of cows present
		int[] indivTime= new int[n]; // [x] is the time xth cow works alone
		int lastPoint=0; // last endpoint
		int totalTime=0; // total time at least 1 cow covers
		for(int c=0;c<n2;c++) { // loop thru endpoints, update sets and arrs
			if(present.isEmpty()) {
				present.add(pts[c][1]);
			}
			else if(present.size()==1) { // 1 guard on duty
				if(!present.contains(pts[c][1])) {
					for(int cID:present) {
						indivTime[cID]+=pts[c][0]-lastPoint;
					}
					present.add(pts[c][1]);
				}
				else {
					indivTime[pts[c][1]]+=pts[c][0]-lastPoint;
					present.remove(pts[c][1]);
				}
				totalTime+=pts[c][0]-lastPoint;
			}
			else { // multiple guards on duty
				if(!present.contains(pts[c][1])) { // add c, all other lifeguards update
					present.add(pts[c][1]);
				}
				else {
					present.remove(pts[c][1]);
				}
				totalTime+=pts[c][0]-lastPoint;
			}
			lastPoint=pts[c][0];
		}
		int minAlone=indivTime[0];
		for(int j=1;j<n;j++) {
//			out.println(indivTime[j]);
			minAlone=Math.min(minAlone, indivTime[j]);
		}
		out.print(totalTime-minAlone);
		
		in.close();
		out.close();
	}
}
