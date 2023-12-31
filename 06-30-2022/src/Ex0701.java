/*
 * new idea of 'halfpoints', review
 */
import java.util.Scanner;
public class Ex0701 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int b = in.nextInt();
		int[][] intervals = new int[n][2];
		for(int j=0;j<n;j++) {
			intervals[j][0]=in.nextInt();
			intervals[j][1]=in.nextInt();
		}
		boolean[] cut = new boolean[b+1];
		for(int j=0;j<n;j++) {
			for(int x = intervals[j][0];x<=intervals[j][1];x++) {
				cut[x]=true;
			}
		}
		int cnt1=0; // count of remaining integer points left after cutting intervals
		for(int x=0;x<=b;x++) {
			if(!cut[x]) cnt1++;
		}
		System.out.println(cnt1);
		boolean[] halfpoints = new boolean[b];
		// total length of the remaining segments
		// halfpoints[x] true iff the unit interval (x,x+1) is still on number line
		// x corresponds to the halfpoint x+0.5
		for(int j=0;j<n;j++) {
			for(int x=intervals[j][0];x<intervals[j][1];x++) {
				halfpoints[x]=true;
			}
		}
		int cnt2 = 0; // # of remaining unit intervals
		for(int x=0;x<b;x++) {
			if(!halfpoints[x]) cnt2++;
		}
		System.out.println(cnt2);
		
		in.close();
	}
}
