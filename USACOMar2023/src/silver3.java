/* USACO Silver 2023 Problem 3
 * 
 * idea: queue?
 * current problem: it doesn't update the closest b if its past the 1st e
 */
import java.util.*;
import java.io.*;

public class silver3 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		char[] str = in.readLine().toCharArray();
		// keep track of all 'b e s s i e' startpoint & endpoint indices
		ArrayList<Integer> starts = new ArrayList<>(); // indices of start 'b's
//		ArrayList<Integer> i1= new ArrayList<>(); // indices of char 1, 'e'
//		ArrayList<Integer> i2= new ArrayList<>(); // indices of char 1, 's'
//		ArrayList<Integer> i3= new ArrayList<>(); // indices of char 1, 's'
//		ArrayList<Integer> i4= new ArrayList<>(); // indices of char 1, 'i'
		ArrayList<Integer> ends = new ArrayList<>(); // indices of ending 'e's
		char[] bessie = "bessie".toCharArray(); // 6 chars
		
		int cc = 0; // current char we're on in bessie[], starting w 'b' (index 0)
		for(int j=0;j<str.length;j++) {
			if(str[j]==bessie[cc]) { // if progress
				cc++;
				if(cc==1) {
					starts.add(j);
				}
				else if(cc==6) {
					ends.add(j);
					cc=0;
				}
			}
			else if(cc==1&&str[j]==bessie[cc-1]) {
				starts.remove(starts.size()-1);
				starts.add(j);
			}
		}
		long total=0;
		int n = ends.size();
		if(starts.size()!=n) {
			starts.remove(n);
		}
		for(int j=0;j<n;j++) { // for each bessie,
			// find the # of substrs that contain it & add to total
			// start index of this 'bessie' is starts.get(j), end is ends.get(j)
			// total # of substrs that include this 'bessie; is  
			// (starts.get(j)+1)*(str.length-ends.get(j)+1)
			total+=(starts.get(j)+1)*(str.length-ends.get(j));
		}
		
		out.print(total);
		out.close();
	}
}
