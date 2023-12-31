package class04x22x2023;
/*
 * Given an array A of positive ints, replace any 2 adjacent eles in A
 * who aren't relatively prime with their LCM until all adj elements are
 * relatively prime. Return sum of eles in final array
 * 
 * idea: loop thru all ajacent #s and check their gcd. use euclidean alg for 
 * that. 
 * pattern of looping: looking at index i, try combining w i+1. if its
 * impossible, move on. if possible, combine. if uve just combined, check
 * if combining this new term w i-1 and repeat process until all is looped
 * thru.
 * save in a double linked list so that removing nodes in between is ez
 * note: lcm(a,b)=a*b/gcd(a,b)
 * 
 * 
 */
import java.util.*;
import java.io.*;

public class coprime {
	static int MOD = (int)1e9+7;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		ArrayList<Integer> A = new ArrayList<>();
		for(int j=0;j<n;j++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		boolean forward=true;
		int i=0;
		while(i<n;)
		
		
		out.close();
	}
	
	public static int gcd(int a, int b) { // return gcd of a and b w Euclidean Alg
		if(a%b==0)return b;
		if(b%a==0)return a;
		if(b>a)return gcd(b-a,a);
		return gcd(a-b,b);
	}
}

public class Node{
	int val;
}
