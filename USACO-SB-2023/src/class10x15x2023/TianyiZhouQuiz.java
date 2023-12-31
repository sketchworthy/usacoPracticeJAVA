package class10x15x2023;
/* Collecting Numbers COMPLETE
 * Given an array containing all of 1...n each once,
 * collect the numbers from 1 to n in strictly increasing order.
 * In each round you pass thru the array from left->right.
 * How many rounds needed to collect all ints?
 * 
 * idea: sort indices of the array.
 * for ex, 4 2 1 5 3 would become 3 2 5 1 4,
 * bc 1 is at pos 3, 2 at pos 2, etc.
 * Then, given the indices array, just check
 * how many times 2 consec numbers go from
 * greater to smaller. add 1, and thats ur answer
 * 
 * difficulty: ez
 */
import java.util.*;
import java.io.*;

public class TianyiZhouQuiz {
	static int x=9;
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("0"+x+".in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[][] A = new int[n][2];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][0]=Integer.parseInt(st.nextToken());
			A[j][1]=j;
		}
		Arrays.sort(A,(a,b)->a[0]-b[0]);
		int ans=1;
		
		for(int j=1;j<n;j++) {
			if(A[j-1][1]>A[j][1])ans++;
		}
		out.println(ans);
		
		in.close();
		out.close();
	}
}
