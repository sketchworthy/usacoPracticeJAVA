/* NEEDS REVIEW
 * basic idea pretty ez to find, implementation was HARD and I got stuck
 * basic idea: simulation, only keep track of the # of passengers at each point where 
 *  someone gets on or gets off

 */
import java.util.*;
public class Ex0902 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt(); // # of queries
		for(int i=0;i<q;i++) {
			int n = in.nextInt(); // # of passenger groups
			int c = in.nextInt(); // bus capacity
			int[] f = new int[1_000_001]; // # of passengers at any location
			int[][] groups = new int[n][3]; // groups[0][0] is # of passengers
			// groups[0][1] is pickup, 0,2 is dropoff
			// fill groups array
			for(int j=0;j<n;j++) {
				for(int k=0;k<3;k++)
					groups[j][k]=in.nextInt();
				
				f[groups[j][1]] += groups[j][0];
				f[groups[j][2]] -= groups[j][0];
			}
//			Arrays.sort(groups, (r1,r2) -> r1[1] - r2[1]);
//			int p = 0; // bus current population
			boolean valid=true;
			for(int x=1;x<=1_000_000;x++) {
				f[x]=f[x]+f[x-1]; // carry over
				if(f[x]>c) {
					valid = false;
					break;
				}
			}
			
			
			
			
			
			
//			int[][] locs = new int[groups[n-1][1]][2]; // locs[0][0]=location/possible stop
//			// point, locs[0][1]=1 -> pickup, 0,1=2 -> dropoff
			
			
			
//			ArrayList<ArrayList<Integer>> dropLocs = new ArrayList<ArrayList<Integer>>();
//			//drop locations of current groups on bus
//			//  dropLocs<0<0>> = drop location, dropLocs<0,1> is group size
//			int l = 0; // bus current location
//			int t = 0; // current task: 0 = pickup, 1=dropoff
//			boolean valid = true;
//			while(l<=groups[n-1][1]) {
//				// change location to next pickup/dropoff location
//				// next pickup location is groups[
//				
//				// 
//				p+=groups[][0];
//				if(p>c) {valid=false; break;} // if overcapacity
//				
//				
//			}
//			
			
			System.out.println(valid);
		}
		in.close();
	}
}
