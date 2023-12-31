/*
 * basic idea p simple in the end, find the min & max x's and y's. executing the code
 * is a NIGHTMARE.
 */
import java.util.*;
import java.io.*;
public class fieldReduction {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("reduce.in"));
		PrintWriter out = new PrintWriter(new File("reduce.out"));
		int n = in.nextInt();
		int[][] cowLocs = new int[n][3]; // k,0 is x-val, k,1 is y-val, k,2 is org index
		for(int j=0;j<n;j++) {
			for(int k=0;k<2;k++)
				cowLocs[j][k]=in.nextInt();
			cowLocs[j][2]=j;
		}
//		for(int j=0;j<n;j++)
//			System.out.println(Arrays.toString(cowLocs[j]));
//		System.out.println();
//		Arrays.sort(cowLocs,(x1,x2) -> x1[0]-x2[0]); // sort by ascending x-val
//		for(int j=0;j<n;j++)
//			System.out.println(Arrays.toString(cowLocs[j]));
		
		
		Arrays.sort(cowLocs,(x1,x2) -> x1[0]-x2[0]); // sort by ascending x-val
		int minX1=cowLocs[0][0]; // most max x val
		int minX1i=cowLocs[0][2]; // index of most max x val cow
		int minX2=cowLocs[1][0]; // 2nd most max x val
//		int minX2i=cowLocs[1][2];
		int maxX1=cowLocs[n-1][0];
		int maxX1i=cowLocs[n-1][2];
		int maxX2=cowLocs[n-2][0];
//		int maxX2i=cowLocs[n-2][2];
		
		Arrays.sort(cowLocs,(x1,x2) -> x1[1]-x2[1]); // sort by ascending y-val
		int minY1=cowLocs[0][1];
		int minY1i=cowLocs[0][2];
		int minY2=cowLocs[1][1];
//		int minY2i=cowLocs[1][2];
		int maxY1=cowLocs[n-1][1];
		int maxY1i=cowLocs[n-1][2];
		int maxY2=cowLocs[n-2][1];
//		int maxY2i=cowLocs[n-2][2];
		
//		long tArea = (maxX1-minX1)*(maxY1-minY1);
//		// maximize the area subtracted
		long sArea = Long.MAX_VALUE;
		// remove minX val cow
		if(minX1i==minY1i) sArea=Math.min((maxX1-minX2)*(maxY1-minY2), sArea);
		if(minX1i==maxY1i) sArea=Math.min((maxX1-minX2)*(maxY2-minY1), sArea);
		sArea=Math.min((maxY1-minY1)*(maxX1-minX2), sArea);
		// remove maxX val cow
		if(maxX1i==minY1i) sArea=Math.min((maxX2-minX1)*(maxY1-minY2), sArea);
		if(maxX1i==maxY1i) sArea=Math.min((maxX2-minX1)*(maxY2-minY1), sArea);
		sArea=Math.min((maxY1-minY1)*(maxX2-minX1), sArea);
		// remove minY val cow
		if(minY1i==minX1i) sArea=Math.min((maxY1-minY2)*(maxX1-minX2), sArea);
		if(minY1i==maxX1i) sArea=Math.min((maxY1-minY2)*(maxX2-minX1), sArea);
		sArea=Math.min((maxY1-minY2)*(maxX1-minX1), sArea);
		// remove maxY val cow
		if(maxY1i==minX1i) sArea=Math.min((maxY2-minY1)*(maxX1-minX2), sArea);
		if(maxY1i==maxX1i) sArea=Math.min((maxY2-minY1)*(maxX2-minX1), sArea);
		sArea=Math.min((maxY2-minY1)*(maxX1-minX1), sArea);
		
		out.close();
		in.close();
	}
}
