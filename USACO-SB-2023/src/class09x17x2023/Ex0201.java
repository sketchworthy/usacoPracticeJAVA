package class09x17x2023;
/* USACO 2019 Jan Silver P3: Mountain View COMPLETE*, NEED TO REVIEW
 * Given several x,y points of mountain peaks where every mountain is a 45-45-90
 * triangle sitting on its hypotenuse along the x axis, figure out how many
 * triangles aren't overshadowed by other triangles.
 * 
 * idea: define each mountain not by its peaks, but instead by its leftmost
 * base point and its rightmost base point. then, iff a mountain's
 * 2 base endpoints are ENTIRELY engulfed in another mountain's base endpoints,
 * the mountain is invisible. to find the # of mountains we then sort and
 * simulate. i thought of a bunch of bogus solutions to solve this problem
 * then, including sorting w O(n^2) time and sorting mountans by endpoint
 * and checking if overshadowed stuff but that still took O(n^2) time bc i 
 * had to also keep iterating to find if the startpoint was first in line or
 * nah. eventually i gave up, so TODO come back to this and review IN CPP. 
 * and SEE RECORDING: 
 * https://us02web.zoom.us/rec/play/8RK_xzNSeWSymkzLcRRCMAd4U_dXca6AICOROF1oSV4BM0CAHEg3pgpg0t5d6ga2kGVDLKhS2YGZTsGO.u6tXNrTyQIN9F5xp?canPlayFromShare=true&from=share_recording_detail&continueMode=true&componentName=rec-play&originRequestUrl=https%3A%2F%2Fus02web.zoom.us%2Frec%2Fshare%2F97_c8yDpD4fkfGQsJqnTWlk3YmSbL5FDRBADERWd81xtu5UHc8vE5Tt88x4xryrf.3ZiO6TzkFdq0_9G9
 * for cpp lambda expression!
 * sort first by startpoint, then if startpoint is the same look at endpoint (use 1-line
 * conditions in comparator). then... literally just go thru and iterate once
 * thru it. thats it. it that simple.
 * 
 * difficulty: honestly, idk if i wouldve found the strat if teacher didn't just
 * tell us straight up. its important to remember when we're iterating thru a
 * row of stuff with startpoints and endpoints that it can often be helpful
 * to sort by first endpoint, then go by startpoint, to see which intervals will
 * swallow other intervals. its also rlly important to figure out the RIGHT
 * sorting method for a problem quickly, lest you waste a lot of time
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0201 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		BufferedReader in = new BufferedReader(new FileReader("mountains.in"));
		PrintWriter out = new PrintWriter(new FileWriter("mountains.out"));
		int n=Integer.parseInt(in.readLine()); // # of mountains
		int[][] peaks = new int[n][2]; //l,r (leftmost base point, rightmost base point)
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int x=Integer.parseInt(st.nextToken()); // x
			int y=Integer.parseInt(st.nextToken()); // y 
			peaks[j][0]=x-y; // l
			peaks[j][1]=x+y; // r
		}
		Arrays.sort(peaks,(x1,x2)->
			x1[0]!=x2[0] ? (x1[0]-x2[0]) : (x2[1]-x1[1]) ); 
		// sort in ascending order by leftpoint, if theyre equal sort DESCENDING by rightpoint
		
		int farthestr=-1; // farthest right point currently encountered
		
		int total=0; // # of mountains bess can see
		for(int j=0;j<n;j++) {
			if(peaks[j][1]>farthestr) {
				farthestr=peaks[j][1];
				total++;
			}
		}
		out.print(total);
		
		in.close();
		out.close();
	}
}
