package class11x19x2023;
/* Water and Jug _status{COMPLETE or not}_
 * DONE IN CPP
 * 
 */
import java.util.*;
import java.io.*;

public class HW1102 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		int truCnt=0; // # of queries that are true
		for(int j=Integer.parseInt(in.readLine());j>0;j--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken()); 
			// ^ amount of water ur trying to measure
			
		}
		out.print(truCnt);
		in.close();
		out.close();
	}
}
