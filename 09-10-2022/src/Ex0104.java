/*
 * 
 */
import java.util.*;

public class Ex0104 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of papers written
		int l = in.nextInt(); // limit of papers she can cite
		int[] citations = new int[n]; // citations[x] is the # of citations xth paper has
		for(int j=0;j<n;j++) citations[n]=in.nextInt();
		
		// sort citations in increasing order, loop through them as possible values
		//    for the current h-index
		Arrays.sort(citations);
		int cHdex = 0; // current h index
		for(int hDex:citations) {
			
		}
		
		in.close();
	}
}
