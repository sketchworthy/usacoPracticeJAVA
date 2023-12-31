/* USACO Jan 2021 Bronze
 * as current code is, can only get 5/12 test cases
 * note: the faster way to do this is to see for when the largest cow can go into any of the top k
 *  stalls bc its shorter, that means you can just multiply k by the # of possibilities when the top
 *  cow goes into any of them
 * 
 */
import java.util.*;

public class justStalling {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of stalls
		
		ArrayList<Integer> cow = new ArrayList<Integer>();
		ArrayList<Integer> stall = new ArrayList<Integer>();
		for(int j=0;j<n;j++) cow.add(in.nextInt());
		for(int j=0;j<n;j++) stall.add(in.nextInt());
		
		Collections.sort(cow);
		Collections.sort(stall);

//		for(int j=0;j<n;j++) System.out.print(cow.get(j)+" ");
//		System.out.println();
//		for(int j=0;j<n;j++) System.out.print(stall.get(j)+" ");
//		System.out.println();
		
		if(cow.isEmpty()) System.out.println("0");
		else {
			long numWays = numWays(cow,stall);
			System.out.println(numWays);}
		in.close();
	}
	
	public static long numWays(ArrayList<Integer> cow, ArrayList<Integer> stall) {
//		for(int j=0;j<cow.size();j++) System.out.print(cow.get(j)+" ");
//		System.out.println();
//		for(int j=0;j<stall.size();j++) System.out.print(stall.get(j)+" ");
//		System.out.println("\n");
		
		// note that cow and stall are sorted & have the same length
		// try placing first cow in earliest position possible
		if(cow.isEmpty())return 1;
		long possibilities = 0;
		if(cow.get(0)>stall.get(0)) return 0; // if smallest stall won't fit the smallest cow,
		//  smallest stall will go unused, meaning impossible
		if(cow.get(cow.size()-1)>stall.get(stall.size()-1)) return 0; // if largest cow too large
		
		// if largest cow must go in largest stall b/c 2nd largest stall can't fit them
		if(cow.size()>1 && cow.get(cow.size()-1)>stall.get(stall.size()-2)) {
			ArrayList<Integer> cow2 = new ArrayList<Integer>();
			cow2.addAll(cow);
			ArrayList<Integer> stall2=new ArrayList<Integer>();
			stall2.addAll(stall);
			cow2.remove(cow.size()-1);
			stall2.remove(cow.size()-1);
			return numWays(cow2,stall2);
		}
		
		
		if(cow.size()==1&&(cow.get(0)<stall.get(0)))return 1; // if just 1 possibility left
		
		// see # of ways you can fit smallest cow into a stall
		for(int j=0;j<cow.size();j++) {
			// j is the index of the stall they'll be forcing the smallest cow into
			ArrayList<Integer> cow2 = new ArrayList<Integer>();
			cow2.addAll(cow);
			ArrayList<Integer> stall2=new ArrayList<Integer>();
			stall2.addAll(stall);
			cow2.remove(0);
			stall2.remove(j);
			possibilities+=numWays(cow2,stall2);
		}
		return possibilities;
	}
}
