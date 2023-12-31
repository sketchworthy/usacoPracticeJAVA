package class03x18x2023;
/* You're given k amounts of 'points that each of the k jury members gave'.
 * Also, n overall 'finishing' scores that bess remembers. return # of options
 * 
 * og idea: (implemented in HW0801)
 * try each of when bess's first remembered score is after the xth
 * jury member's given points, and then simulate thru for all k options.
 * 
 *  difficulty: this method i used is pretty ez but i think some cases mayyy
 *  have timed out
 *  
 * class idea: (implemented in HW0801v2) use prefix sums to simplify calculations.
 * then simulate for each of k possibilities where bess's 1st memory is
 * after the xth jury decree for k xs.
 */
import java.util.*;
import java.io.*;

public class HW0801 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(st.nextToken()); // # of jury members
		int n = Integer.parseInt(st.nextToken()); // # of scores bess remembers
		st = new StringTokenizer(in.readLine());
		int[] points = new int[k]; // points[x]= # of points given by jury x
		for(int j=0;j<k;j++) points[j]=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		ArrayList<Integer> memories = new ArrayList<>();
//		int[] memories = new int[n]; // memories[x]= xth finishing val FJ had at 1 point
//		for(int j=0;j<n;j++) memories[j]=Integer.parseInt(st.nextToken());
		for(int j=0;j<n;j++) memories.add(Integer.parseInt(st.nextToken()));
		
		HashSet<Integer> ogScoreOptions = new HashSet<>();

		int startScore=memories.get(0);
		for(int x=0;x<k;x++) { // try when bess's 1st remembered score right
			// after xth cow
			startScore-=points[x];
			if(ogScoreOptions.contains(startScore))continue;
			// simulate & see all the finishing points that WOULD happen,
			//   compare it against the vals bess remembers and add the
			//   startScore to ogScoreOptions if it works out
			int cScore=startScore;
			ArrayList<Integer> finishingVals = new ArrayList<>();
			ArrayList<Integer> guessedVals = (ArrayList<Integer>) memories.clone();
			for(int j=0;j<k;j++) {
				cScore+=points[j];
				finishingVals.add(cScore);
				if(guessedVals.contains(cScore)) {
					finishingVals.remove(finishingVals.size()-1);
					guessedVals.remove((Integer)cScore);
				}
			}
			if(guessedVals.isEmpty())ogScoreOptions.add(startScore);
		}
		
		out.print(ogScoreOptions.size());
		in.close();
		out.close();
	}
}
