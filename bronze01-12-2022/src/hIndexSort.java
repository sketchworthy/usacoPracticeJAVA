import java.util.*;
public class hIndexSort {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N=kb.nextInt(); // citations
		// create array for papers and citations
		int[] C = new int[N];
		for (int j=0;j<N;j++) {
			C[j]=kb.nextInt();
		}
		// sort array
		Arrays.sort(C); // sort in increasing order
//---------------------------------------------------------------------------------

		int ans = 0;
		for(int j=0; j<N;j++) {
			// the papers [N-1-j...N-1] have citation >= C[N-1-j]
			// there are j+1 papers with citation >= C[N-1-j].
			// when j goes up, count j+1 goes up, but citation threshold down.
			// when j+1 > threshold, j is the answer.
			if(j+1>C[N-1-j]) {
				ans=j;
				break;
			}
		}
		System.out.println(ans);
		kb.close();
	}
}
