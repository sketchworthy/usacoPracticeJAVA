/* basic idea:
 * recursion. first, set your sights on the goal of getting to the party.
 * determine the shortcut that will give you min distance of getting to party (or if you'll
 * take a shortcut at all). then, set your sights on getting to that shortcut.
 * continue until you can't use another shortcut to shorten your steps
 */
import java.util.*;
public class partyTianyiZhou {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of telepaths
		int x = in.nextInt(); // ult goal position
		int[][] tps = new int[n][2]; //tps0,0 is startpoint, tps0,1 is endpoint
		for(int j=0;j<n;j++) {
			tps[j][0]=in.nextInt();
			tps[j][1]=in.nextInt();
		}
		System.out.println(moveTo(x,1,tps));
		in.close();
	}
	public static int moveTo(int goal, int p, int[][] tps) {
		// function gives you min distance it will take to move to goal from p
		int cnt = 0; // distance that will be returned
		
		int[] tpDs = new int[tps.length]; // tpDs[x] tracks distance from p to goal
		//  using the x shortcut
		int minD = Integer.MAX_VALUE; // minimum distance taking a shortcut
		int m = -1; // shortcut address
		for(int j=0;j<tps.length;j++) {
			// this distance is the distance from p to tps[j][0] + tps[j][1] to goal
			tpDs[j]=Math.abs(tps[j][0]-p)+Math.abs(goal-tps[j][1]);
			if(tpDs[j]<minD) { // shortest path using shortcut
				minD=tpDs[j]; m = j;
			}
		}
		// check if faster to take shortcut or walk
		if(goal-p<minD) { // if walking is faster
			return goal-p;  // ...just walk
		}
	// else: if you take a shortcut, run this function again with the goal as the startP
			//    of the shortcut, and eventually add it to the distance goal-endP
		cnt+=moveTo(tps[m][0],p,tps);
		cnt+=Math.abs(goal-tps[m][1]);
		return cnt;
		
		// find shortest path to goal, whether that be through a shortcut or just walking
		//    there
		// if you just walk there, return the goal-p
		// if you take a shortcut, run this function again with the goal as the startP
		//    of the shortcut, and eventually add it to the distance goal-endP
		
		
	}	
}
