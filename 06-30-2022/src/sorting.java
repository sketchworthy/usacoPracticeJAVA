import java.util.*;
public class sorting {

	public static void main(String[] args) {
		Random gen = new Random(1);
		int n=10;
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=gen.nextInt(20);
		}
		System.out.println(Arrays.toString(A));
		
		Arrays.sort(A); // ascending order
		System.out.println(Arrays.toString(A));
		
		int[][] B = new int[n][2];
		for(int j=0;j<n;j++) {
			B[j][0]=gen.nextInt(20);
			B[j][1]=gen.nextInt(20);
		}
		for(int j=0;j<n;j++)
			System.out.println(Arrays.toString(B[j]));
		System.out.println();
		// .sort B w/r column 0
		Arrays.sort(B, (r1,r2) -> r1[0] - r2[0]); // r1 and r2 are 2 rows from B
//		// compare column 0 in r1[0] and r2[0] and see which one will go first
//		// - means r1[0] element will go first
		
//		Arrays.sort(B, (r1,r2) -> r1[0]<r2[0] ? -1 : (r1[0]>r2[0] ? 1 : 0));
		//^fancier way of doing the same thing, sorting in ascending order
		System.out.println();
		for(int j=0;j<n;j++)
			System.out.println(Arrays.toString(B[j]));
		System.out.println();
		
		// Arrays.sort(A,0,m,()->()); //sorts in range [0,m)
	}
}
