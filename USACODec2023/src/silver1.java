/* USACO 2023 Dec Silver _status{COMPLETE or not}_
 * http://usaco.org/index.php?page=viewproblem&cpid=1338
 * 
 * Given nodes with N diff weights, a balanced tower
 * is a list of nodes where every node is at least K 
 * greater in weight than the node below them. Each
 * cow can only belong in 1 tower.
 * What is the max amount of cows that can become
 * involved in at most M balanced towers?
 * 
 * idea: To involve the most cows: start with smallest weight 
 * cow and keep iterating up to smallest-diff cow, add to cnt.
 * keep a list of lists, towers, sorted by weight of 1st index.
 * 
 *  
 * in order to iterate, keep an arr of weights&freqs.
 * binary search for smallest index such that weight at that
 * index >= weight of arr[0]+K. 
 * 
 * difficulty: this one runs out of memory :/
 * 
 */
import java.util.*;
import java.io.*;

public class silver1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of diff weights
		int m = Integer.parseInt(st.nextToken()); // max # of towers left
		int k = Integer.parseInt(st.nextToken()); // min difference of tower nbors
		
		// establish freq arr
		int[][] weightFreqs = new int[n][2]; // (weight,# of cows with the weight)
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			weightFreqs[j][0]=Integer.parseInt(st.nextToken()); // weight
			weightFreqs[j][1]=Integer.parseInt(st.nextToken()); // freq
		}
		
		Arrays.sort(weightFreqs,(a,b)-> (a[0]-b[0]));
		long cnt=0;
		
		// keep an arr of binary searched >=k and <=k indices
		int[] kdiff = new int[n]; 
	//kdiff[j]: smallest weightFreqs[] index s.t. its weight is >= index j's weight +k
		Arrays.fill(kdiff, -1);
		
		// binary search for kdiff[0][1]
		if(k==0) {
			kdiff[0]=0;
		}
		else if(weightFreqs[n-1][0]<weightFreqs[0][0]+k) {
			kdiff[0]=-1;
		}
		else {
			int low=0;
			int high=n-1;
			while(low<high) {
				int mid = (low+high)/2;
				
				if(weightFreqs[mid][0]>=weightFreqs[0][0]+k) {
					high=mid;
				}
				else {
					low=mid+1;
				}
			}
			kdiff[0]=low;
		}
		
		for(int j=1;j<n;j++) { // fill kdiff[j] using kdiff[j-1]
			if(kdiff[j-1]==-1) {
				kdiff[j]=-1;
			}
			else if(weightFreqs[j][0]+k>weightFreqs[n-1][0]) {
				kdiff[j]=-1;
			}
			else { 
				int ci = kdiff[j-1];
				while(ci<n-2 && weightFreqs[ci+1][0]<weightFreqs[j][0]+k) {
					ci++;
				}
				ci++;
				kdiff[j]=ci;
			}
			
		}
		
		
		int startI=0; // weightFreqs[] index of first cow in curr tower
		while(m>0) { // create new towers while towers left >0
			ArrayList<Integer> seq = new ArrayList<>(); //seq of indices in curr tower
			while(weightFreqs[startI][1]==0) startI++;
			seq.add(startI);
			long minNumOfCurrTower=weightFreqs[startI][1]; // min of freqs of indices in seq
			
			int last=0;
			while(true) { // look for next num in sequence
				// binary search using kdiff[]
				int cNextI=kdiff[seq.get(last)];
				last++;
				if(cNextI==-1) break;
				while(cNextI<n && weightFreqs[cNextI][1]==0) {
					cNextI++;
				}
				if(cNextI==n)break;
				
				// cNextI is next index in sequence
				seq.add(cNextI);
				minNumOfCurrTower=Math.min(weightFreqs[cNextI][1],minNumOfCurrTower);
				
			} // finished constructing sequence
			if(minNumOfCurrTower>m) minNumOfCurrTower=m;
			m-=minNumOfCurrTower;
			for(int i:seq) {
				weightFreqs[i][1]-=minNumOfCurrTower;
			}
			cnt+=last*minNumOfCurrTower;
		}

		out.println(cnt);

		in.close();
		out.close();
	}
}
