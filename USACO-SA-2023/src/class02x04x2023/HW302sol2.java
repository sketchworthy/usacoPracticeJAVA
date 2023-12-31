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
 * UPDATE: (inspired from class solution) an improvement that can be 
 * made to this algorithm: Because the genomes only consist of 4 letters
 * A T G C, there are only 64 distinct possibilities for 3-pos groups'
 * identities. Then there are m choose 3 possible positions for the 3
 * pos groups. this way its a much smaller #!
 */
import java.util.*;
import java.io.*;

public class HW302sol2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cownomics.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of spotted or plain cows
		int m = Integer.parseInt(st.nextToken()); // # of chars in genome
		
		int[][] spotted = new int[n][m]; // spotted cow genomes
		int[][] plain = new int[n][m]; // A is 0, C is 1, G is 2, T is 3
		for(int j=0;j<n;j++) {
			String genome = in.readLine();
			for(int k=0;k<m;k++) {
				char c = genome.charAt(k);
				if(c=='A') spotted[j][k]=0;
				else if(c=='C') spotted[j][k]=1;
				else if(c=='G') spotted[j][k]=2;
				else  spotted[j][k]=3;
			}
		}
		for(int j=0;j<n;j++) {
			String genome = in.readLine();
			for(int k=0;k<m;k++) {
				char c = genome.charAt(k);
				if(c=='A') plain[j][k]=0;
				else if(c=='C') plain[j][k]=1;
				else if(c=='G') plain[j][k]=2;
				else plain[j][k]=3;
			}
		}
		
		int sharedGroups=0; // # of shared 3 pos groups

		for(int i=0;i<m-2;i++) {
			for(int j=i+1;j<m-1;j++) {
				for(int k=j+1;k<m;k++) { // for each of positions i,j,k see if they're
					// a shared triple
					boolean[] ijkSpotted = new boolean[64];
					// ^ diff ijk possibilities found in spottedCows
					boolean[] ijkPlain = new boolean[64];
					
					for(int x=0;x<n;x++) { // loop thru n spotty cows & n plain cows
						ijkSpotted[spotted[x][i]*16+spotted[x][j]*4+spotted[x][k]]=true;
						ijkPlain[plain[x][i]*16+plain[x][j]*4+plain[x][k]]=true;
					}
					
					// find # of shared groups
					boolean shares=false;
					for(int x=0;x<64;x++) {
						if(ijkSpotted[x] && ijkPlain[x]) {
							shares=true;
							break;
						}
					}
					if(shares)sharedGroups++;
				}
			}
		}
//		out.println(sharedGroups);
		out.print(m*(m-1)*(m-2)/6-sharedGroups);
		
		in.close();
		out.close();
	}
}
