/* Given four non-zero integers a,b,c,d find the results
 * a/b+c/d,a/b-c/d,(a/b)(c/d),(a/b)/(c/d)
 * in simplified fractions. If the result fraction is negative, make the numerator negative.*/
public class HW10 {
	
	// Euclidean Algorithm function, which returns GCD of ints a and b
	public static int EuclidAlg(int a, int b) {
		int small = Math.min(Math.abs(a), Math.abs(b)); // small is smaller num
		int large = Math.max(Math.abs(a), Math.abs(b)); // large is larger num
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
		int a=2;
		int b=4;
		int c=6;
		int d=8;
		
		// find a/b+c/d, which is (ad+bc)/bd simplified
		int gcdNumDenom1 = EuclidAlg(a*d+b*c,b*d); // find the gcd of the num and denom
		int denom1 = b*d/gcdNumDenom1;
		int numerator1 = (a*d+b*c)/gcdNumDenom1;
		System.out.println(numerator1+"/"+denom1); // fraction 1
		
		// find a/b-c/d, which is (ad-bc)/bd simplified
		int gcdNumDenom2 = EuclidAlg(a*d-b*c,b*d);
		int denom2 = b*d/gcdNumDenom2;
		int numerator2 = (a*d-b*c)/gcdNumDenom2;
		System.out.println(numerator2+"/"+denom2); // fraction 2
		
		// find (a/b)*(c/d), which is ac/bd simplified
		int gcdNumDenom3 = EuclidAlg(a*c,b*d);
		int denom3 = b*d/gcdNumDenom3;
		int numerator3 = a*c/gcdNumDenom3;
		System.out.println(numerator3+"/"+denom3); // fraction 3
		
		// find (a/b)/(c/d), which is ad/bc simplified
		int gcdNumDenom4 = EuclidAlg(a*d,b*c);
		int denom4 = b*c/gcdNumDenom4;
		int numerator4 = a*d/gcdNumDenom4;
		System.out.println(numerator4+"/"+denom4); // fraction 4
	}

}
