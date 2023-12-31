package class01x15x2023;
/* 132 pattern
 * Given an array of n integersA, a 132-pattern is a subsequence of three 
 * integers A[i], A[j], A[k] such that i<j<k and A[i]<A[k]<A[j]
Report 1 if there is a 132-pattern in A, otherwise, report 0.

Solution: done using a monotonic stack (can change to deque for higher
efficiency.)Push elements from back of A into stack. when next element
you're about to push is greater than st.peek, pop the top stack val into
Ak so that it's the greatest Ak less than Aj. when next element is smaller
than Ak, you know you've found a possible (i,j,k).

Difficulty: pretty ez once you know solution, but i had sol spoiled for me
 */
import java.util.*;

public class Ex0101 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if(n<3)System.out.println("0");
		else {
			int[] A = new int[n];
			for(int x=0;x<n;x++)A[x]=in.nextInt();
			Stack<Integer> st = new Stack<Integer>(); // stack of indices
			st.push(A[n-1]);
			int Ak=-1; // index of largest element after A[j] smaller than A[j]
			boolean found=false;
			for(int x=n-2;x>=0;x--) {
				if(A[x]<Ak) {
					System.out.println(1);
					found=true;
					break;
				}
//				if(Ak>-1 && A[x]<st.peek()) {
//					Aj=st.peek();
//				}
				while(!st.isEmpty() && A[x]>st.peek()) {
					Ak=st.pop();
				}
				st.push(A[x]);
			}
			if(!found)System.out.println("0");
		}
		
		in.close();
	}
}
