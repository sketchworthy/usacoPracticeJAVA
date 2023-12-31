// twas the 1st draft of my solution to this problem--helped me figure out the cow-jump
//   quiz question
/* basic idea:
 * recursion. first, set your sights on the goal of getting to the party.
 * determine the shortcut that will give you min distance of getting to party (or if you'll
 * take a shortcut at all). then, set your sights on getting to that shortcut.
 * continue until you can't use another shortcut to shorten your steps
 */
import java.util.*;
public class partyTianyiZhou2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of tps
		int x = in.nextInt(); // distance to party
		int[][] tps = new int[n+1][2]; // tps[0][0] = tp starting point, 0,1 ending point
		for(int j=0;j<n;j++) {
			tps[j][0]=in.nextInt();
			tps[j][1]=in.nextInt();
		}
		for(int j=0;j<n;j++) {
			for(int k=0;k<2;k++) {
				System.out.print(tps[j][k]+" ");
			}
			System.out.println();
		}
		// goal is to get to x
		int[] tpDs = new int[n]; // tpDs[x]=how much distance will take to get to party
		// using this shortcut
		int p = 1; // current position
		int d = x; // current distance from party
		int cnt = 0; // distance traveled so far
		while(d>0) {
			System.out.println("P: "+p+" x: "+x);
			// update tpDs
			int minD=Integer.MAX_VALUE;
			int mintp=0;
			for(int j=0;j<n;j++) {
				tpDs[j]=Math.abs(tps[j][0]-p)+Math.abs(x-tps[j][1]);
				if(tpDs[j]<minD) { // adjust minD and mintp if necessary
					mintp=j; minD=tpDs[j];
				}
				// new goal: try to move to mintp
				System.out.println("mintp: "+mintp+" minD: "+minD);
			}
			// move in the most advantageous way
						// update cnt, then p and d
			if (minD<d){ // if you take a tp
				cnt+=Math.abs(tps[mintp][0]-p);
				p=tps[mintp][1]; 
				d=Math.abs(x-p); // position moves to the endpoint of the best tp
				System.out.println("cnt: "+cnt);
			}
			else {
				cnt+=d;
				System.out.println("d: "+d);
				d=0;
			}
			
		}
		System.out.println(cnt);
		/*
		 * we want to get from 0 to x-1
		 * we have some possible shortcuts that can lower our distance by x depending on
		 * distance
		 * for instance if we are at point j
		 * and theres a tp from x1 to x2
		 * then our distance using that shortcut would be Math.abs(x1-j)+Math.abs(x2-j)
		 * 
		 */
		in.close();
	}
	public static int moveTo(int d, int p, int x, int[] tpDs,int n, int[][] tps) {
		// moves from p a distance of d to x, returns a cnt of how many steps were taken
		int cnt=0;
		System.out.println("P: "+p+" x: "+x);
		// update tpDs
		int minD=Integer.MAX_VALUE;
		int mintp=0;
		for(int j=0;j<tpDs.length;j++) {
			tpDs[j]=Math.abs(tps[j][0]-p)+Math.abs(x-tps[j][1]);
			if(tpDs[j]<minD) { // adjust minD and mintp if necessary
				mintp=j; minD=tpDs[j];
			}
			// new goal: try to move to mintp-th tps
			moveTo(Math.abs(tpDs[mintp]-p), p,tpDs[mintp],tpDs,n,tps);
			System.out.println("mintp: "+mintp+" minD: "+minD);
		}
		// move in the most advantageous way
					// update cnt, then p and d
		if (minD<d){ // if you take a tp
			cnt+=Math.abs(tps[mintp][0]-p);
			p=tps[mintp][1]; 
			d=Math.abs(x-p); // position moves to the endpoint of the best tp
			System.out.println("cnt: "+cnt);
		}
		else {
			cnt+=d;
			System.out.println("d: "+d);
			d=0;
		}
		return cnt;
	}
}
