/* USACO 2014 Silver prob3 Cowjog COMPLETE
 * Difficulty ok. i could mostly see how to do this problem w/o looking at
 * sol, but i needed to look at sol to debug it
 * NOTE: MAKE SURE WHEN ABOVE BRONZE TO CHANGE TO LONG VARIABLES LMAO
 * Also to use bufferedreader
 * 
 * basic problem idea: go backwards and look at consecutive cows. keep
 * a variable of the position of the current closest group. if they
 * don't catch up, that means there's an extra group now.
 */
package class01x14x2023makeup;
import java.io.BufferedReader;

import java.util.*;
import java.io.*;
public class Ex0102 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new File("cowjog.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows
		long t = Integer.parseInt(st.nextToken()); // # of minutes cows run
		int[][] cows = new int[n][2]; // x,0 gives position, x,1 gives speed
		for(int j=0;j<n;j++) {
			StringTokenizer st2 = new StringTokenizer(in.readLine());
			cows[j][0]=Integer.parseInt(st2.nextToken());
			cows[j][1]=Integer.parseInt(st2.nextToken());
		}
		int cnt=0;
		long currentPos=Long.MAX_VALUE;
		// from the latest cow, go backwards and check if the prev cow will 
		//  catch up. if it won't, add 1
		for(int j=n-1;j>=0;j--) {
			// check if cow j will catch up to currentPos
			long expectedPos=cows[j][0]+t*cows[j][1];
			if(expectedPos<currentPos) {
				currentPos=expectedPos;
				cnt++;
			}
		}
		
		out.println(cnt);
		in.close();
		out.close();
	}
}

/*
*
*/