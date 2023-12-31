/* hoofball USACO 2018 feb bronze, done w recursion
 * code doesn't work--recursion wasn't done quite right
 */
import java.util.*;
import java.io.*;

public class Ex1101 {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("hoofball.out"));
		Scanner in = new Scanner(new File("hoofball.in"));
		int n = in.nextInt(); // # of cows
		int[] cows= new int[n]; // distances of cows from barn
		for(int j=0;j<n;j++) {
			cows[j]=in.nextInt();
		}
		Arrays.sort(cows);
//		System.out.println(Arrays.toString(cows));
		int cnt=0;
		
		int[] visited=new int[n];
		Arrays.fill(visited, 0);
		// if visited[x] gets visited when starting pos p isn't x, then it's not a head
		//  (that is unless it's a 2-cow group where they'll pass to each other for eternity)
		for(int p=0;p<n;p++) {
			ArrayList<Integer> traced=new ArrayList<Integer>();
			traced=trace(p,cows,traced);
//			if(traced.size()==2) { // (if it's a 2-cow group who'll pass to each other for eternity)
//				cnt++;
//			}
			for(int i:traced) { // for index i in traced
				visited[i]++;
			}
		}
		System.out.println(Arrays.toString(visited));
		for(int j=0;j<n;j++) {
			if(visited[j]==0)cnt++;
			else if(visited[j]==1&&j+1<n&&visited[j+1]==1) {
				cnt++;
				visited[j+1]=-1;
			}
		}
		
		
		out.print(cnt);
		
		in.close();
		out.close();
	}
	
	public static ArrayList<Integer> trace(int p, int[] cows, ArrayList<Integer> traced){
		// w/ a ball starting from position p, trace all cows that will be reached by the ball
		// returns arrayList of indices of cows visited

		int i; // index about to be added to the arrayList
		// determine if ball is going left or right
		if(p==0) i=1;
		else if(p==cows.length-1)i=p-1;
		else { // if p is not on the end
			// left distance & right distance
			int lD=cows[p]-cows[p-1];
			int rD=cows[p+1]-cows[p];
			if(lD<=rD)i=p-1;
			else i=p+1;
		}
		
		if(traced.contains(i))return traced;
		else {
			traced.add(i);
			traced=trace(i,cows,traced);
			return traced;
		}
	}
}
