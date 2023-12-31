package class03x18x2023;
/* USACO Silver 2017 March Problem 2 Bovine Genomics
 * 
 * idea: for each possible 3-index postitions, check if spottiness
 * can be explained. to see if spottiness can be explained, check if the
 * sets of vals in the spotty cows for those 3 indices have any overlap w
 * the sets of vals in the plain cows
 * 
 * difficulty: once i got going it wasnt too bad. key idea is u need sets,
 * so that you can compare the sets of the strs of the chars at those positions
 * so you can see if theres any overlap
 */
import java.util.*;
import java.io.*;

public class HW0802 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cownomics.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows w spots, cows w/o spots
		int m = Integer.parseInt(st.nextToken()); // # of chars in str gene sequ
		char[][] cowsS = new char[n][m];
		for(int j=0;j<n;j++) {
			cowsS[j] = in.readLine().toCharArray();
		}
		char[][] cowsP = new char[n][m];
		for(int j=0;j<n;j++) {
			cowsP[j] = in.readLine().toCharArray();
		}
		int ans=0; // # of possible sets
		for(int j=2;j<m;j++) {
			for(int k=1;k<j;k++) {
				for(int i=0;i<k;i++) {
					HashSet<String> spotValSets=new HashSet<>();
					HashSet<String> plainValSets=new HashSet<>();
					// check if positions j,k,i explain spottiness
					// in other words, check if vals at j,k,i of cowsS
					//  are distinct from all the vals at j,k,i of cowsY
					//MAKE A SET OF J,K,I VALUES FOR PLAINS, THEN FOR SPOTS,
					// IF THEY HAVE NO UNION ans++
					for(int x=0;x<n;x++) {
						spotValSets.add(cowsS[x][j]+""+cowsS[x][k]+""+cowsS[x][i]);
					}
					for(int x=0;x<n;x++) {
						plainValSets.add(cowsP[x][j]+""+cowsP[x][k]+""+cowsP[x][i]);
					}
					boolean noUnion=true;
					for(String s : plainValSets) {
						if(spotValSets.contains(s)) {
							noUnion=false;
							break;
						}
					}
					if(noUnion)ans++;
				}
			}
		}
		
		out.print(ans);
		in.close();
		out.close();
	}
}
