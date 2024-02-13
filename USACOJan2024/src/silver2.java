/* USACO 2024 Jan Silver Problem 2 _status{COMPLETE or not}_
 * http://www.usaco.org/index.php?page=viewproblem&cpid=1363
 * The map is a tree of N nodes. A traversal is a path from rm 1
 * to any other rm in the tree. Once you're done you are teleported
 * back to rm 1 and can make more traversals. Your main goal is to
 * complete the map in min # of traversals. Your 2ndary goal is to
 * farm as many potions as possible. Before each traversal, a
 * potion spawns for that 1 traversal in a known room. visiting 
 * that room gets you that potion.
 * If you complete map in min # of traversals, what's the max #
 * of potions you can get?
 * 
 * idea: The min # of traversals is the # of leaf nodes 
 * (not counting root node if it is a leaf node).
 * To get the min # of traversals, each traversal must end at
 * a distinct leaf node. So we just need to rearrange our
 * known # of destinations to maximize the amount of potions
 * we grab at specific points.
 * 
 * ideas: dfs, potion children to the point of leaf nodes <---
 * 
 * implementation: 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class silver2 {
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> potionLeaves;
	static HashMap<Integer, Integer> hasPotion; // <rmID, potionID>
	static ArrayList<Integer> potionsHit; // potionIDs hit on curr dfs branch path
	
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of rms/nodes
		
		int[] potion = new int[n]; 
		// potion[j]: room index potion will spawn at before jth traversal
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			potion[j] = Integer.parseInt(st.nextToken())-1;
		}
		
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int j=0;j<n-1;j++) {
			st = new StringTokenizer(in.readLine());
			int rm1 = Integer.parseInt(st.nextToken())-1;
			int rm2 = Integer.parseInt(st.nextToken())-1;
			adj.get(rm2).add(rm1);
			adj.get(rm1).add(rm2);
		}
		
		// find leaf nodes
		ArrayList<Integer> leaves = new ArrayList<>(); // indices of leaf nodes
		for(int j=1;j<n;j++) { // start from 1 bc don't bother checking root node
			if(adj.get(j).size()==1) {
				leaves.add(j);
			}
		}
		int numOfTraversals=leaves.size();
		
		hasPotion = new HashMap<>();
		for(int j=0;j<numOfTraversals;j++) {
			hasPotion.put(potion[j], j);
		}
		potionLeaves = new ArrayList<>();
		// for each potion, keep a list of the rmIDs of the leaves that 
		// that potion eventually can lead to if starting from root node.
		// we only need to consider potion[] indices [0,numOfTraversals-1]
		for(int j=0;j<numOfTraversals;j++) {
			potionLeaves.add(new ArrayList<>());
		}
		potionsHit = new ArrayList<Integer>();
		
//		// fill the potions hit by each leaf node traversal (potionLeaves) w dfs
		dfs(0,-1);
		// now potionLeaves has been filled
		
		// find max # of potions you can get to if you only use each
		// leaf node traversal once
		HashSet<Integer> leavesReachable = new HashSet<>(); 
		// ^size is unique # of leaves reachable
		for(int j=0;j<numOfTraversals;j++) {
			for(int leaf:potionLeaves.get(j)) {
				leavesReachable.add(leaf);
			}
		}

		out.println(leavesReachable.size());

		in.close();
		out.close();
	}
	
	static void dfs(int node, int parent) {
		// fill the potions hit by each leaf node traversal (potionLeaves) w dfs
		boolean thisPotion=false;
		if(hasPotion.containsKey(node)) { // if a potion at this node
			potionsHit.add(hasPotion.get(node));
			thisPotion=true;
		}
		if(adj.get(node).size()==1) { // if leaf node
			// potionsHit is list of curr potions hit leading up to that node
			// update potionLeaves
			for(int potion:potionsHit) {
				potionLeaves.get(potion).add(node);
			}
		}
		for(int child:adj.get(node)) {
			if(child==parent) continue;
			// else if actual child
			dfs(child,node); // keep searching for potions and leaf nodes
		}
		
		if(thisPotion) { // undo adding potion
			potionsHit.remove(potionsHit.size()-1);
		}
	}
}