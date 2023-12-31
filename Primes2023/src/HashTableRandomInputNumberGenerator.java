/* Program identity: 
 * Extra helper class for my own convenience, not part of solution to HashTable problem.
 * Generates input information for HashTable.java (problem #3) by
 * first generating numbers, then deriving the distributions from those randomly generated numbers
 * 
 * Programming language & version: Java 16.0.2
 * Development Framework: Eclipse Studio
 * Platform: Windows
 * 
 * 
Process in-detail:
 * Input the length of the decimal integer, aka the variable L, on its own line
 * Then input N, the number of numbers you want to generate.
The program will then output:
* Length of the decimal integer (aka variable L) on its own line
* L lines of 10 numbers each, distributions of numerals for each of L digits in a #.
* These distributions will be fractions unless they are whole percentages (ex: 0.01, 1/7, 0.57)
* Then the N randomly generated numbers (with leading 0s).
 */
import java.util.*;
import java.io.*;

public class HashTableRandomInputNumberGenerator {
	public static void main(String[] args) throws Exception {

		// set up variables
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome! This program will generate input for HashTable.java");
		System.out.println(
				"by first generating N random numbers of length L, then deriving the distributions from there.");
		System.out.println(
				"\nType the name of the file you'd like to create that will hold L, L 10-digit distributions, then N randomly generated numbers:");
		String fileName = in.readLine(); // read testDataNnumbersM from default location
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		System.out.println("Type L: ");
		int L = Integer.parseInt(in.readLine()); // length of keys in base 10. 4<=L<=10
		System.out.println("Type N: ");
		int N = Integer.parseInt(in.readLine()); // # of keys being generated
		long[][] freqDistributions = new long[L][10]; // gives L lines of 10 #s each
		// each line of 10 #s is the frequencies of the digits that appear in Lth digit
		long[] generatedNumbers = new long[N]; // N numbers with L digits each
		String[] generatedNumStrings = new String[N];
		// string versions of generated #s with leading digits

		StringBuilder stringBuilder;
		HashSet<Long> generatedNums = new HashSet<>(); // to ensure no duplicate keys

		// generate N L-digit numbers and put them into generatedNumbers
		long tenPowL = 10000; // 10 to the power of L
		for (int j = 4; j < L; j++)
			tenPowL *= 10;

		Random rand = new Random();
		for (int j = 0; j < N; j++) {
			while (true) {
				generatedNumbers[j] = Math.abs(rand.nextLong()) % tenPowL;
				// L-digit #, but leading digits gone ^

				// make sure no duplicates
				if (!generatedNums.contains(generatedNumbers[j])) {
					// if key doesn't already exist, business as usual
					generatedNums.add(generatedNumbers[j]);
					break;
				}
				// otherwise keep trying to generate random #s
			}

			stringBuilder = new StringBuilder();
			stringBuilder.append(generatedNumbers[j]);
			int numLen = stringBuilder.length();
			for (int k = 1; k <= L - numLen; k++)
				stringBuilder.insert(0, 0);// add leading zeros
			generatedNumStrings[j] = stringBuilder.toString();
		}
		System.out.println("Numbers randomly generated.");

		// get distributions
		// find frequencies of integers and fill freqDistributions
		for (int j = 0; j < N; j++) { // for each number
			for (int digitIndex = 0; digitIndex < L; digitIndex++) {
				int digit = generatedNumStrings[j].charAt(digitIndex) - '0';
				freqDistributions[digitIndex][digit]++;
			}
		}
		System.out.println("Distributions generated.");

		// print output
		out.println(L);
		for (int j = 0; j < L; j++) { // print L lines of digit distributions
			for (int digit = 0; digit < 10; digit++) {
				// derive digit distributions from their frequencies
				// output fraction or decimal distribution:
				// when simplified, they are both divided by their gcd (Euclidean Algorithm)
				long numerator = freqDistributions[j][digit];
				long denominator = N;
				if (numerator == 0) { // just print 0 if frequency is 0
					out.print(0 + " ");
					continue;
				}
				// otherwise simplify numerator/denominator
				long gcd = euclideanAlg(freqDistributions[j][digit], N);
				numerator /= gcd;
				denominator /= gcd;
				if (100 % denominator == 0) { // if representable as a whole percentage
					// numerator/denominator = (numerator*100/denominator)/100
					out.print((numerator * 100.0 / denominator / 100.0) + " ");
				} else { // print fraction representation
					out.print(numerator + "/" + denominator + " ");
				}
			}
			out.println();
		}
		for (int j = 0; j < N; j++) { // print N randomly generated numbers
			out.print(generatedNumStrings[j] + " ");
		}
		System.out.println("All information has been generated and placed into " + fileName);

		in.close();
		out.close();
	}

	public static long euclideanAlg(long a, long b) {
		// recursively uses euclidean algorithm to return GCD(a,b) for pos whole #s a,b
		if (a < b) { // make a the bigger number
			long temp = b;
			b = a;
			a = temp;
		}
		if (a % b == 0)
			return b;

		if (a < 1 || b < 1)
			return 1;
		return euclideanAlg(b, a % b);
	}
}
