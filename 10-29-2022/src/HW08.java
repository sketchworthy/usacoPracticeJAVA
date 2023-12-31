/*
 * 
 */
import java.util.*;
import java.io.*;
public class HW08 {
	public static void main(String[] args) throws Exception{
		Scanner in2 = new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt(); // # of #s
		int k = in.nextInt(); // # of shuffles
		int[] nums = new int[n+1]; // ignore position 0, shows 1 thru n
		int[] p = new int[n+1]; // ignore position 0, shuffle directions
		// nums[j] is moved to nums[p[j]]
		for(int j=1;j<=n;j++) {
			nums[j]=j;
			p[j]=in.nextInt();
		}
		
		int cnt1=0; // # of stationary ints
		boolean[] filledAtEnd = new boolean[n+1]; // ignore pos 0,
		// filledAtEnd[x]=true if the space is taken by the last shuffle
		Arrays.fill(filledAtEnd, false);
		
		// simulate
		for(int j=1;j<=n;j++) { // for index ur shuffling
			int index=j; // index u end up at
			for(int i=0;i<k;i++) {
				index=p[index];
			}
			if(index==j)cnt1++;
			filledAtEnd[index]=true;
		}
		int cnt2=0;
		for(int j=1;j<n+1;j++) {
			if(!filledAtEnd[j]) cnt2++;
		}
		System.out.println((cnt1*1000+cnt2));
		
		in.close();
	}
}
