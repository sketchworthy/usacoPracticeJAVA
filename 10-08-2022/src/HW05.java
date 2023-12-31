/* decently easy. idea is simple, implementation got kinda rough w/ debugging but was ok overall
 */
import java.util.*;
import java.io.*;

public class HW05 {
	public static void main(String[] args) throws Exception{
		Scanner in2=new Scanner(System.in);
		String fileName=in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt(); int m = in.nextInt(); // dimensions of barn
		int q = in.nextInt(); // # of diamonds
		int[][] T = new int[n+1][m+1]; // all tastiness values
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++)
				T[i][j]=in.nextInt(); // fill tastiness array
		}
		
//		for(int j=0;j<=n;j++) {
//			for(int k=0;k<=m;k++) System.out.print(T[j][k]+" ");
//			System.out.println();
//		}
		
		long sum=0; // total tastiness
		for(int j=0;j<q;j++) { // for each diamond
			int cx=in.nextInt();
			int cy=in.nextInt();
			int d = in.nextInt()/2; // length of diamond diagonal, going up OR down
			
			// LOWER HALF OF DIAMOND (including middle row)
			// the bottommost square to get is (cx,cy-d)
			// next is the line (cx-1,cy-d+1),(cx,cy-d+1),(cx+1,cy-d+1)
			// next is (cx-2,cy-d+2),(cx-1,cy-d+2),(cx,cy-d+2),(cx+1...
			// for k it is (cx-k;cy-d+k)...(cx-k+i,cy-d+k)...(cx+k,cy-d+k)
			// ends when k becomes d
			for(int k=0;k<=d;k++) {
				for(int i=0;i<=2*k;i++) {
//					System.out.println(T[cy-d+k][cx-k+i]); // (note: x&y are swapped)
					sum+=T[cx-k+i][cy-d+k]; // increase total tastiness
				}
			}
//			System.out.println();
			// UPPER HALF OF DIAMOND (excluding middle row)
			// topmost square (cx,cy+d)
			// next is the line (cx-1,cy+d-1),(cx,cy+d-1),(cx+1,cy+d-1)
			// for k it is (cx-k,cy+d-k)...(cx-k+i,cy+d-k)...(cx+k,cy+d-k)
			// ends when k becomes d-1
			for(int k=0;k<=d-1;k++) {
				for(int i=0;i<=2*k;i++) {
//					System.out.println(T[cy+d-k][cx-k+i]); // (note: x&y are swapped)
					sum+=T[cx-k+i][cy+d-k]; // increase total tastiness
				}
			}
			
		}
		System.out.println(sum);
		
		
		in.close();
	}
}