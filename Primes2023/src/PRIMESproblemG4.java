/* MIT PRIMES General Math Problem 4 Programming Solution
 * 
 * Computes all possible ways to write a given natural number
 * n as the sum of squares of consecutive positive integers.
 * 
 * Input & Output:
 * Input the number N on its own line.
 * Program will output the endpoints, inclusive, of all sequences
 * of positive consecutive integers whose squares will sum to N.
 * 
 * 
General Idea: 
We want to find a and b so that (a+1)^2+(a+2)^2+...(b-1)^2+b^2 will equal N.

We will loop thru the range of b, starting from b's max of floor(sqrt(n))
and working down towards 1.
We will keep a variable of a outside of any looping we do.
On the 1st case where b=floor(sqrt(n)), we binary search for the 
largest value of a so that (a+1)^2+(a+2)^2+...(b-1)^2+b^2 >= N.
Then, we set a to that initial largest value. From there, we
always start ticking down the value of a until a is the largest value
such that (a+1)^2+(a+2)^2+...(b-1)^2+b^2 >= N for the particular
b we are dealing with. This way we save some computation time,
since if b is smaller, any valid values of a will definitely get smaller.

 * See Latex document pdf for more finer details of algorithm, 
 * such as how we found the ranges and general motivations
 * 
 * 
 * To test part (c), uncomment the code under // part (c) [lines 53-57]
 */

import java.io.*;

public class PRIMESproblemG4 {
	public static void main(String[] args) throws Exception {

		// read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"Enter a number, N, for which you would like to learn the ways to add consecutive squares to get N: ");
		int N = Integer.parseInt(in.readLine());
		in.close();

		System.out.println(
				"The squares of the terms of the following consecutive sequences, described by their endpoints, will add to N:");
		if (findPossibleSequenceEndpoints(N, true) == 0) { // find and print out endpoints
			System.out.println(
					"[EMPTY]\nActually, no ways found to write N as the sum of squares of consecutive positive integers :(");
		}

		// part (c)
//		System.out.println("\nThe numbers under 150,000 which have 3 different ways to be written as the sum of consecutive squares are");
//		for(N=1;N<150000;N++) {
//			if(findPossibleSequenceEndpoints(N,false)==3) // no need to print out endpoints
//				System.out.println(N);
//		}

	}

	private static int sumOfSquares(int x) { // returns 1^2+2^2+3^2+...+n^2. time complexity O(1)
		return x * (x + 1) * (2 * x + 1) / 6;
	}

	private static int findPossibleSequenceEndpoints(int N, boolean print) {
// returns # of ways to write N as the sum of squares of consecutive positive integers.
// if print is true, also print out the endpoints of each sequence that works

		int count = 0; // tracks # of successes

		// Let a+1 and b be endpoints of sequence whose squares sum to N.
		// We want to find a and b so that (a+1)^2+(a+2)^2+...(b-1)^2+b^2 will equal N

		int bMax = (int) Math.sqrt(N); // take floor of sqrt of N for inclusive max of b. also higher bound for a

		// binary search for largest value of a such that (a+1)^2+(a+2)^2+...(b-1)^2+b^2
		// >= N when b=bMax
		int low = 0;
		int high = bMax; // inclusive max

		// we want a to be smallest value such that sumOfSquares(b)-sumOfSquares(a) >= N
		// so we want sumOfSquares(a) <= sumOfSquares(b)-N
		int goal = sumOfSquares(bMax) - N;
		// make sure a is largest value a can be such that sumOfSquares(a)<=goal
		while (low < high) {
			int a = (low + high) / 2 + 1; // mid of low and high

			int summation = sumOfSquares(a);
			if (summation <= goal) { // a might be able to be bigger
				low = a;
			} else { // summation > goal, a needs to be smaller
				high = a - 1;
			}
		}
		int a = low; // set a to the binary searched value

		// loop thru possible values of b
		// Iterate value of a until you get a
		for (int b = bMax; b >= 1; b--) { 
			while (sumOfSquares(a) > sumOfSquares(b) - N) { // while need to iterate
				a--;
				if (a < 0)
					break;
			}
			// now, sumOfSquares(a) <= sumOfSquares(b)-N
			if (sumOfSquares(a) == sumOfSquares(b) - N) {
				count++; // found the value of a
				if (print)
					System.out.println("Endpoints: " + (a + 1) + " to " + b + ", inclusive");
			}
		}

		return count;
	}
}
