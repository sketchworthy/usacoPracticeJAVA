package class10x29x2023;
/* USACO 2020 Feb Silver: Swapity Swap COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1014
 * 
 * idea: We can reduce the M swaps to one transformation.
 * After we've got down a 'transformation' array,
 * we just need to apply it to itself K times. this would run
 * out of time normally, but we can find the period of the 
 * transformations (# of transformations until the array looks
 * like the original array again) to simplify # of calculations 
 * that needs to be done. each character, when the transformation
 * is applied, has its own period that is guaranteed to be <K.
 * if we keep a list of all the disjoint cycles the indices go thru
 * when transformed over and over, we get 
 * 
 * difficulty: i immediately knew to use graphs structure to my
 * advantage bc was in BFS unit -- idk how hard the idea would be
 * to come up w on my own. but i thought to optimize the wrong part--
 * i thought i needed to optimize the part where i calculate the
 * overall transformation that is the K swaps, but actually it's
 * way more important to optimize the cyclical nature of applying
 * the transformation K times. Also i took SO LONG to implement
 * this but that's just because i was really in a rut lmao.
 * draw pictures, be careful, write stuff out to avoid
 * silly errors
 * 
 */
import java.util.*;
import java.io.*;

public class HW0802 {
	static int[] At;
	public static void main(String[] args) throws Exception {
		// read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new FileReader("swap.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("swap.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows
		int m = Integer.parseInt(st.nextToken()); // # of swaps
		int k = Integer.parseInt(st.nextToken()); // # of times the cows go thru those m-step swaps
		
		// set up data		
		int[] A = new int[n]; // will become transforming array
		At = new int[n]; // transform array 'instructions'
		for(int j=0;j<n;j++) {
			A[j]=j;
			At[j]=j;
		}
		
		for(int j=0;j<m;j++) {
			// take swaps input and make A into transformation array
			st = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(st.nextToken())-1; // L
			int r = Integer.parseInt(st.nextToken())-1; // R
			swap(l,r);
		}
		
		// apply At to arr A, K times. to do this 
		
		
		// indices cycles of each of the characters
		HashSet<Integer> elesInCycles = new HashSet<>();
		ArrayList<ArrayList<Integer>> cycles = new ArrayList<>(); 
		// loop through each og index from 0 thru n-1
		for(int i=0;i<n;i++) {
			if(elesInCycles.contains(i))continue;
			elesInCycles.add(i);
			
			// add to cycle
			int size = cycles.size();
			cycles.add(new ArrayList<>());
			cycles.get(size).add(i);
			
			int curr=A[At[i]];
			if(elesInCycles.contains(curr)) continue;
			elesInCycles.add(curr);
			cycles.get(size).add(curr);
			
			// apply At transformation until repeat
			while(true) {
				curr=A[At[curr]];
				if(elesInCycles.contains(curr)) break;
				elesInCycles.add(curr);
				cycles.get(size).add(curr);
			}
		}
		
		
		// TODO FIX BELOW PART
		// figure which character ended up at which index at the Kth execution
		int[] ans = new int[n];
		for(int i=0;i<cycles.size();i++) {
			for(int j=0;j<cycles.get(i).size();j++) {
				int periodResult = (k+j)%cycles.get(i).size();
				int jIndex = cycles.get(i).get(periodResult); // index of character j is here
				ans[cycles.get(i).get(j)]=jIndex;
			}
		}
		
		for(int j=0;j<n;j++) {
			out.println(ans[j]+1);
		}
		
		in.close();
		out.close();
	}
	public static void swap(int l, int r) {
		int temp=0;
		
		for(int j=l;j<=(l+r)/2;j++) {
			// swap j and r-(j-L)
			temp=At[j];
			At[j] = At[r-j+l];
			At[r-j+l] = temp;
		}
	}
}
