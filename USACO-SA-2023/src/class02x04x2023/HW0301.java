package class02x04x2023;
/* COMPLETE
 * given n spotty cows & n plain cows each w a string of m chars denoting their genome, 
 * find the max # of indiv chars that can be shared by any 2 spotty & plain cows
 * 
 * idea: bc #s are small, just loop thru all spotty & plain cow pairs individually 
 * & return max cnt
 * 
 * difficulty: ez bc of the tiny size. hardest part was understanding the question
 */
import java.util.*;
import java.io.*;

public class HW0301 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of spotted or plain cows
		int m = Integer.parseInt(st.nextToken()); // # of chars in genome
		
		char[][] spotted = new char[n][m]; // spotted cow genomes
		char[][] plain = new char[n][m];
		for(int j=0;j<n;j++) spotted[j]=in.readLine().toCharArray();
		for(int j=0;j<n;j++) plain[j]=in.readLine().toCharArray();
		
		int maxShared=0;
		for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) { // for spotted cow j & plain cow k
				int cShared=0;
				for(int pos=0;pos<m;pos++) {
					if(spotted[j][pos]==plain[k][pos])cShared++;
				}
				maxShared=Math.max(maxShared, cShared);
			}
		}
		
		out.print(maxShared);
		in.close();
		out.close();
	}
}
