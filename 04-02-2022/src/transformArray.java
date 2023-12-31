import java.util.*;
public class transformArray {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt(); // nxn array
		char[][] A = new char[n][n]; // original array
		for(int r=0;r<n;r++) {
				A[r]=kb.next().toCharArray();
		}
		
		char[][] B = new char[n][n]; // transformed array
		for(int r=0;r<n;r++) {
				B[r]=kb.next().toCharArray();
		}
		
		// what transformation makes A.equals(B) true?
		if(same(t1(A),B)) {
			System.out.println(1);
		}
		else if(same(t2(A),B)) {
			System.out.println(2);
		}
		else if(same(t3(A),B)) {
			System.out.println(3);
		}
		else if(same(t4(A),B)) {
			System.out.println(4);
		}
		else if(same(t51(A),B) || same(t52(A),B) || same(t53(A),B)) {
			System.out.println(5);
		}
		else if(same(A,B)) {
			System.out.println(6);
		}
		else System.out.println(7);
		
		kb.close();
	}
	
	static char[][] t1(char[][] A){ // rotate CW 90 deg
		int n = A.length; // length of 2D array is # of rows it has
		char[][] A1 = new char[n][n]; // A after rotated 90 deg CW
		for(int r=0;r<n;r++) {
			for(int c=0;c<n;c++) {
				A1[c][n-r-1]=A[r][c];
			}
		}
		return A1;
	}
	static char[][] t2(char[][] A){
		return t1(t1(A));
	}
	static char[][] t3(char[][] A){
		return t1(t2(A));
	}
	static char[][] t4(char[][] A){
		int n = A.length;
		char[][] A4 = new char[n][n]; // A after reflected over vertical
									// line in middle of image
		for(int r=0;r<n;r++) {
			for(int c=0;c<n;c++) {
				A4[r][n-c-1]=A[r][c];
			}
		}
		return A4;
	}
	static char[][] t51(char[][] A){ // combination of t4 and t1
		return t1(t4(A));
	}
	static char[][] t52(char[][] A){ // combination of t4 and t2
		return t2(t4(A));
	}
	static char[][] t53(char[][] A){ // combination of t4 and t3
		return t3(t4(A));
	}
	static boolean same(char[][] A, char[][] B) {
		int n = A.length;
		for(int r=0;r<n;r++) {
			for(int c=0;c<n;c++) {
				if(B[r][c]!=A[r][c]) return false;
			}
		}
		return true;
	}
}
