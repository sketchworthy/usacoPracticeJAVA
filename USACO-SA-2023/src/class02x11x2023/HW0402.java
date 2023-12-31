package class02x11x2023;
/* USACO 2017 Dec Silver Prob 1: My cow ate my HW COMPLETE
 * 
 * idea: loop thru all vals of K (possible # of 1st qs ur cow ate)
 * and find the one that gives u the max remaining avg score
 * 
 * difficulty: pretty ez tbh but i had trouble debuggin bc i did
 * some dumb mistakes w inequalities that took a while to catch
 */
import java.util.*;
import java.io.*;

public class HW0402 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new FileReader("homework.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("homework.out"));
		int n = Integer.parseInt(in.readLine()); // # of qs
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] scores = new int[n];
		long totalScoreSum=0;
		for(int j=0;j<n;j++) {
			scores[j]=Integer.parseInt(st.nextToken());
			totalScoreSum+=scores[j];
		}
//		out.println("total score: "+totalScoreSum);
		double maxAvg=0;
		ArrayList<Integer> maxAvgKis=new ArrayList<>();
//		int maxAvgK=-1;
		int cMinScoreI=0;
		for(int j=1;j<n;j++) {
			if(scores[j]<scores[cMinScoreI])cMinScoreI=j;
		}
		for(int k=1;k<=n-2;k++) {
			totalScoreSum-=scores[k-1];
			// check if min score was removed
			if(cMinScoreI==k-1) { // if so, find new min score
				cMinScoreI=k;
				for(int j=k+1;j<n;j++) {
					if(scores[j]<scores[cMinScoreI])cMinScoreI=j;
				}
			}
			// current # of scores: n-k
			// remove min, find avg, see if avg replaces current maxAvg
//			out.println(n-k-1);
			double avg=(totalScoreSum-scores[cMinScoreI]+0.0)/(n-k-1);
			if(avg>maxAvg) {
				maxAvg=avg;
				maxAvgKis.clear();
				maxAvgKis.add(k);
			}
			else if(avg==maxAvg) {
				maxAvgKis.add(k);
			}
		}
//		out.print(maxAvgK+1);
		for(int j=0;j<maxAvgKis.size();j++){
			out.println(maxAvgKis.get(j));
		}
		in.close();
		out.close();
	}
}

