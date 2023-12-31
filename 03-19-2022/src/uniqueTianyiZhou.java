import java.util.*;
public class uniqueTianyiZhou {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		Integer[] A=new Integer[N];
		for (int j=0;j<N;j++) {
			A[j]=kb.nextInt();
		}
		kb.close();
		
		// sort array ascending
		Arrays.sort(A,(x1,x2)->x1-x2);
		int count=0; // # of increases
		for(int j=0;j<N-1;j++) { // for the pairs of 0,1; 1,2; ... N-1,N
			if(A[j]>=A[j+1]) {
				count+=A[j]-A[j+1]+1;
				A[j+1]+=A[j]-A[j+1]+1;
			}

		}
		System.out.println(count);
	}
}