package class09x10x2023;
/* Sum of Powers
 * find the sum of a bunch of powers. a^b mod k
 * 
 * idea: Work backwards from ur exponent. a^b comes from a^(b/2) comes
 * from a^((b/2-1)/2). quickly done with recursion
 * 
 * difficulty: w recursion its ez
 */
import java.util.*;
import java.io.*;

public class Ex0102 {
	static long MOD = (long) (1e9+7);
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n=Integer.parseInt(in.readLine()); // # of summations of remainders to compute
		long total=0;
		for(;n>0;n--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			long a=Integer.parseInt(st.nextToken());
			long b=Integer.parseInt(st.nextToken());
			long k=Integer.parseInt(st.nextToken());
			long tot=getPow(a,b,k);
			total=(total+tot)%MOD;
		}
		out.print(total);
		in.close();
		out.close();
	}
	static long getPow(long a, long b, long k){ // return a^b mod k
		if(b==0)return 1;
		if(b==1)return a%k;
		if(b%2==0) {
			long t = getPow(a,b/2,k);
			return t*t%k; 
		}
		else {
			long t = getPow(a,b/2,k);
			return t*t%k*a%k; 
		}
	}
}
