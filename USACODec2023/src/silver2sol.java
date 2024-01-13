/* USACO 2023 Dec Silver Q2 _status{COMPLETE or not}_
 * http://usaco.org/index.php?page=viewproblem2&cpid=1351
 * My personal official post-contest solution
 * 
 * Given N nodes and K edges, Anna assesses that
 * K nodes form a cycle. Bess independently assesses
 * that K more nodes form a cycle. There is only 1
 * cycle. How many nodes max might A and B have labelled
 * the same?
 * 
 * idea: there are only K ways each cycle can be rotated.
 * all we have to do is rotate A's cycle for each of K possible
 * degree rotations and find the max # of nodes that match up w
 * B's specifications. Then we add the nodes not found in either
 * cycle bc we can assume those are the same. Then we're done!
 * In order to find the max # of nodes that match for each of
 * A's rotated cycles and B's cycle, we can keep a 'difference'
 * freq array. If node x is in A's og cycle at index j and in
 * B's og cycle at index k, we do freqDiff[k-j]++;
 * Then at the end, we find the max freq of differences and just
 * have that many nodes that are the same. Yay we done!
 * 
 * difficulty: the idea was easy to come up with once i was
 * thinking clearly and not under time pressure. need to practice
 * more timed contest stress w coding. had some debug trouble,
 * but that was just bc i wasn't careful and switched up n and k
 * as bounds to loops, plus i forgot to code in the backwards
 * case
 * 
 */
import java.util.*;
import java.io.*;

public class silver2sol {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of barns/nodes
		int k = Integer.parseInt(st.nextToken()); // # of barns in cycle
		int[] A = new int[n]; // A[x] gives index node x is at in A's cycle
		int[] B = new int[n]; // B[x]: index node x is at in B's cycle
		
		boolean[] inACycle = new boolean[n];
		int cntOfDistinctNodesInCycles=0;
		
		HashMap<Integer, Integer> freq = new HashMap<>(); 
		// keeps track of frequency of differences between the same node #
		HashMap<Integer, Integer> freq2 = new HashMap<>();
		// for comparing A against flipped B
		
		Arrays.fill(A, -1);
		Arrays.fill(B, -1);
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<k;j++) {
			int temp = Integer.parseInt(st.nextToken())-1;
			if(!inACycle[temp]) {
				cntOfDistinctNodesInCycles++;
				inACycle[temp]=true;
			}
			A[temp] = j;
		}
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<k;j++) {
			int temp = Integer.parseInt(st.nextToken())-1;
			if(!inACycle[temp]) {
				cntOfDistinctNodesInCycles++;
				inACycle[temp]=true;
			}
			B[temp] = j;
		}
		
		for(int j=0;j<n;j++) { // for node
			if(A[j]==-1 || B[j]==-1) continue;
			int diff;
			diff = (B[j]-A[j]+k)%k; // ensures >0
			freq.put(diff,freq.getOrDefault(diff, 0)+1);
//
			diff = ((k-A[j]-B[j]-1)%k+k)%k; // opposite way
			freq2.put(diff,freq2.getOrDefault(diff, 0)+1);
		}
		
		int ans = 0;
		for(Integer key : freq.keySet()) {
			ans = Math.max(ans, freq.get(key));
		}
		for(Integer key : freq2.keySet()) {
			ans = Math.max(ans, freq2.get(key));
		}


		out.println(ans +n-cntOfDistinctNodesInCycles); 
		// max # of nodes that can stay the same in cycles
		// + # of nodes never mentioned in any cycle
		
		in.close();
		out.close();
	}
}
