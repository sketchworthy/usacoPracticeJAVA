// from checking class solution, the idea is that you find the max # of
//  common traits for all pairs of animals

// btw this one is unfinished
import java.util.*;
public class guessingGame {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // # of animals being guessed
		ArrayList<String> allTraits = new ArrayList<String>();
		//^ all animal traits shown by animals
		
		ArrayList<ArrayList<String>> animals = new ArrayList<ArrayList<String>>();
		for(int j=0;j<N;j++) { // for animals being guessed
			ArrayList<String> aTraits = new ArrayList<String>();
			// ^ a single animal's traits
			String aName = in.next();
			int num = in.nextInt();
			for(int k=0;k<num;k++) { // add the traits to aTraits<>
				aTraits.add(in.next());
			}
			animals.add(aTraits);
	// now for each trait in aTraits, check to see if its in allTraits
			// if not put it in
			for(int k=0;k<num;k++) {
				String trait = aTraits.get(k);
				if(!allTraits.contains(trait)) {
					allTraits.add(trait);
				}
			}
		}
//		// test
//		System.out.println(allTraits);
//		System.out.println(animals);
		
		// create an array with the size of # of traits
		//    then make it count the freq of the traits
		int[] freq = new int[allTraits.size()];
		for(ArrayList<String> aTraits:animals) { // for each animal's traits
			for(String trait:aTraits) { // for a trait animal holds
				int index = allTraits.indexOf(trait); // find its index in allTraits
				freq[index]++; // modify the count
			}
		}
		
//		// test
//		for(int j=0;j<freq.length;j++) {
//			System.out.println(freq[j]+" "+allTraits.get(j));
//		}
		// from most to least frequent trait
		
		
		
		
		// go in order from most traits for an animal to least traits
		
		for(int j=0;j<N;j++) { // for animal being guessed
			
		}
		
		int nYeses = 0; // num of yeses for an animal
		int maxYeses = 0; // max # of yeses for an animal
		
		
		
		int[] aTraits = new int[N]; // animal traits
		
		
		
		in.close();
	}
}


/*
 * idea: make an array for max # of yeses?
 * 
 * make an algorithm (function) to determine the max # of yeses it will
 * take to guess an animal
 * 
 * ex: bird
 * its 1st trait, flies, is unique to it
 * its 2nd trait, eatsworms, is unique to it as well
 * there are 0 non-unique traits, and then naming 1 unique trait means
 * there will be 0+1=1 yeses required, max.
 * 
 * ex: cow
 * 1st trait 'eatsgrass' will not do, b/c other animals have it
 * 2nd trait 'isawesome' is unique, so 1 'yes' will do for that trait
 * 3rd trait 'makesmilk' is not unique
 * 4th trait 'goesmoo' is unique
 * there are 2 non-unique traits, plus naming 1 unique trait means
 * 2+1=3 yeses required max.
 * 
 * ex: sheep
 * 1st trait eatsgrass is not unique
 * 1 trait only so 1 yes will do and a buncha no's that don't matter
 * total 1 non-unique trait, and no other traits so 1+0=1 yeses
 * 
 * ex: goat
 * 1st trait makesmilk not unique
 * 2nd trait eatsgrass not unique
 * 2 traits only so 2 yeses will do and no's
 * total 2 non-unique traits, no other traits so 2+0=2 yeses
 */






// TODO: note to self, check the special case of
/*
 * 3
 * cow 4 eatsgrass isawesome makesmilk goesmoo
 * thing 1 goesmoo (isawesome makesmilk)
 * sheep 1 eatsgrass
 * goat 1 makesmilk
 * 
 * ...since the 'eatsgrass' trait immediately narrows it down to just
 * 2 animals and x's out goat, meaning just after 2 moves, eatsgrass
 * and makesmilk, you know it's a cow
 * 
 * ex: cow
 * 1st trait eatsgrass isn't unique
 * 2nd trait is unique
 * 3rd isn't
 * 4th isn't
 * 
 * this can be solved by:
 * make a copy of original arrayList
 * once you choose a trait, 'eatsgrass', you can remove every animal
 * that doesn't contain eatsgrass and then keep checking
 * so 1st trait eatsgrass aint unique, so only cow and sheep are left
 * 2nd trait isawesome is unique, as is 3rd and 4th traits
 * so 1 non-unique+1 unique = 2
 * 
 * problem with this solution is you'll have to try a bunch of diffo
 * permutations of the traits you ask after which will take time
 * like 25! time
 */


