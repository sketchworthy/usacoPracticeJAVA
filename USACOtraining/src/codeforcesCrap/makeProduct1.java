package codeforcesCrap;
/* Codeforces Round 580 Problem B: Make Product Equal 1. COMPLETE
 * https://codeforces.com/contest/1206/problem/B
 * 
 * given n numbers, find the min # of +-1 changes you need to make to 
 * the elements so that the elements' product is 1
 * 
 * 
 * idea: for each #, if >=1, reduce it to 1. else if <=-1, reduce it to -1.
 * if =0, save into a variable called # of 0s.
 * at the end you will be left with x 0s, y -1s, and z 1s. # of 0s
 * doesnt actually matter bc u will have to make 1 change for each of them,
 * so u just add # of 0s to # of changes to get the ans
 * however we do need to check that if there is an odd # of -1s at the end and
 * no 0s, then we're gonna have to add 2 to the total # of changes to make
 * the -1 into 1. so we do need to keep track of # of 0s and # of -1s
 * 
 * difficulty: ez once i thought abt it during a late night right before going
 * to bed
 * 
 */
import java.util.*;
import java.io.*;

public class makeProduct1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		long changes = 0; // total # of changes needed to make
		int zeros = 0; // # of 0s
		int negs = 0; // # of -1s
		for(int j=0;j<n;j++) {
			int k = Integer.parseInt(st.nextToken());
			if(k<=-1) {
				changes+=Math.abs(k+1);
				negs++;
			}
			else if(k>=1) {
				changes+=k-1;
			}
			else {
				changes++;
				zeros++;
			}
		}
		if(negs%2==1&&zeros==0)changes+=2;
		out.print(changes);
		in.close();
		out.close();
	}
}
