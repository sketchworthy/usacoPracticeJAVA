package class02x04x2024;
/* Replace Chalk Silver _status{COMPLETE or not}_
 * There are N students in class, numbered [0,N-1]. 
 * Teacher gives each student a problem in the order 0,1,...N-1,0,1...
 * Given C[] and int K, there are initially K pieces of chalk.
 * Student i uses C[i] chalk to solve that problem. But if
 * currChalk<C[i], student i must replace the chalk.
 * find index of 1st student who must replace the chalk.
 * 
 * idea: 
 * 
 * implementation: 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class HW0301 {
	static int w = 1;

	public static void main(String[] args) throws Exception {
		// take input
		// BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of students
		int k = Integer.parseInt(st.nextToken()); // # of chalk
		int[] C = new int[n]; // # of chalk each person uses
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			C[j]=Integer.parseInt(st.nextToken());
		}
		
		int[] ps = new int[n]; // ps[0]=C[0], ps[x]=C[0]+C[1]+..C[x]
		ps[0]=C[0];
		for(int j=1;j<n;j++) {
			ps[j]=ps[j-1]+C[j];
		}
		
		k%=ps[n-1];
		// find smallest element of ps[] that's >= k, return its index
		int i=0;
		while(ps[i]<k) {
			i++;
		}
		
		out.println(i);

		in.close();
		out.close();
	}
}