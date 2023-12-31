/*
 * Given an array of N integers and a target integer T, find the lengths of the blocks of T (continuous occurrences
of T). Also find the lengths of the gap between two adjacent occurrences of T.
 */
import java.util.*;
public class partitionArray {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt(); // length of array
		int t = kb.nextInt(); // target int
		int[] A = new int[n];
		for (int j=0;j<n;j++) {
			A[j]=kb.nextInt();
		}
		kb.close();
		
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		int tCount = 0; // target count
		int gCount = 0; // gap count
		for (int j=0;j<n;j++) {
			if(A[j]==t) {
				tCount++;
				
				gaps.add(gCount);
				gCount=0;
			}
			else {
				gCount++;
				if(tCount!=0) { // only if past index was t
					blocks.add(tCount);
					tCount=0;
				}
			}
		}
		gaps.add(gCount);
		System.out.println(blocks);
		System.out.println(gaps);
	}
}
