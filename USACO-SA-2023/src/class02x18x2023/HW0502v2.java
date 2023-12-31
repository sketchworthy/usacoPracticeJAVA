package class02x18x2023;
/* USACO Mar 2017 Gold Problem 3 Modern Art 2 WRITTEN USING STACKS
 * 
 * idea: moonet can't copy iff NOT an authentic picowso work.
 * so we just have to check how many rounds are needed for moonet to copy
 * the work, and if it's possible at all.
 * in order to check if possible, stacks idea is to
 * go from left to right of art, and each time u meet a color for the 1st time
 * push it onto stack, and if its last time remove it. if its ever impossible
 * u've found it's impossible.
 * to find # of rounds, look at the max size of the stack over all time.
 * this is because the colors on the stack are the colors currently layered 
 * below the current part of the art
 * 
 * implementation p simple once idea figured out
 * 
 * motivation for stacks: it's a 'will it work if i have stuff overlapping
 * each other at weird intervals' problem, which screams stacks
 */
import java.util.*;
import java.io.*;

public class HW0502v2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new FileWriter("art2.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of cells
		int[] art = new int[n]; // picowso's alleged art's colors at indices
		for(int j=0;j<n;j++)art[j]=Integer.parseInt(in.readLine());
		int rounds=0;
		
		int[][] startEndIs = new int[n+1][2]; // [x][0] is start index of color x
		// [x][1] is last occurrence of color x
		for(int j=0;j<=n;j++) { // if [x][0] is -1, color not present
			Arrays.fill(startEndIs[j], -1); 
		}
		
		for(int j=0;j<n;j++) { // fill startEndIs
			int color = art[j];
			if(startEndIs[color][0]==-1) startEndIs[color][0]=j;
			startEndIs[color][1]=j;
		}
		int[] art2 = new int[n];
		for(int j=1;j<=n;j++) { //fill art2 w startEndIs
			if(startEndIs[j][0]!=-1) art2[startEndIs[j][0]]=j;
			if(startEndIs[j][1]!=-1) art2[startEndIs[j][1]]=j;
		}
		
		int[] metColor = new int[n+1]; // metColor[x]=0 is no, 1 is yes, 2 is met AND finished
		Deque<Integer> q = new ArrayDeque<>();
		boolean possible=true;
		for(int j=0;j<n;j++) {
			int color = art2[j];
			if(color==0)continue;
			if(metColor[color]==0) {
				if(startEndIs[color][0]==startEndIs[color][1]) {
					rounds=Math.max(rounds, q.size()+1);
				}
				else q.add(color);
				rounds=Math.max(rounds, q.size());
				metColor[color]++;
			}
			else { // if(metColor[color]==1)
				if(q.removeLast()!=color) {
					possible=false;
					break;
				}
			}
		}
		if(!possible)out.print(-1);
		else out.print(rounds);
		
		out.close();
	}
}
