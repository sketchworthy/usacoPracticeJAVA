// put 2 arrays next to eachother
public class Ex1406 {

	public static void main(String[] args) {
		int[][] A1 = new int [5][6];
		int[][] A2 = new int [5][6];
		for(int r=0;r<A1.length;r++) {
			for(int c=0;c<A1[r].length;c++) {
				A1[r][c]=(int)(Math.random()*10);
				A2[r][c]=(int)(Math.random()*10);
			}
		}
		printArray(A1);
		System.out.println();
		printArray(A2);
		
		int[][]A3=new int[A1.length][A1[0].length+A2[0].length];
		for(int r=0;r<A3.length;r++) {
			for(int c=0;c<A1[r].length;c++) {
				A3[r][c]=A1[r][c];
			}
			for(int c=A1[0].length;c<A1[r].length+A2[0].length;c++) {
				A3[r][c]=A2[r][c-A1[0].length];
			}
		}
		System.out.println();
		printArray(A3);
	}
	
	static void printArray(int[][]A) {
		for (int r=0;r<A.length;r++) {
			for (int c=0;c<A[r].length;c++) {
				System.out.print(A[r][c]+" ");
			}
			System.out.println();
		}
	}

}
