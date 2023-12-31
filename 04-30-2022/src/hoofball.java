// status: unfinished, check class solution
import java.util.*;
import java.io.*;
public class hoofball {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("hoofball.in"));
		PrintWriter out = new PrintWriter(new File("hoofball.out"));
		int n = in.nextInt(); // # of cows
		int[] position = new int[n]; // cow positions
		for(int j=0;j<n;j++) {
			position[j]=in.nextInt();
		}
		ArrayList<Integer> requests = new ArrayList<Integer>();
		// ^ positions who requested a ball
		
		for(int j=0;j<n;j++) {
			if(requests.contains(j)) continue; // if a ball will already reach j
			
		}
		
		
		in.close();
		out.close();
	}

}
