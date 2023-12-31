/* key insight:
 * if the first if statement in Elsie's 'code' is
 * if (b[x]==1) returns 1
 * 	then when the output of the program is 0, you know b[x] was 0
 * so we can check for whenever the input for one of the b[x]'s was always 
 * the same when the output was always the same.
 * 
 * EDIT: THIS IS INCORRECT LOGIC. SEE reverseEngineeringSol2 FOR CORRECT LOGIC
 * 
 * this code works, but it's way too inefficient (5/12 test cases, fails due to memory error)
 * */
import java.util.*;

public class reverseEngineeringSol {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t=in.nextInt();
		for(int i=0;i<t;i++) {
			int n = in.nextInt(); // length of each input string
			int m = in.nextInt(); // # of strings inputted, # of times Elsie's program is 'run'
			ArrayList<String> inputs = new ArrayList<String>();
			ArrayList<Integer> outputs = new ArrayList<Integer>();
			for(int j=0;j<m;j++) {
				inputs.add(in.next());
				Integer x = new Integer(in.nextInt());
				outputs.add(x);
			}
//			for(int j=0;j<m;j++) {
//				System.out.print(inputs.get(j)+" ");
//			}
//			System.out.println();
//			for(int j=0;j<m;j++) {
//				System.out.print(outputs.get(j)+" ");
//			}
			boolean ans = works(inputs,outputs);
			if(ans) System.out.println("OK");
			else System.out.println("LIE");
		}
		in.close();
	}
	
	public static boolean works(ArrayList<String> inputs, ArrayList<Integer> outputs) {
		// returns false if no possible inputs/outputs satisfy program, otherwise true
		
		// base cases
		if (inputs.size()==0) return true; // if size of both inputs and outputs is 0
		boolean allSame=true;
		int out1=outputs.get(0); // check if all outputs are the same
		int newOut=-1; // index of 1st output that is different from out1
		for(int j=1;j<inputs.size();j++) {
			if(outputs.get(j)!=out1) {
				allSame=false;
				newOut=j;
				break;
			}
		}
		if(allSame) return true;
		// check if there are 2 identical inputs that have 2 diff outputs
		HashMap<String,Integer> hm = new HashMap<>();
		for(int j=0;j<inputs.size();j++) {
			if(hm.containsKey(inputs.get(j))&&
					(!hm.get(inputs.get(j)).equals(outputs.get(j)))) return false;
			hm.put(inputs.get(j), outputs.get(j));
		}
		
		// check to see if a character is always the same when the output is always the same
		String baseline1 = inputs.get(0);
		boolean[] valid1 = new boolean[baseline1.length()]; // if char at index i is still valid, valid1[i] true
		String baseline2 = inputs.get(newOut);
		boolean[] valid2 = new boolean[baseline2.length()];
		Arrays.fill(valid1, true);
		Arrays.fill(valid2, true);
		for(int j=1;j<inputs.size();j++) {
			String input = inputs.get(j);
			if(outputs.get(j).equals(out1)) { // check against baseline1
				for(int k=0;k<input.length();k++) {
					if(input.charAt(k)!=baseline1.charAt(k)) valid1[k]=false;
				}
			}
			else { // check against baseline2
				for(int k=0;k<input.length();k++) {
					if(input.charAt(k)!=baseline2.charAt(k)) valid2[k]=false;
				}
			}
		}
//		ArrayList<Integer> validIs=new ArrayList<Integer>(); // valid indices to remove
		for(int j=0;j<baseline1.length();j++) {
			if(valid1[j] || valid2[j]) {
//				validIs.add(j);
				ArrayList<String> inputs1 = new ArrayList<String>();
				inputs1.addAll(inputs);
				ArrayList<Integer> outputs1 = new ArrayList<Integer>();
				outputs1.addAll(outputs);
				inputs1.remove(j);
				outputs1.remove(j);
				if(works(inputs1,outputs1))return true;
			}
		}
//		if(validIs.size()==0)return false;
//		// else, see if at least 1 validI will grant salvation
//		while(validIs.size()>0) {
//			ArrayList<String> inputs1 = new ArrayList<String>();
//			inputs1.addAll(inputs);
//			ArrayList<Integer> outputs1 = new ArrayList<Integer>();
//			outputs1.addAll(outputs);
//			inputs1.remove(validIs.get(0).intValue());
//			outputs1.remove(validIs.get(0).intValue());
//			validIs.remove(0);
//			if(works(inputs1,outputs1))return true;
//		}
		return false;
	}
}
