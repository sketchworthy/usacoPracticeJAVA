package class11x19x2023;
/* Sum divisible by P COMPLETE, TO REVIEW & SUBMIT TO CANVAS
 * Given an array A of pos ints, remove shortest contiguous subarr such that 
 * remaining total sum is 0 mod p. Report the length of the subarr u remove.
 * If you must remove the whole arr, return -1
 * 
 * idea: keep a prefix sum (mod p) array of A. Record m, the mod p of the 
 * total sum of array A. We basically want to find the shortest difference 
 * in indices that sums to m.
 * We meanwhile keep a hash map. keys: mod p values of ps array sums. 
 * Values: last index that that the key showed up in ps sum arr.
 * To do this, we iterate thru the ps arr once. At every index j we get 
 * ps[j], the sum up to that point. We then check the hash map val for 
 * ps[j]-m mod p. If it exists we update the ans to min of ans and 
 * j-(index by hash map adjusted with +1 mb)
 * 
 * difficulty: ez. straightforward ps.
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
		
		// create ps mod p arr
		int[] ps = new int[n];
		ps[0]=A[0];
		for(int j=1;j<n;j++) {
			ps[j]=(ps[j-1]+A[j])%p; // mod p
		}

		int ans = Integer.MAX_VALUE;
		int m = ps[n-1]; // mod p of total sum of array
		HashMap<Integer,Integer> indicesOfPs = new HashMap<>();
		// indicesOfPs[x] gives most recent index of ps[] that equalled x
		
		// iterate thru ps once, updating hashmap and ans
		for(int j=0;j<n;j++) {
			indicesOfPs.put(ps[j], j);
			if(indicesOfPs.containsKey((ps[j]-m)%p)) {
				ans = Math.min(ans, j-indicesOfPs.get((ps[j]-m)%p));
			}
		}
		
		if(ans==Integer.MAX_VALUE)out.print(-1);
		else out.print(ans);
		
		in.close();
		out.close();
	}
}
