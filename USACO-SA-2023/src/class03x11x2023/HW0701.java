package class03x11x2023;
/* Given an array A of N ints, and a pos int K, find max sum of ints in A
 * after applying negation to any pos in array A K times
 * 
 * idea: put all stuff into a priority queue sorted in descending order, so
 * that the smallest element is at the head. then negate that smallest element
 */
import java.util.*;
import java.io.*;

public class HW0701 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(in.readLine());
		}
		PriorityQueue<Integer> ints = new PriorityQueue<>((x1,x2)->x1-x2);
											// ^sorted in ascending order
		for(int j=0;j<n;j++) {
			Integer i = new Integer(A[j]);
			ints.add(i);
		}
		long ans = 0; // ans max sum
		for(int j=0;j<k;j++) {
			Integer i=ints.poll();
			i*=(-1);
			ints.add(i);
		}
		for(int j=0;j<n;j++) {
			ans+=ints.poll();
		}
		out.print(ans);
		in.close();
		out.close();
	}
}
