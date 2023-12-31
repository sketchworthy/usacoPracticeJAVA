package class09x24x2023;
/* USACO 2018 March Silver COMPLETE
 * 
 * idea: sort backwards. literally thats it
 * 
 * difficulty: so ez why is this silver
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0301 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lemonade.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lemonade.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		Integer[] cows = new Integer[n];
		for(int j=0;j<n;j++) {
			cows[j]=Integer.parseInt(st.nextToken());
		}
		int end=-1;
		Arrays.sort(cows, (a, b) -> b - a); // sort in descending order
		for(int j=0;j<n;j++) {
			if(cows[j]<j) {
				end=j;
				break;
			}
		}
		if(end==-1)end=n;
		out.print(end);
		in.close();
		out.close();
	}
}
