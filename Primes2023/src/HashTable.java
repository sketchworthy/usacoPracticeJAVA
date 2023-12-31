/* Program identity: Solution to Main Problem (CS Pset Problem 3)
 * 
 * Programming language & version: Java 16.0.2
 * Development Framework: Eclipse Studio
 * Platform: Windows
 * 
 * Input format:
 * Input should consist of an integer L, a bunch of decimal numbers
 * separated by 1 space each that are each of length L in base 10,
 * and L 10-number freq distributions of each of the 10 possible digits
 * for every one of the L place values throughout the keys.
 * Everything is according to specifications described in the Main Problem.
 * NOTE: Keys inserted into the hash table are all assumed to be distinct,
 * as per the problem set. This program itself does not check to see that
 * inputted keys are distinct when inserting.
 * 
 * Nothing needs to be commented in/out.
 * 
 * I tested many test cases but only saved 2; they are in folder HashTableTestData but
 * need to be moved to the default input file location.
 * Read TestDataREADME in order to understand which test data files were intended for what purpose.
 * HashTableRandomInputDistributionGenerator.java and HashTableRandomInputNumberGenerator.java 
 * were the helper programs I created and used for test data generation.
 * 
 * Another source I used to understand Hash Tables other than the givens was
 * https://courses.cs.washington.edu/courses/cse332/17au/lectures/cse332-17au-lec11-HashingII.pdf
 * 
 * Solution to non-programming problems are in CSapp.pdf
 */
import java.util.*;
import java.io.*;

public class HashTable {
	private static final int CAPACITY = 1021; 
	// I chose to set capacity = 1021 once and for all. 1021 is prime
	// The idea was to have a hash function that made the non-uniform input
	// keys become as uniform as possible. My hope was that h(k)=k mod 1021
	// as a hash function would make the distribution more uniform,
	// no matter how skewed the base 10 decimal digits distribution is
	private static BufferedReader in;
	private static int totalOperations = 0;
	// count of operations computed by hash func and probing funcs together on all elements
	private static long[] table; // initialize all as -1, -1 represents NULL
	private static int L; // # of digits in a key
	private static double[][] distributions;
	//  L lines of 10 INTEGERS representing frequencies of each digit in each
	// position

	private static int hashProbeFunction(int probe_n, int key) {
		// Returns an index given a probe number and the key
		// Doubles as both a hash function (when probe_n=0) and a probe function
		// For my hash function, I chose h(k) = k mod 1021 because I chose
		//  a prime capacity.
		// For my probe function, I chose
		// p(k,i) = ( h(k)+ i( k mod m + 1 ) ) mod [capacity] (Double Hashing)
		//		  = ( k+i + i( k mod m ) ) mod [capacity]
		//
		// I chose double hashing for my probe function because:
		// With the high load factor, I didn't want to choose linear probing 
		// due to clustering, or choose quadratic probing since 
		// it doesn't necessarily cover every index.

		totalOperations++; // for probe_n==0 check
		if (probe_n == 0) {
			totalOperations++; // for key%capacity operation
			return (key) % CAPACITY;
		}

		totalOperations += 5;
		return (key + probe_n + probe_n * (key % 1019)) % CAPACITY; // 5 operations. note m=1019 because 1019 is next largest prime

	}

	public static void insertKey(int key) {
		// given a key, insert it into the hash table using hash_probe_function()
		int probe_n = 0;
		totalOperations++;
		int index = -1;
		totalOperations++;
		
		while (probe_n < CAPACITY) {
			totalOperations++;
			
			index = hashProbeFunction(probe_n, key);
			totalOperations++; // for copying hashProbeFunction value to index
			
			if (table[index] == -1) { // checking if hash table position is NULL don't count as operation
				// table value is NULL, can place key in index
				table[index] = key;
				totalOperations++;
				break;
			}
			totalOperations++; // for checking probe_n<CAPACITY for the last time

			probe_n++;
			totalOperations++;
		}
	}

	public static int findElement(String item) {
		// Given item, return index of item in hash table using hash_probe_function().
		// Return -2 is item is the wrong length of digits.
		// Return -1 if item is not in hash table.
		if (item.length() != L)
			return -2;

		int key = Integer.parseInt(item);
		int probe_n = 0;
		int index = -1;
		while (probe_n < CAPACITY) {
			index = hashProbeFunction(probe_n, key);
			if (table[index] == -1) { // table value is NULL, but item not found yet
				return -1; // item does not exist
			}
			if (table[index] == key) {
				return index;
			}
			probe_n++; // otherwise if table value already occupied, iterate
		}
		return -1; // if gone through all indices but haven't found item, it's not in table
	}

	public static void printHashTable() {
		for (int j = 0; j < CAPACITY; j++) {
			if (table[j] == -1) {
				System.out.println("Index " + j + ": NULL");
			} else {
				System.out.println("Index " + j + ": " + table[j]);
			}
		}
	}

	public static void clearHashTable() {
		Arrays.fill(table, -1);
	}

	private static int isDistributionValid(double[][] distributions) {
		// checks if distributions are valid and add up to 1.0 within 10^(-8).
		//  returns -1 if distribution IS valid, 
		//  otherwise returns the line # of the first invalid distribution
		for (int distributionN = 0; distributionN < L; distributionN++) {
			// for each distribution, check that it adds up to 1
			double sum = 0.0;
			for (int digit = 0; digit < 10; digit++) {
				sum += distributions[distributionN][digit];
			}
			if (Math.abs(sum - 1.0) > 0.00000001)
				return distributionN+1; // if doesn't add up to 1, return 1-indexed line #
		}
		return -1;
	}
	
	private static void readInput() throws Exception { // read in L and distributions of digits
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// read number L on own line
		System.out.println("Enter a number L on its own line. L will be the number of digits in all keys:");
		L = Integer.parseInt(in.readLine()); // # of digits in a key

		// read in distributions of digits (which i never actually end up using oops)
		System.out.println("\nEnter L lines of 10 #s. The i'th line are the digit distributions for the i'th place value: ");
		distributions = new double[L][10];
		for (int distribution = 0; distribution < L; distribution++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int digit = 0; digit < 10; digit++) {
				String rational = st.nextToken();

				if (rational.contains("/")) { // if distribution is written as a fraction
					int slashIndex = rational.indexOf('/');
					double numerator = Double.parseDouble(rational.substring(0, slashIndex));
					int denominator = Integer.parseInt(rational.substring(slashIndex + 1));

					distributions[distribution][digit] = numerator / denominator;
				} else { // if distribution written as 1, 0, or a decimal
					distributions[distribution][digit] = Double.parseDouble(rational);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		// read in L and distributions of digits
		readInput();

		// check distribution of digits is valid
		int validity=isDistributionValid(distributions);
		if (validity != -1) { // if distribution isn't valid
			// throw exception and break
			throw new Exception("Distribution of digits invalid. Line " + validity
			+ " of inputted distribution does not add up to 1. ");
		} else {
			System.out.println("Your distribution is valid!");
		}

		table = new long[CAPACITY]; // initialize all as -1
		Arrays.fill(table, -1);

		// loop, prompting for file name and filling hash table, then perform actions on hash table
		while (true) {
			System.out.println("\nThe capacity of the hash table is " + CAPACITY);

			System.out.println("\nEnter on its own line the name of a file containing numbers generated by the given distribution:");
			String fileName = in.readLine();
			if (fileName.equals("Q"))
				break;
			if (fileName.equals("\n"))
				continue;
			// open reader to file
			BufferedReader fileIn = new BufferedReader(new FileReader(fileName));

			// read & insert elements 1 by 1 into hash table, keeping track of operations
			while (true) {
				String nextLine = fileIn.readLine();
				if (nextLine == null)
					break;
				StringTokenizer st = new StringTokenizer(nextLine);
				while (st.hasMoreTokens()) {
					String key = st.nextToken();
					
					totalOperations++; // for parsing key from String to integer
					insertKey(Integer.parseInt(key));
				}
			}

			System.out.println("\nTotal number of operations computed by hash & probing function: " + totalOperations);
			System.out.println(totalOperations);

			fileIn.close();

			// give options to perform actions on hash table
			System.out.println(
					"\nWould you like to print the hash table?\n" + "(Enter y or n on its own line to respond): ");
			if (in.readLine().equals("y")) {
				printHashTable();
			}

			System.out.println(
					"\nWould you like to search for an element?\n" + "(Enter y or n on its own line to respond): ");
			if (in.readLine().equals("y")) {
				while (true) {
					System.out.println("\nType on its own line the element you would like "
							+ "to search for (leading zeroes included): ");
					String element = in.readLine().trim();
					int index = findElement(element);
					if (index == -1) {
						System.out.println("The element " + element + " was not found in the table.");
						break;
					} else if (index == -2) {
						System.out.println("ERROR: Your given number has the wrong length. Try again.");
					} else {
						System.out.println("The element " + element + " was found at index " + index);
						break;
					}

				}
			}

			System.out.println("\nWould you like to quit the application?\n"
					+ "(Enter Q on its own line to quit, enter anything else to stay): ");
			String response = in.readLine();
			if (response.equals("Q")) {
				break;
			}

			
			// reset in preparation for another set of test data
			clearHashTable();
			totalOperations = 0;
		}
		System.out.println("Program finished.");

		in.close();
	}

}
