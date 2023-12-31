package class09x17x2023;
/* USACO 2020 March Silver Moo Particle COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1040
 * Ur given n coordinates (x,y). For any 2 coords (x1,y1) and (x2,y2),
 * a chosen one of the 2 may disappear if y2>y1 and x2>x1. What's the
 * min # of coords left after you remove the max # of particles?
 * 
 * idea: you need some algorithm of how to disappear the most particles
 * to organize this problem & get idea of how to implement that algorithm.
 * this problem is identical to the problem of 'find the separate 'groups'
 * of NE & SW-related nodes, each reducing to just 1 survivor. detect these
 * groups and return the # of groups.
 * However, we're doing this by sorting. So instead what we do is sort
 * by x-coord, then y-coord. What then happens is you look for the
 * sequences of points where it goes SE, meaning
 * the x is bigger but the y is smaller to any connected point.
 * you then collect a list of the smallest y-coords of each separate connected
 * component. every time u analyze a new point, you check to see where 
 * in the list the y-coord falls.
 * 
 * difficulty: i got it, but 2 test cases timed out :( edit: that was with sublist,
 * which apparently has too much of a time complexity. but just removing from
 * the end over and over went super fast and worked!!! apparently u can also
 * do this problem w sorting and a set.
 * 
 */
import java.util.*;
import java.io.*;

public class HW0202 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("moop.in"));
		PrintWriter out = new PrintWriter(new FileWriter("moop.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n=Integer.parseInt(in.readLine());
		int[][] coords = new int[n][2];
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			coords[j][0]=Integer.parseInt(st.nextToken());
			coords[j][1]=Integer.parseInt(st.nextToken());
		}
		
		// now sort and simulate
		Arrays.sort(coords,(a,b)-> a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]);
		
//		int smallestY=coords[0][1]; // smallest y coord in a connected component
		ArrayList<Integer> components = new ArrayList<Integer>(); // list of
		//  existing components' smallest y-coords. theyll be in descending order
		//    integrate a new point into this list as either the new smallest y
		//     coord, or just assimilate it w the others

		components.add(coords[0][1]);
		for(int j=1;j<n;j++) {
			int componentSmallesty = coords[j][1]; 
			for(int k=0;k<components.size();k++) {
				if(coords[j][1]>=components.get(k)) { // if this true, all indices after k also tru
					componentSmallesty=components.get(components.size()-1);
//					components.subList(k, components.size()).clear();;
//					break;
					while(components.size()>k) components.remove(components.size()-1);
//					k--;
					break;
				}
			}
			components.add(componentSmallesty);
		}
		out.print(components.size());

		in.close();
		out.close();
	}
}
