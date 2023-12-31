/* USACO Feb 2023 Silver Problem 2
 * 
 */
import java.util.*;
import java.io.*;

public class silver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int g = Integer.parseInt(st.nextToken()); // # of gardens
		int n = Integer.parseInt(st.nextToken()); // # of cows
		long[][] grazings = new long[g][3]; // location & time of grazings
		long[][] alibis = new long[n][3]; // location & time of cow alibis
		for(int j=0;j<g;j++) {
			st=new StringTokenizer(in.readLine());
			grazings[j][0]=Long.parseLong(st.nextToken()); // x val
			grazings[j][1]=Long.parseLong(st.nextToken()); // y val
			grazings[j][2]=Long.parseLong(st.nextToken()); // time
		}
		Arrays.sort(grazings, (x1,y1) -> (int)(x1[2]-y1[2]));
		for(int j=0;j<n;j++) {
			st=new StringTokenizer(in.readLine());
			alibis[j][0]=Long.parseLong(st.nextToken()); // x val
			alibis[j][1]=Long.parseLong(st.nextToken()); // y val
			alibis[j][2]=Long.parseLong(st.nextToken()); // time
		}
		int innocents = 0; // ans; # of cows w alibis that prove innocence
		// for each cow/alibi, only need to check 2 closest timepoints
		// if 1 of those timepoints is impossible to make, innocents++;
		for(int j=0;j<n;j++) {
//			System.out.println("hi");
			long cowTime=alibis[j][2];
			int upPointI=Integer.MAX_VALUE; // index of lowest grazing time greater than cowTime
			int downPointI=Integer.MIN_VALUE; // index of greatest grazing time smaller than cowTime
			
			// find 2 closest timepoints to cowTime in grazings w binarySearch
			int lowI=0;
			int upI=g-1;
			int midI=(lowI+upI)/2;
			while(true) { // binary search
				if(grazings[midI][2]>cowTime) {
					if(midI==0) {
						upPointI=0;
						downPointI=0;
						break;
					}
					else if(grazings[midI-1][2]>cowTime) {
						upI=midI-1;
						midI=(lowI+upI)/2;
					}
					else if(grazings[midI-1][2]<cowTime){ 
						upPointI=midI;
						downPointI=midI-1;
						break;
					}
					else { // if grazings[midI-1][2]=cowTime
						upPointI=midI-1;
						downPointI=midI-1;
						break;
					}
				}
				else if(grazings[midI][2]<cowTime) {
					if(midI==g-1) {
						upPointI=g-1;
						downPointI=g-1;
						break;
					}
					else if(grazings[midI+1][2]<cowTime) {
						lowI=midI+1;
						midI=(lowI+upI)/2;
					}
					else if(grazings[midI+1][2]>cowTime) {
						upPointI=midI+1;
						downPointI=midI;
						break;
					}
					else { // if grazings[midI+1][2]==cowTime
						upPointI=midI+1;
						downPointI=midI+1;
						break;
					}
				}
				else { // if grazings[midI][2]==cowTime
					upPointI=midI;
					downPointI=midI;
					break;
				}
			}
			
//			out.println(Math.abs(grazings[downPointI][0]-alibis[j][0])+
//					Math.abs(grazings[downPointI][1]-alibis[j][1])<=
//					Math.abs(grazings[downPointI][2]-alibis[j][2]));
//			out.println(Math.abs(grazings[upPointI][0]-alibis[j][0])+
//					Math.abs(grazings[upPointI][1]-alibis[j][1])<=
//					Math.abs(grazings[upPointI][2]-alibis[j][2]));
//			out.println(j);
			// check if timepoints are makeable
			if(!(Math.abs(grazings[downPointI][0]-alibis[j][0])+
					Math.abs(grazings[downPointI][1]-alibis[j][1])<=
					Math.abs(grazings[downPointI][2]-alibis[j][2])&&
					Math.abs(grazings[upPointI][0]-alibis[j][0])+
					Math.abs(grazings[upPointI][1]-alibis[j][1])<=
					Math.abs(grazings[upPointI][2]-alibis[j][2]))) {
				
				innocents++;
			}
		}
		out.print(innocents);
		out.close();
	}
}
/*
 * 3 4
-100 0 0
0 0 100
50 0 200
0 50 50
1000 1000 0
50 0 200
10 0 170

3 4
0 100 0
0 0 100
50 0 200
0 50 50
1000 1000 0
50 0 200
10 0 170

4 1
10 60 0
-100 60 150
20 30 300
150 10 500
20 50 20
*/
