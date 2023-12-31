package class10x22x2023;
/* USACO 2017 Mar Silver Where's Bessie? Version 2 COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=740
 * 
 * idea: lots and lots of loop layers. just bash and floodfill tbh.
 * because my version 1 was so messy i couldn't figure out how to
 * debug it, this is my 2nd write of Where's Bessie with more cleanliness,
 * helper funcs & classes, etc
 * 
 * difficulty: implementation making sure its clean is hard. but i did it!
 */
import java.util.*;
import java.io.*;

public class HW0702 {
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static char[][] A;
	static ArrayList<Rectangle> PCL;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("where.in"));
		PrintWriter out = new PrintWriter(new FileWriter("where.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // side len of grid
		A = new char[n][n]; // grid
		for(int j=0;j<n;j++)A[j]=in.readLine().toCharArray(); // fill grid
		
		
		PCL = new ArrayList<>(); // contains {r1,c1,r2,c2}s of preexisting PCLs
		boolean[][] visited = new boolean[n][n];

		for(int r1=0;r1<n;r1++) {
			for(int c1=0;c1<n;c1++) { // (r1,c1) upper left corner of rectangle
				for(int r2=n-1;r2>=r1;r2--) {
					for(int c2=n-1;c2>=c1;c2--) { // (r2,c2) upper left corner of rectangle
						// check if rectangle is a PCL candidate by enuring NOT inside another PCL
						Rectangle R = new Rectangle(r1,c1,r2,c2);
						if(!couldBePCL(R))continue;
						
						// if rectangle is a PCL candidate, check if is actually a PCL
						for(int j=0;j<n;j++) Arrays.fill(visited[j], false);
						int[] cFreq = new int[26]; // color freq arr
						
						// floodfill thru R and fill cFreq as we go
						for(int a=r1;a<=r2;a++) {
							for(int b=c1;b<=c2;b++) {
								if(visited[a][b])continue;
								
								visited[a][b]=true;
								cFreq[(A[a][b]-'A')]++;
								ArrayDeque<int[]> dq = new ArrayDeque<>();
								dq.add(new int[] {a,b});
								
								while(!dq.isEmpty()) {
									int[] head = dq.poll();
									for(int i=0;i<4;i++) {
										int r = head[0]+dr[i];
										int c = head[1]+dc[i];
										
										// check if addable to dq
										if(r>=r1 && r<=r2 && c>=c1 && c<=c2 && !visited[r][c]
											&& A[head[0]][head[1]]==A[r][c]) {
											dq.add(new int[] {r,c});
											visited[r][c]=true;
										}
									}
								}
								
							}
						}
						
						// if candidate have 2 colors and just 1 color block with freq 1
						int colors=0; // # of diff colors present
						int numOnes=0; // # of colors who have just 1 block
						for(int j=0;j<26;j++) {
							if(cFreq[j]>0) {
								colors++;
							}
							if(cFreq[j]==1)numOnes++;
							if(numOnes>1 || colors>2)break;
						}
						if(numOnes==1 && colors==2) { // if works, add to PCL
							PCL.add(R);
						}
						
					}
				}
			}
		}
		
		
		out.print(PCL.size());
		in.close();
		out.close();
	}

	public static boolean couldBePCL(Rectangle x) { // if not in another PCL and edges all inside grid
		for(Rectangle i:PCL) {
			if(x.inside(i))return false;
		}
		return true;
	}
}



class Rectangle{
	int r1,c1,r2,c2;
	
	Rectangle(int R1, int C1, int R2, int C2){
		r1=R1; // length of potential PCL
		c1=C1; // width of PCL
		r2=R2; // start index of row for PCL
		c2=C2; // start index of col for PCL
	}
	
	public boolean inside(Rectangle x){ // return true if this obj inside rect x
		return (r1>=x.r1 && c1>=x.c1 && r2<=x.r2 && c2<=x.c2);
	}
	public boolean inside(int R1, int C1, int R2, int C2){ // return true if this obj inside rect x
		return (r1>=R1 && c1>=C1 && r2<=R2 && c2<=C2);
	}
}

