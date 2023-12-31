/* Idea/Logic:
 * Elsie's program is a tree of if statements that say: if variable v = x, then
 * return a consistent value. We want to find the root node and remove it. If it
 * ever becomes evident that there is no possible root node, we can return false.
 * If the list of variables becomes empty, then we can return true.
 * 
 * a variable is a root node if for all the times it is a consistent value, the
 * output is also a consistent value.
 */
import java.util.*;

public class reverseEngineeringSol2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int t = in.nextInt();t>0;t--) {
			int n = in.nextInt(); // # of chars in input str
			int m = in.nextInt(); // # of input strs/test cases
			ArrayList<String> inputs = new ArrayList<String>();
			ArrayList<Integer> outputs = new ArrayList<Integer>();
			for(int j=0;j<m;j++) {
				inputs.add(in.next());
				outputs.add(in.nextInt());
			}
		int[] validIs = new int[n];	// 1 or 2 at index x if xth char can be root node, neg if can't
//		Arrays.fill(validIs, true);
		if(works(inputs,outputs,validIs))System.out.println("OK");
		else System.out.println("LIE");			
		}
		in.close();
	}
	
	public static boolean works(ArrayList<String> inputs, ArrayList<Integer> outputs, 
			int[] validIs) { 
		if(inputs.isEmpty()) return true; // maybe later add to check if outputs is all the same?
		// check if all outputs are the same, return true if they are
		boolean allSame=true;
		Set<Integer> output = new HashSet<>();
		for(int j:outputs) {
			if(output.contains(j)) {
				allSame=false;
				break;
			}
			output.add(j);
		}
		if(allSame)return true;
		
		// check for root node(s)
		
		ArrayList<Integer> roots = new ArrayList<>(); // contains indexes of charAts
		// if when the same index character of a string is always the same value,
		//  the string's output is also the same value, add to list of possible roots
		
		for(int j=0;j<inputs.get(0).length();j++) { // inputs.get(x).charAt(j)
			if(validIs[j]<=0)continue; // skip this char if it can't be a root
			char baseline = inputs.get(0).charAt(j);
			int baseline2i=-1;
			boolean firstDiff=true;
			boolean B1canBeRoot=true;
			boolean B2canBeRoot=true;
			for(int x=1;x<inputs.size();x++) { 
				if(inputs.get(x).charAt(j)==baseline) { // if input char matches baseline
					// check if output also matches baseline
					if(B1canBeRoot && outputs.get(x)!=outputs.get(0)) B1canBeRoot=false;
				}
				else {
					if(firstDiff) {
						baseline2i=x;
						firstDiff=false;
						
					}
					else if (B2canBeRoot && (outputs.get(x)!=outputs.get(baseline2i))) { 
						// check against other baseline
						B2canBeRoot=false;
					}
				}
				if(!(B1canBeRoot||B2canBeRoot)) {
					validIs[j]--;
				}
			}
			
		}
		// remove valid roots' outputs & run again
		ArrayList<String> inputs2 = new ArrayList<>();
		ArrayList<Integer> outputs2 = new ArrayList<>();
		inputs2.addAll(inputs);
		outputs2.addAll(outputs);
				
		for(int j=0;j<validIs.length;j++) {
			if(validIs[j]) { // then remove entries from inputs2 and outputs2
			// if their inputs.get(j) is the 'right' baseline
			// TODO maybe make validIs an int array to record if it was '0' or '1'
					
			}
		}
		// return XX;	
	}
}
