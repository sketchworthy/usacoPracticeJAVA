/*
 * 
 */
import java.util.*;

public class Hoofball {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] distances = new int[n];
		for(int j=0;j<n;j++)	distances[j]=in.nextInt();
		Arrays.sort(distances); // sort cows from closest to barn to farthest from barn
		System.out.println(Arrays.toString(distances));
		// arraylist of differences
		ArrayList<Integer> diffs = new ArrayList<Integer>();
		for(int j=1;j<n;j++)	diffs.add(distances[j]-distances[j-1]);
		
		for(int j=0;j<diffs.size();j++) {
			System.out.print(diffs.get(j)+" ");
		}
		System.out.println();
		
		// start from endpoints
		diffs=(removeEnds(diffs));
		for(int j=0;j<diffs.size();j++) {
			System.out.print(diffs.get(j)+" ");
		}
		
		
		in.close();
	}
	
	public static ArrayList<Integer> removeEnds(ArrayList<Integer> diffs){
		// removes the cows at the ends of the arraylist of differences
		//   first index in arraylist is the cnt of balls added, 
		//     remove it when you get the function result
		int cnt=0;
		
		if(diffs.isEmpty()) {
			diffs.add(cnt); // TODO add a variable that shows you added 0 balls
			return diffs;
		}
		if(diffs.size()==1 || diffs.size()==2) {
			cnt++; //TODO show you add 1 ball
			ArrayList<Integer> x = new ArrayList<Integer>();
			x.add(cnt);
			return x;
		}
		// check right end
		// flip array and use the 'check left end' code
		int n = diffs.size();
		ArrayList<Integer> diffs2 = new ArrayList<Integer>();
		// copy elements from diffs to diffs2
		for(int j=0;j<n;j++) {
			diffs2.add(diffs.get(j));
		}
		diffs.clear();
		// flip diffs
		for(int j=n-1;j>=0;j--)diffs.add(diffs2.get(j));
		// check left end code
		if(diffs.get(0)>diffs.get(1)) { // give first cow a ball
			// remove first few descending diffs
			int cVal=diffs.get(0); // val of last index
			int ci=1; // index ur comparing
			while(cVal>diffs.get(ci)&&ci<diffs.size()) {
				cVal=diffs.get(ci); ci++;
			}
			if(ci==diffs.size())diffs.clear(); // if empties entire arraylist
			else { // remove first ci+1 elements from diffs
				for(int j=0;j<ci;j++) {
					System.out.println("removing "+diffs.get(0));
					diffs.remove(0);
				}
			}
			cnt++; System.out.println("add 1");// TODO add a variable that shows you added 1 (more) ball
		}
		System.out.println("testing print");
		for(int j=0;j<diffs.size();j++) {
			System.out.print(diffs.get(j)+" ");
		}
		System.out.println("end");
		
		// flip diffs back TODO: NOT WORKING FOR SM REASON
		diffs2.clear();
		for(int j=0;j<diffs.size();j++) {
			diffs2.add(diffs.get(j));
		}
		
		System.out.println(diffs2.get(diffs2.size()-1)+" "+(diffs2.size()-1));
		diffs.clear();
		for(int j=diffs2.size()-1;j>=0;j--) {
			diffs.add(diffs2.get(j));
		}
		
		System.out.println("testing print");
		for(int j=0;j<diffs.size();j++) {
			System.out.print(diffs.get(j)+" ");
		}
		System.out.println("end");
		
		if(diffs.isEmpty()) { System.out.println("empty");
			diffs.add(cnt); // TODO add a variable that shows you added 0 balls
			return diffs;
		}
		
		if(diffs.size()==1 || diffs.size()==2) {
			ArrayList<Integer> x = new ArrayList<Integer>();
			cnt++; //TODO show you add 1 ball
			x.add(cnt); 
			return x;
		}
		System.out.println("Left end!");
		
		
		// check left end
		if(diffs.get(0)>diffs.get(1)) { // give first cow a ball
			// remove first few descending diffs
			int cVal=diffs.get(0); // val of last index
			int ci=1; // index ur comparing
			while(cVal>diffs.get(ci)&&ci<diffs.size()) {
				cVal=diffs.get(ci); ci++;
			}
			if(ci==diffs.size())diffs.clear(); // if empties entire arraylist
			else { // remove first ci+1 elements from diffs
				for(int j=0;j<ci;j++) {
					System.out.println("removing "+diffs.get(0));
					diffs.remove(0);
				}
			}
			cnt++;// TODO add a variable that shows you added 1 (more) ball
		}
		
		if(diffs.size()==1)diffs.clear(); // last one is left over if both left & right end have been cleared
		ArrayList<Integer> returning = new ArrayList<Integer>();
		returning.add(cnt);
		for(int j=0;j<diffs.size();j++)returning.add(diffs.get(j));
		return returning;
	}
}
