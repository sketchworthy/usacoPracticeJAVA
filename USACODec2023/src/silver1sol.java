/* USACO 2023 Dec Silver Q1 _status{COMPLETE or not}_
 * http://usaco.org/index.php?page=viewproblem&cpid=1338
 * My personal official post-contest solution
 * 
 * Given nodes with N diff weights, a balanced tower
 * is a list of nodes where every node is at least K 
 * greater in weight than the node below them. Each
 * cow can only belong in 1 tower.
 * What is the max amount of cows that can become
 * involved in at most M balanced towers?
 * 
 * idea:
 * we have cow weight freqs and m empty towers to fill.
 * To involve the most cows: start with smallest weight 
 * cow and binary search for smallest-diff cow, add to cnt.
 * 
 * --we can iterate thru the cows and place them in the
 * binary-searched towers, which are rearranged each time.
 * we place as many lowest-cows as possible across the m towers,
 * then the next lowest cows that work on the towers, and so on.
 * To do this would require a TreeSet[MAP] of tower top-cow-weights & [THEIR FREQS]
 * a sorted nx2 array of cow weights linked to their frequencies.
 * The treeset starts out with m distinct < -K values.
 * While(true), we look at the smallest tower-top-weight and
 * binary search for the lowest cow weight that will fit it, add it on,
 * then remove all the cow weights lower than it.--
 * 
 * i like the idea of only ever having to look at the smallest 
 * tower-top-weight and finding a cow that goes on there. could we
 * possibly use a queue and just only ever look at the smallest
 * tower-top-weight, put smallest cow possible on there, and push
 * it to the back? yes, bc then then its still an increasing queue from
 * front to back!
 * we can keep a queue of tuples, <tower-top-weight, freq-of-this-tower>.
 * then first thing is to fill the queue with m towers with just 1 cow
 * each, the smallest cow that can go on them. once we've hit that we
 * keep looking at the smallest tower-top-weight and binary search 
 * for the lowest cow weight that will fit it, add it on, then remove 
 * all the cow weights lower than it. if there isn't enough freq of the
 * cow weight we were adding, we append a new freq of tower-top-weights
 * to the end of the queue and remove some freq from our top of queue
 * tower tuple. we keep doing this until we have
 * no cows left to place. then we just take our cnt variable we've been
 * keeping a running tally of and we're done!
 * 
 * difficulty: 
 * 
 */
import java.util.*;
import java.io.*;

public class silver1sol {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of diff weights
		int m = Integer.parseInt(st.nextToken()); // max # of towers left
		int k = Integer.parseInt(st.nextToken()); // min difference of tower nbors
		
		int[][] weightFreqs = new int[n][2]; // (cow weight, freq of that cow)
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			weightFreqs[j][0] = Integer.parseInt(st.nextToken()); // weight
			weightFreqs[j][1] = Integer.parseInt(st.nextToken()); // freq
		}
		Arrays.sort(weightFreqs, (a,b)->(a[0]-b[0]) );
		
		int cnt=0; // # of cows in towers
		int currLow=0; 
		// [currLow,m-1]: only indices of weightFreqs considered at any point
		int numOfTowers=0; // curr # of towers total
		Deque<Tower> towerTops = new ArrayDeque<>();
		
		// add cows initially to towerTops
		while(numOfTowers<m && currLow<n) {
			if(weightFreqs[currLow][1] <= m-numOfTowers) {
				// if # of smallest cows doesn't breach # of towers left 
				Tower t = new Tower(weightFreqs[currLow][0],
										weightFreqs[currLow][1]);
				numOfTowers+=weightFreqs[currLow][1];
				weightFreqs[currLow][1]=0;
				currLow++;
				towerTops.add(t);
			}
			else { // if # of smallest cows more than # of towers left
				// just add the # of towers left of smallest cow
				Tower t = new Tower(weightFreqs[currLow][0],
										m-numOfTowers);
				weightFreqs[currLow][1]-= m-numOfTowers;
				numOfTowers=m;
				towerTops.add(t);
			}
		}
		// update cnt
		cnt = numOfTowers;
		if(cnt<m) { // if ran out of cows to place
			out.println(cnt);
			return;
		}
		
		// add as many cows to towers as possible
		while(currLow<n) { // look at head of queue
			Tower t = towerTops.getFirst();
			// binary search index of lowest cow weight 
			// that can go on tower at front of queue
			int low = currLow;
			int high = m-1;
			while(low<high) {
				int mid = (low+high)/2;
				if(weightFreqs[mid][0]<k+t.weight) {
					low = mid+1;
				}
				else {
					high=mid;
				}
			}
			currLow=low;
			
			// add on the cow weight to the tower
			if(weightFreqs[currLow][1] >= t.freq) {
				// if enough of smallest cow weight for tower t freq 
				Tower t2 = new Tower(weightFreqs[currLow][0], t.freq);
				cnt+=t.freq;
				weightFreqs[currLow][1]-=t.freq;
				towerTops.add(t2);
				towerTops.remove();
			}
			else { // if not enough of smallest cow weight for tower t freq 
				Tower t2 = new Tower(weightFreqs[currLow][0],
										weightFreqs[currLow][1]);
				cnt+=weightFreqs[currLow][1];
				currLow++;
				towerTops.add(t2);
			}
		}

		out.print(cnt);
		
		in.close();
		out.close();
	}
}

class Tower{
	int weight; // of cow at top of tower
	int freq;
	
	public Tower(int w, int f){
		weight=w; freq=f;
	}
}