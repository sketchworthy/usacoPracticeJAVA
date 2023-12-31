// display times table of 9x9 and the sum of each column and sum of each row
public class timesTable {

	public static void main(String[] args) {
		int n = 9; // times table size
		int[][] table = new int[n][n];
		
		// assign to table[r][c]
		for(int r=0;r<n;r++) {
			for(int c=0;c<n;c++) {
				table[r][c]=(r+1)*(c+1);
			}
		}
		
		// display times table
		for (int r=0;r<table.length;r++) {
			for(int c=0;c<table[r].length;c++) {
				System.out.printf("%4d",table[r][c]);
			}
			System.out.print(" | ");
			
			int sum=0; // sum of row r
			for(int c=0;c<table[r].length;c++) {
				sum = sum+table[r][c];
			}
			System.out.println(sum);
		}
		for(int j=0;j<4*n+3+4;j++) {
			System.out.print("=");
		}
		System.out.println();
		
		int total=0;
		for(int c=0;c<n;c++) {
			// column c sum
			int sum=0;
			for(int r=0;r<n;r++) {
				sum+=table[r][c];
			}
			System.out.printf("%4d",sum);
			total+=total+sum;
		}
		System.out.println(" | "+total);
	}
}
