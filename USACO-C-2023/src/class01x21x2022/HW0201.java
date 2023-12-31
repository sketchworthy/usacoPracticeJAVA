package class01x21x2022;
/* Given the temperature in N consecutive days, find the max average temperature
 * over any K or more consecutive days.
 * 
 * Pretty easy
 */
import java.util.*;
import java.io.*;
public class HW0201 {
	public static void main(String[] args) throws Exception{
		Scanner in2 = new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt(); // # of consec days
		int k = in.nextInt();
		int[] dayTemps = new int[n]; // dayTemps[x] = temperature on day x
		for(int j=0;j<n;j++) {
			dayTemps[j]=in.nextInt();
		}
		double maxAvg=0;
		for(int start=0;start<n-k+1;start++) {
			// add avg of all #s from dayTemps[0] to dayTemps[start+k-1]
			double sum=0;
			for(int j=start;j<start+k;j++) {
				sum+=dayTemps[j];
			}
			maxAvg = Math.max(sum/k, maxAvg);
			int num = k; // # of consec days
			for(int end=start+k;end<n;end++) {
				num++;
				sum+=dayTemps[end];
				maxAvg=Math.max(sum/num, maxAvg);
			}
		}
		System.out.println(maxAvg);
		
		in.close();
	}
}
