package codeforcesCrap;
/* Codeforces Nebius Welcome Round Problem B: Vaccination. NOT IMPLEMENTED
 * https://codeforces.com/contest/1804/problem/B
 * 
 * n patients each come at diff times, and can each stay max of w units.
 * packs of (k vaccines), where a vaccine lasts d units, are used to treat
 * these patients. whats the min # of packs needed to fix all patients?
 * 
 * idea: open the first pack at the very end of the waitspan of the 1st patient.
 * then simulate thru all of the patients.
 * 
 * difficulty: seems ez. 
 * 
 */
import java.util.*;
import java.io.*;

public class vaccination {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for(int t=Integer.parseInt(in.readLine());t>0;t--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()); // # of patients
			int k = Integer.parseInt(st.nextToken()); // vaccines in a pack
			int d = Integer.parseInt(st.nextToken()); // time of vaccine
			int w = Integer.parseInt(st.nextToken()); // wait time
			int[] T = new int[n]; // times patients arrive
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;j++) {
				T[j]=Integer.parseInt(st.nextToken());
			}
			
			int x=0; // # of packs we've used so far
			int c=0; // # of vaccines left in this current pack to use
			int t=0; // current timepoint
			int i = 0; // next patient to be processed
			while(i<=n) { // simulate thru
				
				
			}
			out.println(x);
		}
		
		
		in.close();
		out.close();
	}
}
