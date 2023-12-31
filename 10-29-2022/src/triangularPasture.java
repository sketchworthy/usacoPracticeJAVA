/*
 * 
 */
import java.util.*;
import java.io.*;
public class triangularPasture {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("triangles.out"));
		Scanner in = new Scanner(new File("triangles.in"));
		int n = in.nextInt(); // # of fence posts
		int[][] posts = new int[n][2]; // posts[k][0]=x-coord of kth post
		// posts[k][1]=y-coord of kth post
		for(int j=0;j<n;j++) {
			posts[j][0]=in.nextInt();
			posts[j][1]=in.nextInt();
		}
		
		long maxArea=0;
		// compare every 3 posts
		for(int j=0;j<n;j++) {
			int x1=posts[j][0];
			int y1=posts[j][1];
			for(int k=j;k<n;k++) {
				int x2=posts[k][0];
				int y2=posts[k][1];
				for(int i=k;i<n;i++) {
					int x3=posts[i][0];
					int y3=posts[i][1];
					
					long area=0;
					// check if there is a horizontal and a vertical side
					if((x1!=x2 && x2!=x3 && x1!=x3) || // if all different
							y1!=y2 && y2!=y3 && y1!=y3) continue;
					if((x1==x2&&x2==x3)||(y1==y2&&y2==y3)) continue; // all same
					if(x1==x2&&y1==y3) {
						area=(y1-y2)*(x1-x3);
					}
					else if(x1==x3&&y1==y2) {
						area=(y1-y3)*(x1-x2);
					}
					else if(x2==x1&&y2==y3) {
						area=(y2-y1)*(x2-x3);
					}
					else if(x2==x3&&y2==y1) {
						area=(y2-y3)*(x2-x1);
					}
					else if(x3==x1&&y3==y2) {
						area=(y3-y1)*(x3-x2);
					}
					else if(x3==x2&&y3==y1) {
						area=(y3-y2)*(x3-x1);
					}
					area = Math.abs(area);
					maxArea=Math.max(maxArea, area);
				}
			}
		}
		
		out.print(maxArea);
		out.close();
		in.close();
	}
}
