// find GCD using Euclidean Algorithm

public class Ex1010 {
	// Euclidean Algorithm function, which returns GCD of ints a and b
	public static int EuclidAlg(int a, int b) {
		int small = Math.min(a, b); // small is smaller num
		int large = Math.max(a, b); // large is larger num
		int r = large % small; // r is remainder when small is divided into large
		// now to compare small and r
		while(r>0) {
			large = small;
			small = r;
			r = large % small;
		}
		return small;
	}
	
	public static void main(String[] args) {
		System.out.println(EuclidAlg(20,46));
	}

}
