package class01x14x2023makeup;
/* n points on a grid. find the min area of fence u need if you can remove up to 3 points
 * at max. points can be on a fence. fencing is rectangular.
 * 
 * key idea: you only need to remove 3 cows from each side at most. we can cleverly look
 * at this calculation and account for overlap by setting 'bounds' at the 3
 * leftmost,upmost,rightmost, or downmost points, then looping thru the points to check if
 * there are 3 or less points present to check if its a valid solution
 * then at the end return the min area
 * 
 * difficulty: once i got the idea, implementation ez, but i did watch the class solution
 * walkthru so ye. i originally realized you only need to remove 3 cows from each side at
 * most but couldn't figure out the trick for how to count all of those while also accounting
 * for overlap
 */
import java.util.*;
import java.io.*;

public class HW0102 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new FileWriter(new File("reduce.out")));
		int n = Integer.parseInt(in.readLine());
		int[][] cowLocs = new int[n][2]; // x & y positions of cows
		int[] x = new int[n]; // x coords
		int[] y = new int[n]; // y coords
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			cowLocs[j][0]=Integer.parseInt(st.nextToken());
			x[j]=cowLocs[j][0];
			cowLocs[j][1]=Integer.parseInt(st.nextToken());
			y[j]=cowLocs[j][1];
		}
		Arrays.sort(x);
		Arrays.sort(y);
		if(n<=3)out.println(0);
		else {
			// x[] & y[] stores 3 leftmost, 3 rightmost, 3 upmost, & 3 downmost cows
			// for each set of points in/on x[i]...x[j] and y[k]...y[l]
			//  where 0<=i<=3 and n-4<=j<=n-1, 0<=k<=3 & n-4<=l<=n-1
			//   check if there are >=n-3 cows present. if yes, set area to min
			long minAns=Long.MAX_VALUE; // min area FJ can enclose w fence
			for(int i=0;i<=3;i++) {
				for(int j=n-4;j<=n-1;j++) {
					for(int k=0;k<=3;k++) {
						for(int l=n-4;l<=n-1;l++) {
//							check if there are >=3 cows not present. if not, set area to min
							int leftbound=x[i];
							int rightbound=x[j];
							int downbound=y[k];
							int upbound=y[l];
							int notPresent=0;
							for(int cow=0;cow<n;cow++) {
								if(cowLocs[cow][0]<leftbound||cowLocs[cow][0]>rightbound
										|| cowLocs[cow][1]<downbound||cowLocs[cow][1]>upbound) {
									notPresent++;
								}
							}
							long cArea=(upbound-downbound)*(rightbound-leftbound);
							if(notPresent<=3) {
								minAns=Math.min(minAns, cArea);
							}
						}
					}
				}
			}
			out.print(minAns);
		}
		in.close();
		out.close();
	}
}
