/* USACO Silver 2023 Problem 3
 * 
 * idea: queue
 */
import java.util.*;
import java.io.*;

public class silver3try {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		char[] str = in.readLine().toCharArray();
		int n = str.length;
		long total=0;
		char[] bessie = "bessie".toCharArray(); // 6 chars
		for(int start=0;start<n-5;start++) {
			for(int end=5;end<n;end++) {
				int cc = 0; // current char we're on in bessie[], starting w 'b' (index 0)
				for(int j=start;j<=end;j++) {
					if(str[j]==bessie[cc]) { // if progress
						cc++;
						if(cc==6) {
							total++;
							cc=0;
						}
					}
				}
			}
		}
		
		out.print(total);
		out.close();
	}
}
