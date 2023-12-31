/* USACO 2017 Feb Bronze p3
 * very ez
 * basic idea is that most optimal time is first come first served so do that
 */
import java.util.*;

public class Ex0103 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows
		int[][] cows = new int[n][2]; // cows[x][0] is time xth cow arrives
		// cows[x][1] is time xth cow takes
		for(int j=0;j<n;j++) {
			cows[j][0]=in.nextInt();
			cows[j][1]=in.nextInt();
		}
		
		// first come first served
//		for(int j=0;j<n;j++) {
//			for(int k=0;k<2;k++) {
//				System.out.print(cows[j][k]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		Arrays.sort(cows, (x1,x2) -> x1[0]-x2[0]);
//		for(int j=0;j<n;j++) {
//			for(int k=0;k<2;k++) {
//				System.out.print(cows[j][k]+" ");
//			}
//			System.out.println();
//		}
		int time=0;
		for(int j=0;j<n;j++) {
			if(cows[j][0]>time) time=cows[j][0];
			time+=cows[j][1];
		}
		System.out.println(time);
		
		in.close();
	}
}
