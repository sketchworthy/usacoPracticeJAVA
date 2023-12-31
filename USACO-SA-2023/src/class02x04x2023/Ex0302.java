package class02x04x2023;
/*
 * Problem: USACO 2016 Dec Silver Problem 2. Cities and States COMPLETE
 * 
 * Idea: because you're just looking at the 1st 2 letters, you can reduce the problem
 * to jut looking at all possible 26*26 2-letter combos. then it becomes super ez.
 * idea can be generalized to reduce any problem with smaller recurring symbols
 * 
 * Difficulty: rlly ez once i logic-ed it out and also after class sol was revealed.
 * however idk how hard this is when u dunno how to do it
 */
import java.util.*;
import java.io.*;
public class Ex0302 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new FileWriter("citystate.out"));
		int n = Integer.parseInt(in.readLine()); // # of cities
		int[][] citiesStates = new int[676][676]; // row is city tag. col is state tag
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String city = st.nextToken().substring(0,2);
			String state = st.nextToken(); // already 2 uppercase letters
			// put into citiesStates arr
			citiesStates[convertTagToInt(city)][convertTagToInt(state)]++;
		}
//		System.out.println("hi");
		long ans=0; // # of pairs of cities where each cities' first 2 letters r the
		// same as the other city's state
		
		for(int j=0;j<676;j++) {
			for(int k=0;k<j;k++) {
				ans+=citiesStates[j][k]*citiesStates[k][j];
			}
		}
		out.print(ans);
		in.close();
		out.close();
	}
	public static int convertTagToInt(String XX) {
//		System.out.println("hi");
		return (XX.charAt(0)-'A')*26+(XX.charAt(1)-'A');
	}
}
