import java.util.Scanner;
import java.io.*;
public class circularBarn {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File ("cbarn.in"));
		PrintWriter out = new PrintWriter(new File ("cbarn.out"));
		
		int n = in.nextInt();
		int[] R = new int[n]; // read r_i's to array R
		for (int j=0;j<n;j++) R[j]=in.nextInt();
		in.close();
		
		int minD = Integer.MAX_VALUE; // initialize minimum distance variable
		for (int startRm=1;startRm<=n;startRm++) { // see distance for each starting room
			int distance = 0; // initialize this room's distance
			for (int rmN=1;rmN<=n;rmN++) { // add distance for cows getting to each room
				if(rmN-startRm>=0) {
					distance+=R[rmN-1]*(rmN-startRm);
				}
				else {
					distance+=R[rmN-1]*(rmN-startRm+n);
				}
			}
			if(distance<minD) { // see if distance is smaller than current minD
				minD = distance;
			}
		}
		System.out.println(minD);
		out.print(minD);
		out.close();
	}
}