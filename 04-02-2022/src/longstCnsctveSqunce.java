/* Given an array of integers, find the maximum length of any consecutive sequence that can formed using integers
from the array. */
import java.util.*;
public class longstCnsctveSqunce {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of integers in array
		int[] A = new int[n];
		for(int k=0;k<n;k++) {
			A[k]=in.nextInt();
		}
		Arrays.sort(A);
		
		int streakLen = 1; // length of current sequence of consecutive #s
		int streakHigh = A[0]; // highest # in current consec # sequence
		int streakLongestLen = 1;
		
		for(int k=1;k<n;k++) { // don't start from index 0
			if(A[k]==streakHigh+1) {
				streakLen++;
				streakHigh++;
			}
			else {
				streakHigh = A[k];
				streakLen=1;
			}
			if (streakLongestLen<streakLen) streakLongestLen=streakLen;
		}
		System.out.println(streakLongestLen);
		in.close();
	}
}
