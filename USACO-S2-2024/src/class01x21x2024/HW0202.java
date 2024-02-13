package class01x21x2024;
/* USACO 2020 Jan Silver Loan Repayment COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=991
 * FJ needs to give B n milk within k days, giving >=m milk each day. 
 * Find the largest # x s.t. FJ can successfully obey above and
 * follow this procedure for giving milk on any day:
 * FJ has already given B g milk. y=floor((n-g)/x). 
 * if y<m, y=m. then, give B y milk on this day.
 * 
 * idea: inverse binary search for largest value of x
 * that satisfies all conditions.
 * 
 * difficulty: shaving down the time complexity of simulation.
 * basically key is if you're subtracting some variable c
 * over and over, just subtract c*i if i is the # of times
 * you subtract that variable.
 * 
 */
import java.util.*;
import java.io.*;

public class HW0202 {
	static long n,k,m;
	public static void main(String[] args) throws Exception {
		// take input
		 BufferedReader in = new BufferedReader(new FileReader("loan.in"));
		PrintWriter out = new PrintWriter(new FileWriter("loan.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Long.parseLong(st.nextToken()); // amount of milk FJ needs to give B
		k = Long.parseLong(st.nextToken()); // # of days FJ needs to pay B back in
		m = Long.parseLong(st.nextToken()); // min amount milk FJ needs to give B each day

		// binary search for largest value of x
		long low = 1;
		long high = (long)n/m;  // since k*m<n, n/x must > m, so n>x*m, and n/m>x
		while(low<high) {
			long x = (low+high+1)/2;
			
			// test if x works
			
			if(isValid(x)) {
				low=x;
			}
			else high = x-1;
		}
		out.println(low);

		in.close();
		out.close();
	}
	
	static boolean isValid(long x) {
		// simulate to test if it works
		long owed = n; // # of milk still owed
		long daysPassed=0;
		
		while((owed/x)>m) { // simulate current day
			long y = owed/x;
			
			owed-=y;
			daysPassed++;
			
			// find # of days payment is still this y

			// as long as owed is >= y*x, y is the same
			long numIntervals = (owed-y*x)/y;
			if((owed-y*x)%y!=0) numIntervals++;
			// numIntervals: # of intervals of y that
			// can be removed from owed until owed finally < yx
			
			if(numIntervals<=0) continue;
			owed-=numIntervals*y;
			daysPassed+=numIntervals;
		}
		
		// from now on, y will always be m, so simulate that
		daysPassed+=owed/m;
		if(owed%m!=0) {
			daysPassed++;
		}
		
		if(daysPassed>k) return false;
		return true;
	}
}