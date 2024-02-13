package class01x21x2024;
/* USACO 2021 April Silver COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1136
 * h-index: largest # h s.t. the author has >= h papers 
 * with >= h citations
 * 
 * idea: inverse binary search for the max h-index bess can achieve.
 * low is 0. high is n. for each target h-index, check if
 * it can be met by simulating adding citations of closest papers
 * 
 * to more easily simulate adding citations, instead of doing binary search
 * to find threshold i can simplify by just ignoring the ones who
 * already have >=h citations
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0202 {
	static int[] citations;
	static int n;
	static long k, l;
	
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); // # of papers bess has published
		k = Integer.parseInt(st.nextToken()); // # of articles bess writes
		l = Integer.parseInt(st.nextToken()); // max # of articles citable in 1 article
		
		st = new StringTokenizer(in.readLine());
		citations = new int[n]; // citations[x]: # of citations of paper x
		for(int j=0;j<n;j++) {
			citations[j]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(citations);
		
		// inverse binary search for greatest achievable h-index
		int low = 0;
		int high= n;
		while(low<high) {
			int h = (low+high+1)/2;
			
			if(isValid(h)) {
				low=h;
			}
			else {
				high=h-1;
			}
		}
		
		out.println(low);

		in.close();
		out.close();
	}
	
	static boolean isValid(int h) { // checks if h-index of h is achievable
		
		long articlesLeft=k*l; // how many articles left to be written/used
		int hMet=0; // # of papers that have met h citations
		// iterate from largest to smallest/back to front
		int j=n-1;
		while(j>=0 && articlesLeft>=0 && hMet<h) { // see how many articles need to be
			// written for paper citations[j] to meet h citations
			if(citations[j]>=h) {
				hMet++;
				j--;
				continue;
			}
			
			// citations[j]<h, need to add citations
			if(h-citations[j]>k) { // if citations needed > # of articles available
				return false;
			}
			
			articlesLeft-=h-citations[j];
			hMet++;
			j--;
		}
		if(articlesLeft<0 || hMet<h) {
			return false;
		}
		return true;
		
		
		
		
		
		
		
		
		
		
		
		
		// version 2 with another round of binary search: 
		//  more buggy bc more complex
//		int numPassedH = 0; // # of articles already w more than h citations
//		// binary search for threshold index
//		
//		int smallestH=-1; // smallest index that has citations[smallestH]>=h 
//		if(citations[n-1]>=h) { // if citations[n-1]<h, numPassedH is alr 0
//			// find smallest index that has citations[midI]>=h 
//			int low = 0; int high = n;
//			while(low<high) {
//				int midI = (low+high)/2;
//				
//				if(citations[midI]>=h) {
//					high=midI;
//				}
//				else {
//					low=midI+1;
//				}
//			}
//			numPassedH=n-low;
//			smallestH=low;
//		}
//		int articlesLeft = h-numPassedH; // # of articles left that need to get >=h cites
//		if(articlesLeft>k)return false;
//		
//		// check if possible to get articlesLeft # of articles >= h citations
//		// need to get citations[smallestH-1] ... [smallestH-articlesLeft] >=h 
//		// (if smallestH-articlesLeft>=0. but if its <, return false)
//		if(smallestH-articlesLeft<0) return false;
//		
//		// if k articles each adding 1 citation not enough for smallest paper
//		if(citations[smallestH-articlesLeft]+k<h)return false;
//		
//		
//		// check if possible to get articlesLeft # of articles >= h citations
//		// with k articles each citing L distinct papers
//		
//		// see if k*l is >= # of articles we need to add
//		int articlesNeeded=0;
//		for(int j=smallestH-articlesLeft;j<=smallestH-1;j++) {
//			articlesNeeded+=h-citations[j];
//		}
//		
//		if(k*l>=articlesNeeded) return true;
//		return false;
	}
}