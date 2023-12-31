/* Bronze 2 Momentum Learning Summer Camp, 6/27/2022 to 7/1/2022
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any
two time-points in the list.
idea: compare timepoints pairwise using nested loops
ALT idea, better: sort timepoints, then compare neighboring times
 */
import java.util.*;
public class Ex0101 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] tpsM = new int[n]; // timepoint minutes
		int[] tpsH = new int[n]; // timepoint hours
		for(int j=0;j<n;j++) {
			String time = in.next();
			tpsH[j]=Integer.parseInt(time.substring(0,2));
			tpsM[j]=Integer.parseInt(time.substring(3));
		}
		int[] times = new int[n];
		for(int j=0;j<n;j++) {
			times[j]=tpsH[j]*60+tpsM[j];
		}
		
		int minD=720; // minimum time difference
		
		// approach 1: nested loop, compare pairwise (slower)
//		for(int j=0;j<n-1;j++) {
//			for(int k=j+1;k<n;k++) { // compare tp index j with index k
//				int jT = times[j]; // jtime
////				System.out.println(jT);
//				int kT = times[k]; // ktime
////				System.out.println(kT);
//				int dif = Math.abs(jT-kT);
//				if(dif>720) dif = 1440-dif;
//				if(dif<minD) minD=dif;
//			}
//		}
		
		// approach 2: using 1-layered loop
		// sort tps in ascending or descending layer
		// compare each index i with index i+1
		Arrays.sort(times);
		for(int j=0;j<n-1;j++) {
			int d = times[j+1]-times[j];
			minD = Math.min(d, minD);
		}
		int d = 1440-(times[n-1]-times[0]);
		minD = Math.min(d, minD);
		
		in.close();
		System.out.println(minD);
	}
}
