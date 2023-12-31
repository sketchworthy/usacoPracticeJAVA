/* unfinished, confused as fuck
 * basic idea: minesweeper style pain
 * debugging went wrong somewhere... i know what i gotta do but idk what tf is going on in my code thats not 
 * making it run
 * blehhhh
 */

import java.util.Scanner;
public class Ex0504 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int r = in.nextInt();
		int c = in.nextInt();
		char[][] A = new char[r][c];
		boolean[][] visited = new boolean[r][c];
		for(int j=0;j<r;j++) {
			A[j]=in.nextLine().toCharArray();
		}
		for(int j=0;j<r;j++) {
			for(int k=0;k<c;k++) {
				System.out.print(A[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println(checkNeighbors(A,visited,0,0));
		in.close();
	}
	public static int checkNeighbors(char[][] A, boolean[][] visited, int x,int y) {
		System.out.println("x: "+x+", y: "+y);
		int total = 0;
		if(A[x][y]!='@') return 0;
		visited[x][y]=true;
		if(x>0) {
			if(A[x-1][y]=='@' && visited[x-1][y]==false) total+=1+checkNeighbors(A,visited,x-1,y); // check left
		}
		if(x<A[0].length-1){
			if(A[x+1][y]=='@' && visited[x+1][y]==false) total+=1+checkNeighbors(A,visited,x+1,y); // check right
		}
		if(y<A.length-1) {
			if(A[x][y+1]=='@' && visited[x][y-1]==false) total+=1+checkNeighbors(A,visited,x,y+1); // check top
		}
		if(y>0){
			if(A[x][y-1]=='@' && visited[x][y+1]==false) total+=1+checkNeighbors(A,visited,x,y-1); // check bottom
		}
		return total;
	}
}
