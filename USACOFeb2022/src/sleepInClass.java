import java.util.*;
public class sleepInClass {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int T = kb.nextInt(); // test cases to solve
		for (int test=1;test<=T;test++) { // for each test case
			int N=kb.nextInt(); // class periods to suffer through
			
			int totalNaps = 0; // initialize
			int[] log = new int[N]; 
			for (int period=0;period<N;period++) { // log Bessie's naps during class periods
				log[period]=kb.nextInt();
				totalNaps+=log[period];
			}
			
			// see factors of totalNaps
			ArrayList<Integer> factors = new ArrayList<Integer>();
			if(totalNaps!=0) factors.add(1); // totalNaps divisible by 1
			else if(totalNaps!=1) factors.add(totalNaps); // totalNaps divisible by itself
			for (int factor=2;factor<=totalNaps/2;factor++) {
				if(totalNaps%factor==0) factors.add(factor);
			}
			// now factors[] is a list of factors of totalNaps
			
			int len = factors.size();
			for (int index=0;index<len;index++) { // for factor of totalNaps
				// see if it is possible to partition into totalNaps/factor factor's
				
//				factors[index]
			}
			
			System.out.println(); // print minimum # of modifications
		}
		kb.close();
	}
}
