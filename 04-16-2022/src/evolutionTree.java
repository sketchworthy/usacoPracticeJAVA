// status: need to check class solution, txt in unit 19, vid unit 20
// TODO: rewatch vid+read the code
import java.util.*;
import java.io.*;
public class evolutionTree {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("evolution.in"));
		PrintWriter out = new PrintWriter(new File("evolution.out"));
		
		/* Essential Problem:
		 * 	Given a set of characteristics of the bottom leaves, ex:
		 *      (s,f,t), (s), (f)
		 *  We wanna output 'no' if any of these characteristic sets
		 *   contain at least 2 of any of the others
		 *  Otherwise output 'yes'
		 */
		int n = in.nextInt(); // # of sub populations
		ArrayList<ArrayList<String>> subPopulations = new ArrayList<ArrayList<String>>();
		int numTraits = 0;
		for(int j=0;j<n;j++) { // fill subPopulations arrayList
			ArrayList<String> subPop = new ArrayList<String>();
			numTraits=in.nextInt();
			for(int k=0;k<numTraits;k++) {
				subPop.add(in.next());
			}
			subPopulations.add(subPop);
		}
//		// test
//		System.out.println(subPopulations);
		
		// remove empty lists
		// if subPopulations contains more than 1 empty list, output 'no'
		// if it has 1 empty list, remove it
		int emptyNum = 0; // # of empty arrayLists in subPopulations
		int size=subPopulations.size();
		for(int j=0;j<size;j++) { // for population in subPopulations
			ArrayList<String> subPop=subPopulations.get(j-emptyNum);
			if(subPop.isEmpty()) {
				subPopulations.remove(subPop); // remove empty lists
				emptyNum++;
			}
		}
		if(emptyNum>1) { // contained more than 1 empty list
			out.write("no");
		}
		else {
			boolean yes = true;
			for (ArrayList<String> subPop:subPopulations) { // for subPop in subPopulations
				if(containingSubPops(subPop,subPopulations)>=2) {
					yes=false;
					break;
				}
			}
			if(yes==false) out.write("no");
			else out.write("yes");
		}
		
		in.close();
		out.close();
	}

	public static int containingSubPops(ArrayList<String> subPop,ArrayList<ArrayList<String>> subPopulations) {
		// returns the # of other subPops in subPopulations that subPop contains
		int count = 0;
		int index = subPopulations.indexOf(subPop);
		for(int j=0;j<index;j++) {
			if(subPop.containsAll(subPopulations.get(j))) {
				count++;
			}
		}
		for(int j=index+1;j<subPopulations.size();j++) {
			if(subPop.containsAll(subPopulations.get(j))) {
				count++;
			}
		}
		return count;
	}
}
