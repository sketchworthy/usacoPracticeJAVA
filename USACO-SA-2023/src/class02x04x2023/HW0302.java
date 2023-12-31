package class02x04x2023;
/* Mar 2017 USACO Silver Problem 2 Bovine Genomics COMPLETE
 * 
 * given genomes of n spotty cows and n plain cows,
 * find max # of 3-pos groups that can explain spottiness
 * 
 * idea: first, find which 3 or more position groups are shared by 
 * some spotty cow and some plain cow, meaning they are excluded from 
 * the 3-pos groups that can explain spottiness
 * then, subtract the 3+ pos groups that DON'T work from the total of
 * all possible 3-pos groups (m choose 3)
 * 
 * current sol only gives 7/10 test cases on USACO, rest timed out :(
 * 
 * THIS IS ORIGINAL VERSION, AKA A SOL I FOUND ON MY OWN. SEE sol2 VERSION
 * FOR THE IMPROVED CLASS SOLUTION
 */
import java.util.*;
import java.io.*;

public class HW0302 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of spotted or plain cows
		int m = Integer.parseInt(st.nextToken()); // # of chars in genome
		
		char[][] spotted = new char[n][m]; // spotted cow genomes
		char[][] plain = new char[n][m];
		for(int j=0;j<n;j++) spotted[j]=in.readLine().toCharArray();
		for(int j=0;j<n;j++) plain[j]=in.readLine().toCharArray();
		
		TreeSet<String> tripleIs=new TreeSet<>();
		
		int shared3posGroups=0;
		ArrayList<Integer> sharedIs=new ArrayList<>();
		for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) { // for spotted cow j & plain cow k
				sharedIs.clear();
				int cShared=0; // check how many positions they share
				for(int pos=0;pos<m;pos++) {
					if(spotted[j][pos]==plain[k][pos]) {
						cShared++;
						sharedIs.add(pos);
					}
				}
				if(cShared>=3) {
					// add triple indices positions to tripleIs. if it's new, add 1 to
					//   shared3posGroups
					for(int a=0;a<sharedIs.size();a++) {
						for(int b=a+1;b<sharedIs.size();b++) {
							for(int c=b+1;c<sharedIs.size();c++) {
								String str = (sharedIs.get(a)+"."+sharedIs.get(b)+"."+sharedIs.get(c));
								if(!tripleIs.contains(str)) {
									shared3posGroups++;
									tripleIs.add(str);
								}
							}
						}
					}
				}
			}
		}
//		out.print(shared3posGroups);
		out.print(m*(m-1)*(m-2)/6-shared3posGroups);
		
		
		out.close();
	}
}
