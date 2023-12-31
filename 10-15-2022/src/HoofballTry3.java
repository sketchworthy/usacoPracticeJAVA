/* Idea: for each cow, see if they will ever get passed to when every other
 * cow is given a ball. if they don't, they have to get a ball.
 * also, check for two-cow groups isolated from the rest of the game,
 * and give one of them a ball 
 * 
 * NOTE: I had to look at official usaco sol for this :/ */
import java.util.*;
import java.io.*;
public class HoofballTry3 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("hoofball.in"));
		int n = in.nextInt();
		int[] distances = new int[n];
		for(int j=0;j<n;j++)	distances[j]=in.nextInt();
		Arrays.sort(distances); // sort cows from closest to barn to farthest from barn
//		System.out.println(Arrays.toString(distances));
		
		int[] visited = new int[n];
		Arrays.fill(visited, 0);
		// for each cow, make each of its recievers true in visited
		for(int j=0;j<n;j++) {
			ArrayList<Integer> cVisits=findRecievers(distances,j);
			for(int i:cVisits) {
				System.out.println(i);
				System.out.println(Arrays.toString(visited));
				visited[i]++;
			}
		}
		int cnt=0;
		for(int j=0;j<n;j++) {
			if(visited[j]==0) cnt++; // source cow
			else if(visited[j]==1&&j<n-1&&visited[j+1]==1){
				cnt++;
				visited[j+1]=-1; // ensure it won't be counted again
			}
		}
		
		// now also account for case where 2 cows from 1 tiny group
		
		PrintWriter out = new PrintWriter(new File("hoofball.out"));
		out.print(cnt);
//		System.out.println(cnt);
		out.close();
		in.close();
	}
	
	public static ArrayList<Integer> findRecievers(int[] distances, int i){
		// given the distances of the cows, return arraylist of all the
		// cows that will recieve the ball passed to index i cow
		int orgi=i; // original starting index
		ArrayList<Integer> visited = new ArrayList<Integer>(); // visited indexes
		while(true) {
			// simulate going to shorter distance cow from i
			if(i>0&&i<distances.length-1) { // if left and right cow both exists
				if(distances[i]-distances[i-1]<=distances[i+1]-distances[i]) {
					// left has shorter or equal distance
					i--;
				}
				else i++; // right has shorter distance
			}
			else { // if only right exists, or if only left exists, 
				// automatically move i in that direction
				if(i==distances.length-1)i--; // if only left exists
				else if (i==0)i++; // if only right exists
			}
			if(visited.contains(i) || i==orgi) { // now repeating
				break;
			}
			else visited.add(i);
		}
		return visited;
	}
}
