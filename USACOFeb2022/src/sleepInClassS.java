import java.util.*;
public class sleepInClassS {
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
			for (int factor=2;factor<=Math.sqrt(totalNaps);factor++) {
				if(totalNaps%factor==0) {
					factors.add(factor);
					factors.add(totalNaps/factor);
				}
			}
			// now factors[] is a list of factors of totalNaps
			
			int lenFact = factors.size();
			int lenLog = log.length;
			int max = 0; // initialize max number in log[]
			for (int index=0;index<lenLog;index++) {
				if (max>log[index]) max=log[index];
			}
			for (int index=0;index<lenFact;index++) { // for factor of totalNaps
				if (totalNaps/factors.get(index)>=max) {// start from the largest number in log[]
					for (int i=0;i<lenLog;i++) { // see if it is possible to partition log into totalNaps/factor factor's
						int part1=0;
						while (part1<totalNaps/factors.get(index)) {
							
						}
					}
				}
			}
			
			System.out.println(); // print minimum # of modifications
		}
		kb.close();
	}
}
