import java.util.*;
public class luckyPerson {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // # of ppl
		int [] A = new int[N-1];
		for(int j=0;j<N-1;j++) { // A is removals
			A[j]=in.nextInt();
		}
		in.close();
		
		ArrayList<Integer> L = new ArrayList<>(); // L is list of ppl
		for (int j=1;j<=N;j++) {
			L.add(j);
		}
		
		// simulate removing A[j]-th person from list
		L.add(L.remove(0)); // first remove 1
		for(int round=0;round<N-1;round++) { // rounds of removal
			// remove A[round]-th person from list
//			System.out.println(L);                       // debugging
			for(int k=0;k<A[round]-1;k++) {
//				System.out.println(L+" "+A[round]);          // debugging
				L.add(L.remove(0));
			}
//			System.out.println("Removing "+L.get(0));          // debugging
			L.remove(0);
		}
		System.out.println(L.get(0));
	}
}
