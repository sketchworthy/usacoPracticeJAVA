package class04x22x2023;
/* COMPLETE
 * Given n points p[1], p[2], ..., p[n], on the XY-plane, find the number 
 * of triples (i, j, k) where i, j, k are distinct indices, so that 
 * the distance between p[i] and p[j] is equal to the distance between 
 * p[i] and p[k]. That is, triangle formed by the points 
 * p[i], p[j], p[k] is an isosceles triangle.
 * 
 * idea: keep a hashmap of hashmaps that link a point index to a freq hashmap of
 * distances. so loop thru all the points, and for each of those loop thru all
 * other points (n^2), adding their distances to the freq map. then,
 * loop thru each of the n points in the hashmap of hashmaps, looking at their
 * freq hashmap and adding to an ans variable. overall complexity should be
 * O(2n^2) i think.
 * 1 problem: overcounting. what abt equilateral triangles? ANS: doesnt matter
 * bc order matters, we're counting the hashmap int index as the [j]
 * 
 */
import java.util.*;
import java.io.*;

public class isoscelesThree {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("08.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		long[][] points = new long[n][2]; // x,ys
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			points[j][0]=Long.parseLong(st.nextToken());
			points[j][1]=Long.parseLong(st.nextToken());
		}
		HashMap<Integer,HashMap<Long,Integer>> lengths = new HashMap<>();
		for(int j=0;j<n;j++) { // loop thru all the points
			HashMap<Long,Integer> freq = new HashMap<>();
			for(int k=0;k<n;k++) { // add distances to other points to the freq map
				if(j==k)continue;
				Long dist=findDist(points[j][0],points[j][1],points[k][0],points[k][1]);
				freq.put(dist, freq.getOrDefault(dist, 0)+1);
			}
			lengths.put(j, freq);
		}
		
		// loop thru all of lengths<>
		long ans=0;
		for(int i1:lengths.keySet()) { // for index in lengths
			HashMap<Long,Integer> freq = lengths.get(i1);
			for(long i:freq.keySet()) {
				long ifreq=freq.get(i);
				ans+=ifreq*(ifreq-1);
			}
		}
		out.print(ans);

		out.close();
	}
	
	public static long findDist(long x1, long y1, long x2, long y2) {
		// actually returns dist squared but whatever lol
		return (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
	}
}
