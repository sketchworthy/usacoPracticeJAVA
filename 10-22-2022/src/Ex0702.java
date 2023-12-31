/*
 * unfinished - guess the animal jan 2019 usaco bronze
 */
import java.util.*;

public class Ex0702 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of animals
		ArrayList<ArrayList<String>> animals = new ArrayList<ArrayList<String>>();
		ArrayList<String> allTraits = new ArrayList<String>();
		
		for(int j=0;j<n;j++) { // fill arraylists
			in.next();
			ArrayList<String> animalTraits=new ArrayList<String>();
			int k=in.nextInt();
			for(int trait=0;trait<k;trait++) {
				String newTrait=in.next();
				animalTraits.add(newTrait); // add trait
				if(!allTraits.contains(newTrait))allTraits.add(newTrait);
			}	
		}
		
		int maxGuesses=0;
		// TODO for each pair of animals, find max # of common features & add 1
		
		in.close();
	}
}
