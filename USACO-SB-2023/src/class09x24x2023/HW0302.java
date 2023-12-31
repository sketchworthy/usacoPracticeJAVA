package class09x24x2023;
/* USACO 2020 Dec Silver COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1064
 * 
 * idea:
 * u want to sort the cows in a way that you can arrange a method
 * of easily finding the next cows that will meet and remove them
 * from consideration. 
 *  
 * pseudocode:
 * array of cows
 * sorted by bottom-top list of cows going east
 * sorted by left-right list of cows going north
 * loop thru each of the e-bound cows
 *  loop thru all nbound cows and check in order if meetings occur. if
 *  that ncow has already been blocked, /continue./
 *  otherwise block either that cow or the current cow.
 *  else if there are no cows this cow will meet going right,
 *  the cow is free of further interaction and can be
 *  set aside. once a cow is blocked they can be yeeted
 *  from the list
 *  proceed in this fashion until this eCow has met its match
 *  (blocked or travel forever right)
 *  then move on to next eCow
 *  once uve gone thru all cows, just print blames in order
 * 
 * difficulty: god once i understood the key idea that u gotta
 * just loop thru in order it was so ez of implementation
 * after ofc i also figured out what data structure to use
 */
import java.util.*;
import java.io.*;

public class HW0302 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of cows
		
		int[][] cowxy = new int[n][3]; // x, y, ID
		char[] cowdir = new char[n];
		int[] cowblame = new int[n];
		boolean[] cblocked=new boolean[n]; // cblocked[x] true if cow x is blocked

		ArrayList<Integer> eCows = new ArrayList<>(); // indices, ascending by y val
		ArrayList<Integer> nCows = new ArrayList<>(); // ascending by x val
		
		for(int j=0;j<n;j++) { // take in input of cows
			StringTokenizer st = new StringTokenizer(in.readLine());
			cowdir[j]=st.nextToken().charAt(0);
			cowxy[j][0]=Integer.parseInt(st.nextToken());
			cowxy[j][1]=Integer.parseInt(st.nextToken());
			cowxy[j][2]=j;
			
			// add cow ID to sorted set
			if(cowdir[j]=='E') eCows.add(j);
			else nCows.add(j);
		}
		Collections.sort(eCows,(a,b)->cowxy[a][1]-cowxy[b][1]);
		Collections.sort(nCows,(a,b)->cowxy[a][0]-cowxy[b][0]);

		for(int eid:eCows) { // for cow id in eastbound cows
//			boolean found=false; // a cow who blocked eid was found
			for(int nid:nCows) { // see if itll meet nid in nCows
				if(cblocked[nid])continue;// if this cows alr interrupted
				if(cowxy[nid][1]>cowxy[eid][1]) { // if they don't actually meet (y)
					continue;
				}
				if(cowxy[nid][0]<cowxy[eid][0]) continue; // dont meet bc x-variable
				
				// coords these cows would meet is (cowxy[nid][0],cowxy[eid][1])
				int edist=cowxy[nid][0]-cowxy[eid][0]; // distance ecow travels
				int ndist=cowxy[eid][1]-cowxy[nid][1];
				if(edist==ndist)continue; // cows share time space and dont block
				// otherwise figure out who blocks who
				if(edist>ndist) { // if ecow is blocked by ncow
					cblocked[eid]=true;
//					found=true;
					cowblame[nid]+=cowblame[eid]+1;
					break;
				}
				else { // ecow was not blocked by ncow but ncow was blocked
					cblocked[nid]=true;
					cowblame[eid]+=cowblame[nid]+1;
				}
			}
		}
		
		
		for(int j=0;j<n;j++) {
			out.println(cowblame[j]);
		}

		in.close();
		out.close();
	}
}


//class Cow {
//	public Cow(char dir,long xx, long yy, int i) {
//		x=xx; // starting x coord
//		y=yy;
//		blame=0;
//		direction=dir;
//		ID=i;
//		stopped=false;
//	}
//	public long x;
//	public long y;
//	public long blame;	
//	public char direction;
//	public int ID;
//	public boolean stopped;
//	
//	public boolean beats(Cow b) { // returns true if this cow stops b
//		
//	}
//}