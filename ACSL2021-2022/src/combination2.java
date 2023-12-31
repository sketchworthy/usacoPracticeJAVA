
public class combination2 {

	public static void main(String[] args) {
		System.out.println(choose(30,15));
	}

	public static long choose(int N,int k) { // N choose k
	       // (N choose i) = (N choose i-1) * (N-i+1)/(i)
	    	long total = 1; // N choose 0 is 1
	    	for(int i=1;i<=k;i++) {
	    		total = total*(N-i+1)/(i);
	    	}
	    	return total;	
	    }
	
}
