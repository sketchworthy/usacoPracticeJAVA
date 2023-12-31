package class02x18x2023;
/* SOLUTION USING STACKS: given arr A of n digits, find largest 
 * multiple of 3 makeable w digits of A
 * 
 * idea: put digits into array and pre-sort them into ascending order. then,
 * separate all digits into 3 stacks: 0 mod 3, 1 mod 3, and 2 mod 3.
 * when removing the #s, if sum is 1 mod 3 check if 1 mod 3 stack has anything.
 *  if not then remove 2 from 2 mod 3
 * if sum 2 mod 3 check try to remove 2 from 1 mod 3, else remove 2 from 1 mod 3 
 * then put them all into an arrayList and sort that, then print it.
 * 
 * motivation: when u see there are ascending order or descending order lists
 * of #s u gotta go thru and remove stuff from, think of pre-sorting an array
 * you've put the #s into and slapping it into a queue
 * 
 * difficulty: hard b/c one of my 1st times working w queues, even tho the q
 * itself is pretty ez.
 * 
 * NOTE: Deques can be used like stacks faster than actual stacks in java
 * Just use .add to add stuff and .removeLast to remove stuff
 */
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class HW0501v2 {
	static long MOD=(long) (1e9+7);
	
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader(new File("10.in")));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		Deque<Integer> s0 = new ArrayDeque<>();
		Deque<Integer> s1 = new ArrayDeque<>();
		Deque<Integer> s2 = new ArrayDeque<>();
		long sum=0;
		for(int j=n-1;j>=0;j--) {
			int x = A[j];
			if(x%3==0)s0.add(x);
			else if(x%3==1) {
				s1.add(x);
				sum+=1;
			}
			else {
				s2.add(x);
				sum+=2;
			}
		}
		sum%=3;
		if(sum==1) {
			if(!s1.isEmpty()) {
				s1.removeLast();
			}
			else {
				s2.removeLast();
				s2.removeLast();
			}
		}
		else if(sum==2) {
			if(!s2.isEmpty()) {
				s2.removeLast();
			}
			else {
				s1.removeLast();
				s1.removeLast();
			}
		}
		
		ArrayList<Integer> answ = new ArrayList<>();
		while(!s1.isEmpty()) {
			answ.add(s1.removeLast());
		}
		while(!s2.isEmpty()) {
			answ.add(s2.removeLast());
		}
		while(!s0.isEmpty()) {
			answ.add(s0.removeLast());
		}
		Collections.sort(answ);
		StringBuilder ans = new StringBuilder();
		for(int j=answ.size()-1;j>=0;j--) {
			ans.append(answ.get(j));
		}
		BigInteger x = new BigInteger(ans.toString());
		BigInteger mod = new BigInteger(String.valueOf(MOD));
		out.print(x.remainder(mod));
		
		out.close();
	}
}
