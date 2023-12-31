/*
 * VERY unfinished, copied half from class
 */
import java.util.*;
public class sleeping {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N=in.nextInt();
		int[] A = new int[N];
		long s0=0;
		for(int j=0;j<N;j++)
			A[j]=in.nextInt();
			s0+=A[j];
		}
		
		// s1=A[0]+... + A[j] if s1|sum, then try to partition array into segments where each seg
			// has sum s1
		long s1=0;
		for(int j=0;j<N;j++) {
			s1+=A[j]; // gets partial sum A[0...j]
			
			// if s1 is factor of sum
			if((s1==0&&sum==0) || sum%s1==0) {
				// partition A[j+1...N-1] into segments with sum equal to s1
				int steps=partition(A,j+1,s1);
				if(steps>=0) {
					int M = steps+j;
					System.out.println(M);
					break;
			}
			in.close()
		}
	}
	
	static int partition(int[] A, int x, long s1) {
		int ans = 0;
		int N = A.length;
		while(x<N) {
			long s2=A[x];
			int y=x+1;
			while(y<N&&s2<s1) {
				s2+=A[y];
				y++;
			}
			// s2 = A[x]+...+A[y-1]; lags behind y by 1 position
			
			if(s2==s1) {
				ans+= y-1-x;
				x=y;
			}
			else {
				return -1;
			}
		}
		return ans;
	}
}
