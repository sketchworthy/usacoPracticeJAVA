package class01x21x2024;
/* K-th fraction _status{COMPLETE or not}_
 * Given array A w N distinct pos ints all relatively prime,
 * find the K-th smallest fraction using the N ints
 * 
 * idea:
 * can keep a list of all possible fractions,
 * x / y, and their corresponding x's and y's.
 * then sort the whole thing. then just
 * print the kth smallest fraction.
 * this actually works, but it's buggy because sorting an
 * array of doubles is pretty nasty, also slower & more memory usage.
 * to get a faster, cleaner, finish we can use binary search.
 * 
 * we have a search space from low to high, which each start
 * as 0 and 1. we generate a mid=(low+high)/2.0, and for each mid we
 * split fractions into ones greater than mid and smaller.
 * then once a mid processing is done, we have found a cnt of how
 * many fractions are <= that mid.  if cnt<=k mid must be bigger,
 * and low= mid. if cnt>k mid must be smaller, and high=mid.
 * to find cnt as fast as possible, we can iterate thru fractions in a fancy way.
 * 
 * 
 * 
 * implementation: 
 * 
 * difficulty: 
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0201 {
	static int w = 2;

	public static void main(String[] args) throws Exception {
		// take input
//		BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of distinct pos ints
		int k = Integer.parseInt(st.nextToken()); 
		int[] A = new int[n]; 
		// fractions, and the 2 indices of ints that made up the fraction
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		
		
		
		in.close();
		out.close();
	}
}