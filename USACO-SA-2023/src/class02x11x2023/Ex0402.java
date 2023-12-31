package class02x11x2023;
/* USACO Jan 2020 Silver Problem 1 Berry Picking COMPLETE.
 * http://usaco.org/index.php?page=viewproblem2&cpid=990
 * 
 * idea: loop thru all possible basket capacities, from 1 to 1000.
 * partially inspired by class solution
 * 
 * difficulty: kinda hard to debug, which was main difficulty i
 * ran into. the problem was that it was faster to sort array by
 * descending order i think.
 */
import java.util.*;
import java.io.*;

public class Ex0402 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("berries.in"));
		PrintWriter out = new PrintWriter(new FileWriter("berries.out"));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of trees
		int k = Integer.parseInt(st.nextToken()); // # of baskets Bess has
		StringTokenizer st2=new StringTokenizer(in.readLine());
		int[] B = new int[n]; // B[x]: # of berries tree x has
		for(int j=0;j<n;j++) B[j]=Integer.parseInt(st2.nextToken());
		
		// idea: loop thru all possible basket capacities, from 1 to 1000
		
		int maxCnt=0; // max # of berries Bess can get
		
//		for(int cap=1000;cap>=1;cap--) {
//			int[] basketCnts=new int[n]; // basketCnts[x]: # of berries basket x has
//			// loop thru trees, for each tree add to baskets
//			for(int j=0;j<n;j++) {
//				for(int b=B[j]/cap;b>0;b--) {
//					
//				}
//			}
//		}
		ArrayList<Integer> basketCnts=new ArrayList<>();
		for(int cap=1000;cap>=1;cap--) {
			if(maxCnt>=cap*k/2)break;
			basketCnts.clear();
			// loop thru trees, for each tree add to baskets
			for(int j=0;j<n;j++) {
				int q = B[j]/cap;
				for(int x=0;x<q;x++)basketCnts.add(cap);
				if(B[j]>0)basketCnts.add(B[j]%cap);
			}
			Collections.sort(basketCnts,(x1,x2) -> x2-x1); // sort in descending order
			// if basketCnts has size s, and we're looking at top k basket's
			//  first k/2 baskets
			int cCnt=0;
			int s = basketCnts.size();
			int num=k/2;
			if(s<k)num=s/2;
			for(int j=num;j<2*num;j++) {
				cCnt+=basketCnts.get(j);
			}
			maxCnt=Math.max(maxCnt, cCnt);
		}
		out.print(maxCnt);
		in.close();
		out.close();
	}
}
//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));