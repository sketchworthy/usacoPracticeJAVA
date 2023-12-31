package class02x18x2023;
/* Given a non-negative integer represented as a string, remove k digits 
 * from the number so that the new number is the largest possible.
 * 
 * my idea: remove the k smallest digits. if u have to choose between removing
 * 2 equal val ints, remove the more forward 1 
 * ^UPDATE: wouldn't actually work. if u had to remove 1 dig from 43829, this
 * method would give 4389, but largest possibility is actually 4829
 * 
 * class idea: go thru and decide what digit to remove by going from the front.
 * check if the smaller index val is smaller than the next index val. if so,
 * remove it. if its larger, cement it as keeping it
 * to implement this ezily, use a stack. going from the front, push the digis
 * 1 by 1. if the next dig ur gonna push is bigger than the one at the top
 * of the stack, pop() (remove the dig at top of stack) and 
 * again check if dig ur gonna push is bigger than one at top.
 * pop until its smaller, then push the dig.
 * note the stack ends up w largest element at bottom, decreasing as it goes up
 */
import java.util.*;
import java.io.*;

public class Ex0502 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of digits
		int k = Integer.parseInt(st.nextToken()); // # of digits to remove
		String A = in.readLine();
		int[] str = new int[n];
		for(int j=0;j<n;j++)str[j]=A.charAt(j)-'0';
		
		Stack<Integer> s = new Stack<>();
		int i = 0; // index of str we're on
		ArrayList<Integer> removed = new ArrayList<>(); // indices of removed elements
		while(i<n&&removed.size()<k) {
			// check if this dig if larger than s.peek()
			while(!s.empty()&& str[i]>str[s.peek()]&& removed.size()<k) {
				removed.add(s.pop());
			}
			s.push(i);
			i++;
		}
		boolean[] kept = new boolean[n];
		Arrays.fill(kept, true);
		for(int j=0;j<removed.size();j++)kept[removed.get(j)]=false;
//		if(removed.size()<k) {
//			// remove k-removed digits at the front of the remaining str
//			int toRemove=k-removed.size();
//			int j=0; // str's pointer
//			while(j<n && toRemove>0) {
//				if(kept[j]==true) {
//					kept[j]=false;
//					toRemove--;
//				}
//				j++;
//			}
//		}
		for(int j=0;j<n;j++) {
			if(kept[j])out.print(str[j]);
		}
		
		out.close();
	}
}
