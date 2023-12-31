import java.util.Scanner;
import java.io.*;
public class sleepyCowsSort {
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new File ("sleepy.in"));
		PrintWriter out = new PrintWriter(new File("sleepy.out"));
		int n = in.nextInt(); // num of cows
		// create array for cows
		int[] cows = new int[n];
		for(int j=0;j<n;j++) {
			cows[j]=in.nextInt();
		}
		// check for latest descending value
		int index = 0;
		for(int j=1;j<n;j++) { // check pairs 0-1, 1-2...(n-2)-(n-1)
			if(cows[j]>cows[j-1]) {
				index=j;
			}
		}
		out.println(index);
		
		in.close();
		out.close();
	}
}


// NOTES:
// unsorted \ sorted
// sorted are in ascending order
// output # of unsorted
//        5 3 2 6 1 4
//        5 3 2 6\1 4
// index: 0 1 2 3 4 5
// can check for each subsequent pair of values if its ascending or descending
// for latest descending...





// multi array
// 