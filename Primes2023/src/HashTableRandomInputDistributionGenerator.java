/* Program identity: 
 * Extra helper class for my own convenience, not really part of the HashTable problem.
 * Generates input information for HashTable.java (problem #3) by
 * first generating the digits distribution, then generating numbers to match generated distributions
 * 
 * Programming language & version: Java 16.0.2
 * Development Framework: Eclipse Studio
 * Platform: Windows
 * 
 * 
Process in-detail:
 * Input the length of the decimal integer, aka the variable L, on its own line
 * Then input N, the number of numbers you want to generate.
 * Then input L lines of 10 numbers each, the distributions for which you seek
 * randomly generated numbers for
The program will then output:
* Length of the decimal integer (aka variable L) on its own line
* L lines of 10 numbers each, distributions of numerals for each of L digits in a #.
* These distributions will be fractions unless they are whole percentages (ex: 0.01, 1/7, 0.57)
* Then the N randomly generated numbers (with leading 0s).

Citation: TODO
I used the Fisher-Yates algorithm for shuffling the digit frequencies.
Here are the sources I used for learning and implementation help:
https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/#

 */
import java.util.*;
import java.io.*;

public class HashTableRandomInputDistributionGenerator {
	private static int L;
	private static int N;

	public static void main(String[] args) throws Exception {

		// read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome! This program will generate input for HashTable.java");
		System.out.println(
				"by first reading distributions from the user, then generating random numbers to satisfy the distribution.");
		System.out.println(
				"Type the name of the file you'd like to create that will hold L, L 10-digit distributions, then N randomly generated numbers:");
		String fileName = in.readLine();
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		System.out.println("Type L: ");
		L = Integer.parseInt(in.readLine()); // length of keys in base 10. 4<=L<=10
		System.out.println("Type N: ");
		N = Integer.parseInt(in.readLine()); // # of keys being generated

		int[][] freqDistributions = new int[L][10]; // gives L lines of 10 integers each
		String[][] freqDistributionStrings = new String[L][10];

		// read freqDistributions
		System.out.println("Type L lines of 10 numbers each for the digit distributions: ");
		for (int distribution = 0; distribution < L; distribution++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int digit = 0; digit < 10; digit++) {
				freqDistributionStrings[distribution][digit] = st.nextToken();
				if (freqDistributionStrings[distribution][digit].contains("/")) {
					// if distribution is written as a fraction
					int slashIndex = freqDistributionStrings[distribution][digit].indexOf('/');
					int numerator = Integer
							.parseInt(freqDistributionStrings[distribution][digit].substring(0, slashIndex));
					int denominator = Integer
							.parseInt(freqDistributionStrings[distribution][digit].substring(slashIndex + 1));

					// convert to fraction with denom of N gives
					freqDistributions[distribution][digit] = numerator * N / denominator; // as frequency
				} else { // either 0, 1, or decimal
							// multiply decimal by total N to get frequency
					freqDistributions[distribution][digit] = (int) (N
							* Double.parseDouble(freqDistributionStrings[distribution][digit]));
				}
			}
		}

		// generate N random #s from freqDistributions
		// Plan: each place value of the #s is constructed from a distribution of
		// digits.
		// Keep a character array of the #s

		// each line of 10 #s is the frequencies of the digits that appear in Lth digit
		char[][] generatedNumStrings = new char[N][L]; // N #s with L digits each (includes leading digits)

		// put the unrandomized digits in each of generatedNumStrings[][placeValue]
		for (int placeValue = 0; placeValue < L; placeValue++) {
			// generatedNumStrings[][placeValue] is filled by freqDistributions[placeValue]
			int ci = 0; // current slot where we start placing forward from
			for (int digit = 0; digit < 10; digit++) {
				long freq = freqDistributions[placeValue][digit];

				for (int j = ci; j < ci + freq; j++) { // place from ci to ci+freq the current digit
					generatedNumStrings[j][placeValue] = (char) ('0' + digit);
				}
				ci += freq; // iterate
			}
		}

		generatedNumStrings = stringsFisherYatesShuffle(generatedNumStrings);

		// check if all keys are unique. if not, keep shuffling until all keys are
		// unique
		while (!keysUnique(generatedNumStrings)) {
			generatedNumStrings = stringsFisherYatesShuffle(generatedNumStrings);
		}

		System.out.println("Numbers randomly generated.");

		// print output
		out.println(L); // print L on its own line
		for (int j = 0; j < L; j++) { // print L lines of digit distributions
			for (int digit = 0; digit < 10; digit++) {
				out.print(freqDistributionStrings[j][digit] + " ");
			}
			out.println();
		}
		for (int j = 0; j < N; j++) { // print N randomly generated numbers
			for (int k = 0; k < L; k++) {
				out.print(generatedNumStrings[j][k]);
			}
			out.print(" ");// TODO check
		}

		System.out.println("All information has been generated and placed into " + fileName);

		in.close();
		out.close();
	}

	private static char[][] stringsFisherYatesShuffle(char[][] generatedNumStrings) {
		// rand shuffle digits in columns across rows, but in-place of arr,
		// using Fisher-Yates shuffle
		// FISHER-YATES: starting from last element, randomly select an ele
		// from whole array and swap w last element. do this until you hit first element
		Random rand = new Random();
		for (int i = 0; i < N; i++) {
			// shuffle generatedNumStrings[j][placeValue] w fisher yates
			for (int placeValue = L - 1; placeValue >= 0; placeValue--) {
				int index = rand.nextInt(N); // random index from 0 to L-1 inclusive

				// swap generatedNumStrings[j][placeValue] with generatedNumStrings[j][index]
				char temp = generatedNumStrings[index][placeValue];
				generatedNumStrings[index][placeValue] = generatedNumStrings[i][placeValue];
				generatedNumStrings[i][placeValue] = temp;
			}
		}
		return generatedNumStrings;
	}

	private static boolean keysUnique(char[][] generatedNumStrings) {
		for (int j = 0; j < N; j++) {
			for (int k = j + 1; k < N; k++) {
				
				// compare generatedNumStrings[j] and generatedNumStrings[k]
				for(int i1=0;i1<L;i1++) {
					for(int i2=0;i2<L;i2++) {
						if(generatedNumStrings[j][i1]!=generatedNumStrings[k][i2])
							return false;
					}
				}
				
			}
		}
		return true;
	}
}
