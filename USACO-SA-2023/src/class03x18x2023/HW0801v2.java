package class03x18x2023;
/* You're given k amounts of 'points that each of the k jury members gave'.
 * Also, n overall 'finishing' scores that bess remembers. return # of options
 * 
 * og idea: (implemented in HW0801)
 * try each of when bess's first remembered score is after the xth
 * jury member's given points, and then simulate thru for all k options.
 * 
 *  difficulty: this method i used is pretty ez but i think some cases mayyy
 *  have timed out
 *  
 * class idea: (implemented in HW0801v2) use prefix sums to simplify calculations.
 * then simulate for each of k possibilities where bess's 1st memory is
 * after the xth jury decree for k xs.
 */
import java.util.*;
import java.io.*;

public class HW0801v2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("09.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(st.nextToken()); // # of jury members
		int n = Integer.parseInt(st.nextToken()); // # of scores bess remembers
		st = new StringTokenizer(in.readLine());
		int[] points = new int[k]; 
		// ^ points[x] = # of points up from og score after jury x's decree
		points[0]=Integer.parseInt(st.nextToken());
		for(int j=1;j<k;j++) {
			points[j]=points[j-1]+Integer.parseInt(st.nextToken());;
		}
		st = new StringTokenizer(in.readLine());
		int[] memories = new int[n]; // scores bessie remembers
		for(int j=0;j<n;j++) {
			memories[j]=Integer.parseInt(st.nextToken());
		}
//		Arrays.sort(memories);
//		Arrays.sort(points);
		HashSet<Integer> s2 = new HashSet<>();
		for(int x=0;x<n;x++) {
			s2.add(memories[x]);
		}
		HashSet<Integer> ogScoreOptions = new HashSet<>();
		for(int j=0;j<k;j++) { // simulate for each of k possibilities 
			// where bess's 1st memory is after the xth jury decree for k xs
			int ogScore=memories[0]-points[j];
			// IN OTHER WORDS: check if points[...]+ogScore contains memories[]
			HashSet<Integer> s1 = new HashSet<>();
			for(int x=0;x<k;x++) {
				s1.add(points[x]+ogScore);
			}
			if(s1.containsAll(s2))ogScoreOptions.add(ogScore);
		}
		
		out.print(ogScoreOptions.size());
		in.close();
		out.close();
	}
}
