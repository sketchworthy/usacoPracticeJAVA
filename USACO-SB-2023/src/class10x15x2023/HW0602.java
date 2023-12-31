package class10x15x2023;
/* USACO 2016 Mar Silver DONE BUT SLOW VER. SEE HW0602v2 FOR TRUE/FAST SOL
 * http://usaco.org/index.php?page=viewproblem2&cpid=643
 * Given N #s, find the max # of them that can be
 * contained in 2 spans of K
 * 
 * idea: make a freq arrlist of the sizes.
 * Actually not even that: make a prefix freq
 * treemap of the sizes. for ex,
 * if u wanted to represent 2,2,3,4,4 you'd have
 * [[2,2],[3,3],[4,5]] to represent the indices/freqs
 * you end at. then if u want to find the amount of
 * #s that would be in the span defined by 2 to 2+k,
 * you would just do floor func.
 * then, just find the 2 spans whose
 * union has the max amount of #s contained
 * by looping thru, for each span1 you iterate
 * a span2 whose starpoint is span1's startpoint+k+1,
 * until u find ur max
 * 
 * difficulty: i enjoyed working thru this problem, but
 * this solution with the O(N^2) looping is still too slow
 * for 2 test cases. NOTE: THIS WAS VER1, MY VERSION
 * 
 */
import java.util.*;
import java.io.*;

public class HW0602 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new FileWriter("diamond.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of diamonds
		int k = Integer.parseInt(st.nextToken()); // max # 2 sizes in a case can differ by
		TreeMap<Integer,Integer> pfreq = new TreeMap<>();
		for(int j=0;j<n;j++) {
			int x=Integer.parseInt(in.readLine());
			pfreq.put(x, pfreq.getOrDefault(x, 0)+1); // not prefi yet, but is freq
		}
		for(Integer x:pfreq.keySet()) { // convert into PREFIX freq
			if(pfreq.lowerKey(x)!=null)
				pfreq.put(x, pfreq.get(x)+pfreq.get(pfreq.lowerKey(x))); // add to prev
		}
		int max = 1;
		
		// create array holding #s in a span from x to x+k
		HashMap<Integer,Integer> span = new HashMap<>();
		for(int a:pfreq.keySet()) {
			if(pfreq.lowerKey(a)!=null)
				span.put(a,pfreq.get(pfreq.floorKey(a+k))-pfreq.get(pfreq.lowerKey(a)));
			else span.put(a,pfreq.get(pfreq.floorKey(a+k)));
		}
		
		// loop thru and get the max amount of #s possible to be contained in 2 spans
		for(int a:pfreq.keySet()) {
			for(int b:pfreq.keySet()) {
				if(a+k<b) { // no overlap	
					// # of ints in a span from a to a+k is
					//  pfreq.get(pfreq.floorKey(a+k))-pfreq.getOrDefault(pfreq.lowerKey(a),0)
					max=Math.max(max,span.get(a)+span.get(b));
				}
			}
		}
		
		out.print(max);
	
		in.close();
		out.close();
	}
}
