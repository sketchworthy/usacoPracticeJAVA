package class11x19x2023;
/* Sum divisible by P _status{COMPLETE or not}_
 * Given an array A of pos ints, remove shortest contiguous subarr such that 
 * remaining total sum is 0 mod p. Report the length of the subarr u remove.
 * If you must remove the whole arr, return -1
 * 
 * idea: prefix sum arrs, 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class HW1101 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int[] A = new int[n]; // mod p
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken())%p;
		}
		
		// create freq ps arr
		ArrayList<ArrayList<Integer>> modPindices = new ArrayList<>();
		// each of 0,...i...p-1 lists will give the indices x where ps[x]=i
		for(int j=0;j<p;j++) {
			modPindices.add(new ArrayList<Integer>());
		}
		// add -1 to modPindices.get(0)
		modPindices.get(0).add(-1);
		
		// create ps arr
		int[] ps = new int[n];
		ps[0]=A[0];
		for(int j=1;j<n;j++) {
			ps[j]=(ps[j-1]+A[j])%p; // mod p
			modPindices.get(ps[j]).add(j);
		}
		
		// find min diff in identical mods using 
		int min = Integer.MAX_VALUE;
		for(int j=0;j<p;j++) {
			for(int k=modPindices.get(j).size()-1;k>0;k--) {
				int debg=modPindices.get(j).get(k)-modPindices.get(j).get(k-1);
				min=Math.min(min, modPindices.get(j).get(k)-modPindices.get(j).get(k-1));
			}
		}
		
		
		if(min==Integer.MAX_VALUE)out.print(-1);
		else out.print(min);
		
		in.close();
		out.close();
	}
}
