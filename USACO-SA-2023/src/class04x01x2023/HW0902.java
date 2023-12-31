package class04x01x2023;
/* USACO Feb 2017 Silver Problem 1
 * cows have a start&endtime, chickens only 1 timepoint
 * 
 * og idea: (implemented in HW0902)
 * keep a treemap of chickens, <time,ID> and go thru them 1 by 1
 * and see which cows can be satisfied. what ENDED UP HAPPENING: I made 2
 * arrs and then pointers to go thru em. this did not solve all cases,
 * tho its possible i had a bug and just didnt go thru and fix it bc i was
 * lazy and so done w coding when i finished this using pointers method
 * NOTE: ok figured out bug. basically what i was doing was pairing cows
 * and chickens by first pairing cows with the earliest startpoints.
 * what i SHOULDVE been doing is first pairing cows with the earliest endpts.
 * so pointers doesnt work bc u go past some chickens with earlier times.
 * i could fix this implementation by instead of using pointers, just
 * re-looping thru all the chickens each time i go forward a cow. but
 * im just gonna do the class method
 * 
 * class method: (not implemented)
 * basically, just use a map to track chickens in a freq map of
 * <timestamp,# of chickens>. super clever to use maps to track
 * scattered indices' frequencies i think
 * 
 * 
 * difficulty: 
 */
import java.util.*;
import java.io.*;

public class HW0902 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("helpcross.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] chickens = new int[c]; // times chickens can help
		for(int j=0;j<c;j++) { // times chickens willing to help
			chickens[j]=Integer.parseInt(in.readLine());
		}
		Arrays.sort(chickens); // sort in ascending time order
		int[][] cows = new int[n][2]; // [x][0] startpoint, [x][1] endpoint
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			cows[j][0]=Integer.parseInt(st.nextToken());
			cows[j][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cows,(a,b)->a[0]-b[0]); // sort in ascending startpoint order
		// use pointers? idk
		
		long ans=0;
		int i=0; // chickens arr pointer
		int j=0; // cows arr pointer
		while(i<c&&j<n) { // compare chicken i and chicken j
			int cht=chickens[i]; // chicken time
			int cot1=cows[j][0]; // cow time startpoint
			int cot2=cows[j][1]; // cow time endpoint
			if(cht>cot1) {
				if(cht<cot2) { // chicken and cow pair formed
					ans++;
					i++;
					j++;
				}
				else { // chicken time ahead of this cow
					j++;
				}
			}
			else { // chicken time behind
				i++;
			}
		}
		out.print(ans);
		
		in.close();
		out.close();
	}
}
