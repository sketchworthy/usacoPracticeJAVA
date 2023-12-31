package oldUsacoProbs;
/* USACO 2022 Mar Bronze Problem 3: Alchemy COMPLETE
 * 
 * Difficulty: kind of medium level. ez once i figured out how to do it!
 * 
 * idea: we want to recursively try to craft the topmost metal, going thru its recipe.
 * if we don't have one of the recipe components, we try to make that component by /that/
 * component's recipe. and so on and so on. to keep track of which ingredients have been
 * used up, i used a universal array with the amounts of metals. this can be done bc
 * each time we try to craft a component it will ALWAYS be necessary, so if we ever
 * run out of a component/can't craft something it's bc we don't have enough ingredients
 * in total. so we can just break. yay!
 */
import java.util.*;

public class alchemy {
	public static int[] metalAmnts;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of distinct metals that exist
		int[] metalAmounts = new int[n+1]; // metalAmnts[0] is nothing
		metalAmnts=metalAmounts;
		for(int j=1;j<=n;j++)metalAmnts[j]=in.nextInt();
		int k = in.nextInt(); // # of recipies bessie knows
		HashMap<Integer,ArrayList<Integer>>recipes= new HashMap<>();
		for(int j=0;j<k;j++) {
			ArrayList<Integer> components = new ArrayList<>(); // will contain component metal #s
			int l = in.nextInt();
			recipes.put(l, components);
			for(int m=in.nextInt();m>0;m--) components.add(in.nextInt());
		}
		
//		int[][] recipes = new int[k][]; // recipes bessie knows
//		for(int j=0;j<k;j++) {
//			int l = in.nextInt();
//			int m = in.nextInt();
//			recipes[j] = new int[m];
//			recipes[j][0]=l; // metal # who is being formed
//			for(int i=1;i<=m;i++) { // component metal #s
//				recipes[j][i]=in.nextInt();
//			}
//		}
		
		int ans=metalAmnts[n];
		
		while(true) {
			if(canMake(n,recipes))ans++;
			else break;
		}
//		if(recipes.get(n)!=null) {
//			while(true) { // check if you can make more metal #N thru its recipe
//				ArrayList<Integer> components = recipes.get(metalAmnts);
//				for(int component:components) {
//					if(metalAmnts[component]>0)metalAmnts[component]--;
//					else if(metalAmnts[component]==0 && component!=1 && recipies.get(component)!=null) { 
//						// if you don't have component and are able to craft it
//						// see if you can craft the component!
//						boolean canCraft=false;
//						// check the recipe
//						
//						
//						// if u can't, u done boi
//						if(!canCraft)break;
//					}
//					
//				}
//			}
//			
//		}
		System.out.println(ans);
		in.close();
	}
	public static boolean canMake(int metalX, HashMap<Integer,ArrayList<Integer>> recipes) {
		// returns true if its possible to make 1 metalX w what u have
		
		// search recipes for ability to make metalX
		if(recipes.get(metalX)==null)return false;
		ArrayList<Integer> components = recipes.get(metalX);
		for(int component:components) {
			if(metalAmnts[component]>0)metalAmnts[component]--;
			// if you don't have the component in your metalAmnts[]
			else if(component!=1) { // metalAmnts[component]==0
				// see if you can craft the component!
				if(canMake(component,recipes))continue;
				else return false;
			}
			else return false;
		}
		return true;
	}
}
