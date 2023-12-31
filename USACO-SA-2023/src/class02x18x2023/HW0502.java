package class02x18x2023;
/* USACO Mar 2017 Gold Problem 3 Modern Art 2 WRITTEN W/O STACKS
 * 
 * idea: if moonet can't copy, then its NOT an authentic picowso work.
 * so we just have to check how many rounds are needed for moonet to copy
 * the work, and if it's possible at all.
 * in order to check how many rounds needed for copying, my idea is to
 * have an array simulating the copying, & loop from start of the actual
 * finished artwork. for each newly encountered color, put on an interval
 * of its startI to its endI. if it goes over a color, check if that
 * color has already been overwritten (track w boolean array). if not,
 * then this new overwrite means a +1 for a round
 * 
 * this solution is too slow, only 8/10 test cases. need to use stacks
 */
import java.util.*;
import java.io.*;

public class HW0502 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new FileWriter("art2.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of cells
		int[] art = new int[n]; // picowso's alleged art's colors at indices
		for(int j=0;j<n;j++)art[j]=Integer.parseInt(in.readLine());
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
		
		int rounds=1; // max layer
		boolean validArt=true;
		int[] art2 = new int[n]; // moonet's copied art wip
		boolean[] colorCopied = new boolean[n+1]; // tracks which colors used
		int[] layer = new int[n+1]; // layer[x] tracks which layer color x is
//		int[] copiedOver = new int[n+1]; // copiedOver[x] tracks which layer color x is on
		//--if color x has been copied over--
//		boolean newRound=false;
		for(int j=0;j<n;j++) {
			int cLayer=1;
//			newRound=false;
			int color=art[j];
			if(colorCopied[color]||color==0)continue;
			// if color not yet copied, paste an interval from startI to endI
			for(int k=startEndIs[color][0];k<=startEndIs[color][1];k++) {
//				if(art2[k]!=0 && copiedOver[art2[k]]==false) {
//					newRound=true;
//					copiedOver[art2[k]]=true;
//				}
				cLayer=Math.max(cLayer, layer[art2[k]]+1);
				rounds=Math.max(cLayer, rounds);
				art2[k]=color;
			}
//			if(newRound)rounds++;
			layer[color]=cLayer;
			colorCopied[color]=true;
		}
		// check art2 against art and see if they're the same
		for(int j=0;j<n;j++) {
			if(art[j]!=art2[j]) {
				validArt=false;
				break;
			}
		}
		
		if(!validArt)out.print(-1);
		else out.print(rounds);
		in.close();
		out.close();
	}
}
/*
11
3
1
5
1
3
3
6
6
7
8
7
*/