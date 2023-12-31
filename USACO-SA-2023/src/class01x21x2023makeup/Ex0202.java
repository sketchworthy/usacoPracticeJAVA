package class01x21x2023makeup;
/* read n reactanges R1,R2...Rn & rectange T. There is a region R1 U R2 U...U Rn \ T
 * that denotes the region covered by any of the rectangles but excluding T. Find the
 * area of the smallest rectange that can cover that region.
 * 
 * Idea: A rectangle is defined by its upmost, downmost, rightmost and leftmost
 * coords. So keep track of upmost, downmost, rightmost, leftmost points by some
 * clever mechanism
 * Update: I watched the class sol. basically, the 'clever mechanism' is that,
 * instead of looking at R1UR2U...URn and THEN \ T, look at (R1\T)U(R2\T)U...U(Rn\T)
 * and then from there look at upmost & downmost etc coords. So look at Rj \ T's
 * bounds.
 * To find Rj \ T's bounds, I watched the class sol again and saw that basically,
 * look at the # of corners of Rj that T covers. If It covers 1, 2, or 4 corners,
 * we need to accound for those cases
 * 
 * Difficulty: implementation was pretty annoying, and i kept getting stuck at
 * various steps and having to look at the class sol
 */
import java.util.*;
import java.io.*;

public class Ex0202 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of points
		int[][] rectangles = new int[n][4]; // rectangle coords
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			rectangles[j][0]=Integer.parseInt(st.nextToken()); // x1
			rectangles[j][1]=Integer.parseInt(st.nextToken()); // y1
			rectangles[j][2]=Integer.parseInt(st.nextToken()); // x2
			rectangles[j][3]=Integer.parseInt(st.nextToken()); // y2
		}
		StringTokenizer st = new StringTokenizer(in.readLine());
		int tx1=Integer.parseInt(st.nextToken());
		int ty1=Integer.parseInt(st.nextToken());
		int tx2=Integer.parseInt(st.nextToken());
		int ty2=Integer.parseInt(st.nextToken());
		
		int xmax=0;
		int xmin=Integer.MAX_VALUE;
		int ymax=0;
		int ymin=Integer.MAX_VALUE;
		
		for(int j=0;j<n;j++) { // for each rectangle find its bounds when \ T
			// & update total bounds
			int cxmax=rectangles[j][2];
			int cxmin=rectangles[j][0];
			int cymax=rectangles[j][3];
			int cymin=rectangles[j][1];
			// remove T
			int coveredCornersNum=0;
			boolean[] cornersCovered = new boolean[4];
			if(tx1<=cxmin && cxmin<=tx2 && ty1<=cymin && cymin<=ty2) {
				coveredCornersNum++;
				cornersCovered[0]=true;
			}
			if(tx1<=cxmin && cxmin<=tx2 && ty1<=cymax && cymax<=ty2) {
				coveredCornersNum++;
				cornersCovered[1]=true;
			}
			if(tx1<=cxmax && cxmax<=tx2 && ty1<=cymin && cymin<=ty2) {
				coveredCornersNum++;
				cornersCovered[2]=true;
			}
			if(tx1<=cxmax && cxmax<=tx2 && ty1<=cymax && cymax<=ty2) {
				coveredCornersNum++;
				cornersCovered[3]=true;
			}
			if(coveredCornersNum==2) { // must decrease a bound & update all bounds
				if(cornersCovered[0]&&cornersCovered[1])cxmin=tx2;
				else if(cornersCovered[0]&&cornersCovered[2])cymin=ty2;
				else if(cornersCovered[2]&&cornersCovered[3])cxmax=tx1;
				else if(cornersCovered[1]&&cornersCovered[3])cymax=ty1;
				xmax=Math.max(cxmax, xmax);
				xmin=Math.min(cxmin, xmin);
				ymax=Math.max(cymax, ymax);
				ymin=Math.min(cymin, ymin);
			}
			else if(coveredCornersNum==1 || coveredCornersNum==2) { // update all bounds
				xmax=Math.max(cxmax, xmax);
				xmin=Math.min(cxmin, xmin);
				ymax=Math.max(cymax, ymax);
				ymin=Math.min(cymin, ymin);
			}
			// if all 4 corners covered, do NOT update all bounds
		}
		out.print((xmax-xmin)*(ymax-ymin));
		out.close();
	}
}
