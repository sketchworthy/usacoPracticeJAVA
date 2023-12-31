import java.util.*;
public class jumpJohnSmith {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] A = new int[N];
		for(int i = 0; i<N;i++) {
			A[i] = in.nextInt();
		}
		int i = 0; 
		int cnt = 0;
		while(i<N-1) {
			int max = 0; 
			int maxindice = 0;
			for(int j = i; j<=Math.min(A[i] + i, N-1);j++) {
				if(j + A[j] >= max) {
					max = j+A[j];
					maxindice = j;
				}
			}
			i = maxindice;
			if(max == 0 || A[i] == 0) {
				cnt = -1;
				break;
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}
