package class02x11x2023;
/*
 * USACO Silver Dec 2015 Problem 2: High Card COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=571
 * 
 * idea: go from front, pair off smallest-valued cards el owns w the smallest
 * larger val card bess has. use 2 arrays of bess/el vals w
 * n size arrays, pointers
 * 
 * difficulty: pretty ez. figured out most of it before class sol finished 
 * explaining, but i initially went from back and only used 1 array. class
 * sol is much simpler.
 * 
 */
import java.io.*;
import java.util.Arrays;

public class Ex0401 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter out = new PrintWriter(new FileWriter("highcard.out"));
		int n = Integer.parseInt(in.readLine());
		int[] eCards=new int[n];
		int[] bCards = new int[n];
		boolean[] exists = new boolean[n*2];
		for(int j=0;j<n;j++) {
			eCards[j]=Integer.parseInt(in.readLine())-1;
			exists[eCards[j]]=true;
		} 
		Arrays.sort(eCards);
		int ci = 0; // current index
		for(int j=0;j<2*n;j++) {
			if(!exists[j]) {
				bCards[ci]=j;
				ci++;
			}
		}
		
		int ans=0; // # of pairs
		int ei=0; // elsie's cards pointer
		int bi=0; // bessie's cards pointer
		while(ei<n && bi<n) { // idea: going from smallest possible elsie's card,
			// find smallest possible bessie's card that is larger & pair em off
			if(bCards[bi]>eCards[ei]) {
				ans++;
				ei++;
				bi++;
			}
			else {
				bi++;
			}
		}
		out.println(ans);
		in.close();
		out.close();
	}
}
