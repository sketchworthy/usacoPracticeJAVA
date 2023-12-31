//package test2;
import java.util.*;
public class jumpGrantZhang {
	static int[] A;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		A = new int[N];
		for (int i = 0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		System.out.println(f(0,0));
	}
	public static int f(int cur, int jumps) {
		if (cur + A[cur] >= A.length-1) {
			return jumps+1;
		}
		if (A[cur] == 0 || jumps == -1) {
			return -1;
		}
		ArrayList<Integer> pos = new ArrayList<>();
		for (int i = 1; i<= A[cur]; i++) {
			pos.add(f(cur+ i, jumps+1));
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < pos.size(); i++) {
			if (pos.get(i) < min && pos.get(i) != -1) {
				min = pos.get(i);
			}
		}
		if (min == Integer.MAX_VALUE) {
			return -1;
		}
		return min;
	}
}
