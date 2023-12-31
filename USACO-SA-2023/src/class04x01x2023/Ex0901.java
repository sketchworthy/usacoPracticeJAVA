package class04x01x2023;
/* Given array A of pos ints, for each int A[j], find max val of all ints
 * A[0],A[1],..A[j-1] less than A[j], and report # of times the val appears
 * 
 * idea: keep a freq treemap of <A[j],freq>. then as you iterate thru and build 
 * the map, just use floor to find max val. advantage of using a treemap is
 * that it's auto-sorted and u can look at it while building/adding to
 * the map
 * 
 * difficulty: geez once u see the idea its super simple. ig the chain of
 * logic should be 'oh freq arr -> freq array doesnt work. no, array just
 * doesnt work -> ohh freq map so u can build it as u go'
 */
import java.util.*;
import java.io.*;

public class Ex0901 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[] A = new int[n]; // array of pos ints
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		TreeMap<Integer,Integer> freq = new TreeMap<>();
		freq.put(A[0], 1);
		for(int j=1;j<n;j++) { // while adding to freq<>
			freq.put(A[j], freq.getOrDefault(A[j], 0)+1);
			if(freq.lowerKey(A[j])!=null) 
				out.println(freq.lowerKey(A[j])+" "+freq.get(freq.lowerKey(A[j])));
			else
				out.println(-1);
		}
		
		
		out.close();
	}
}
